package com.bennero.common.messages;

import java.util.UUID;

public class FileTransferMessage extends Message {
    public final static byte TYPE_IMAGE = 0x01;
    // maybe in future releases: public final static byte TYPE_SOFTWARE_UPDATE = 0x02;

    private static final int FILENAME_STR_NUM_BYTES = 48;

    private byte transferType;
    private int numBytes;
    private String filename;

    public FileTransferMessage(UUID senderUuid, boolean ok, byte transferType, int numBytes, String filename) {
        super(senderUuid, ok, MessageType.FILE_TRANSFER);
        this.transferType = transferType;
        this.numBytes = numBytes;
        this.filename = filename;
    }

    public FileTransferMessage(byte[] bytes) {
        super(bytes);
    }

    @Override
    protected void readData() {
        transferType = readByte();
        numBytes = readInt();
        filename = readString(FILENAME_STR_NUM_BYTES);
    }

    @Override
    protected void writeData() {
        writeByte(transferType);
        writeInt(numBytes);
        writeString(filename, FILENAME_STR_NUM_BYTES);
    }

    public byte getTransferType() {
        return transferType;
    }

    public int getNumBytes() {
        return numBytes;
    }

    public String getFilename() {
        return filename;
    }
}
