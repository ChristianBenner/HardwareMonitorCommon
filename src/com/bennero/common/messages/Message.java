package com.bennero.common.messages;

import java.util.UUID;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

public abstract class Message {
    /*
    A message is 256 bytes long. The structure of each message is:
    InstanceUUID    (Pos: 0, Length: 16): A unique identifier of the device sending the message
    Status          (Pos: 16, Length: 1): GOOD or BAD_DATA to signal to the sender to send the message again
    MessageType     (Pos: 17, Length: 1): Each message has a type 0-255
    Data            (Pos: 18, Length: 229): Different messages have different length of data, maximum of 229 bytes, any unused bytes will be 0
    Checksum        (Pos: 247, Length: 8): Checksum at the end of each message used to verify data integrity
     */

    public final static int NUM_BYTES = 256;
    public static final byte STATUS_OK = 1;
    public static final byte STATUS_BAD_RECEIVE = 2; // the sender is telling us that they did not receive message correctly
    public static final byte STATUS_FAILED_READ = 3; // we failed to read the message (this trumps BAD_RECEIVE anyway because we cannot confirm the integrity of the status)

    private final static int TYPE_POS = 17;
    private final static int SENDER_UUID_POS = 0;
    private final static int CHECKSUM_POS = 247;

    private static final byte BOOL_TRUE = 0x01;
    private static final byte BOOL_FALSE = 0x00;

    private UUID senderUuid;
    private byte status;
    private byte type;

    private byte[] bytes;
    private int index;

    // Constructor used when writing
    protected Message(UUID senderUuid, boolean ok, byte type) {
        this.senderUuid = senderUuid;
        this.status = ok ? STATUS_OK : STATUS_BAD_RECEIVE;
        this.type = type;
    }

    // Constructor used when reading
    protected Message(byte[] bytes) {
        this.bytes = bytes;
        read();
    }

    public final UUID getSenderUuid() {
        return senderUuid;
    }

    public final byte getStatus() {
        return status;
    }

    abstract protected void readData();
    abstract protected void writeData();

    private final void read() {
        index = 0;
        senderUuid = readUuid();
        status = readByte();
        type = readByte();
        readData();
        if(!verify()) {
            status = STATUS_FAILED_READ;
        }
    }

    public final byte getType() {
        return type;
    }

    public final String getTypeString() {
        return MessageType.asString(type);
    }

    public static byte getType(byte[] bytes) {
        return bytes[TYPE_POS];
    }

    public static UUID getSenderUUID(byte[] bytes) {
        return MessageUtils.readUuid(bytes, SENDER_UUID_POS);
    }

    public static String getTypeString(byte[] bytes) {
        return MessageType.asString(getType(bytes));
    }

    public static boolean isValid(byte[] bytes) {
        long receivedChecksum = MessageUtils.readLong(bytes, CHECKSUM_POS);

        // Create a checksum from all the data in the message
        Checksum checksum = new CRC32();
        checksum.update(bytes, 0, CHECKSUM_POS + 1);
        return checksum.getValue() == receivedChecksum;
    }

    private boolean verify() {
        index = CHECKSUM_POS;
        long receivedChecksum = readLong();

        // Create a checksum from all the data in the message
        Checksum checksum = new CRC32();
        checksum.update(bytes, 0, CHECKSUM_POS + 1);
        return checksum.getValue() == receivedChecksum;
    }

    public final byte[] write() {
        index = 0;

        bytes = new byte[NUM_BYTES];
        writeUuid(senderUuid);
        writeByte(status);
        writeByte(type);
        writeData();
        writeChecksum();

        return bytes;
    }

    private void writeChecksum() {
        Checksum checksum = new CRC32();
        checksum.update(bytes, 0, CHECKSUM_POS + 1);

        index = CHECKSUM_POS;
        writeLong(checksum.getValue());
    }

    protected final byte readByte() {
        byte b = bytes[index];
        index++;
        return b;
    }

    protected final boolean readBool() {
        boolean b = bytes[index] == 0x01;
        index++;
        return b;
    }

    protected final UUID readUuid() {
        return new UUID(readLong(), readLong());
    }

    protected final float readFloat() {
        byte[] floatBytes = new byte[Float.BYTES];
        MessageUtils.putBytes(floatBytes, 0, bytes, index, Float.BYTES);
        index += Float.BYTES;
        return MessageUtils.byteArrayToFloat(floatBytes);
    }

    protected final int readInt() {
        byte[] intBytes = new byte[Integer.BYTES];
        MessageUtils.putBytes(intBytes, 0, bytes, index, Integer.BYTES);
        index += Integer.BYTES;
        return MessageUtils.byteArrayToInt(intBytes);
    }

    protected final long readLong() {
        byte[] longBytes = new byte[Long.BYTES];
        MessageUtils.putBytes(longBytes, 0, bytes, index, Long.BYTES);
        index += Long.BYTES;
        return MessageUtils.byteArrayToLong(longBytes);
    }

    protected final String readString(int maxLength) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < maxLength; i++) {
            if (bytes[i + index] == 0x00) {
                break;
            }

            stringBuilder.append((char) (bytes[i + index] & 0xFF));
        }

        index += maxLength;
        return stringBuilder.toString();
    }

    protected final byte[] readBytes(int length) {
        byte[] retBytes = new byte[length];
        for (int i = 0; i < length; i++) {
            retBytes[i] = bytes[index + i];
        }

        index += length;
        return retBytes;
    }

    protected final void writeByte(byte val) {
        bytes[index] = val;
        index++;
    }

    protected final void writeBool(boolean val) {
        bytes[index] = val ? BOOL_TRUE : BOOL_FALSE;
        index++;
    }

    protected final void writeFloat(float val) {
        byte[] floatByteArray = MessageUtils.floatToByteArray(val);
        for (int i = 0; i < floatByteArray.length && i < Float.BYTES; i++) {
            bytes[index + i] = floatByteArray[i];
        }

        index += Float.BYTES;
    }

    protected final void writeInt(int val) {
        byte[] intToByteArray = MessageUtils.intToByteArray(val);
        for (int i = 0; i < intToByteArray.length && i < Integer.BYTES; i++) {
            bytes[index + i] = intToByteArray[i];
        }

        index += Integer.BYTES;
    }

    protected final void writeLong(long val) {
        byte[] longToByteArray = MessageUtils.longToByteArray(val);
        for (int i = 0; i < longToByteArray.length && i < Long.BYTES; i++) {
            bytes[index + i] = longToByteArray[i];
        }

        index += Long.BYTES;
    }

    protected final void writeString(String text, int maxLength) {
        byte[] bytes = new byte[text.length()];
        for (int i = 0; i < text.length(); i++) {
            bytes[i] = (byte) text.charAt(i);
        }

        writeBytes(bytes, maxLength);
    }

    protected final void writeBytes(byte[] val, int maxLength) {
        for (int i = 0; i < val.length && i + index < bytes.length && i < maxLength; i++) {
            bytes[i + index] = val[i];
        }

        index += maxLength;
    }

    protected final void writeUuid(UUID uuid) {
        writeLong(uuid.getMostSignificantBits());
        writeLong(uuid.getLeastSignificantBits());
    }

    private static void putBytes(byte[] dest, int destOffset, byte[] src, int srcOffset, int len) {
        for (int i = 0; i < len; i++) {
            dest[destOffset + i] = src[srcOffset + i];
        }
    }
}
