package com.bennero.common.messages;

import java.util.UUID;

public class MessageUtils {
    public static byte[] floatToByteArray(float val) {
        int bits = Float.floatToIntBits(val);
        return new byte[]
                {
                        (byte) (bits >> 24),
                        (byte) (bits >> 16),
                        (byte) (bits >> 8),
                        (byte) (bits)
                };
    }

    public static float byteArrayToFloat(byte[] bytes) {
        int bits = bytes[0] << 24 |
                (bytes[1] & 0xFF) << 16 |
                (bytes[2] & 0xFF) << 8 |
                (bytes[3] & 0xFF);
        return Float.intBitsToFloat(bits);
    }

    public static byte[] intToByteArray(int val) {
        return new byte[]
                {
                        (byte) (val >> 24),
                        (byte) (val >> 16),
                        (byte) (val >> 8),
                        (byte) (val)
                };
    }

    public static int byteArrayToInt(byte[] bytes) {
        return bytes[0] << 24 |
                (bytes[1] & 0xFF) << 16 |
                (bytes[2] & 0xFF) << 8 |
                (bytes[3] & 0xFF);
    }

    public static byte[] longToByteArray(long val) {
        return new byte[]
                {
                        (byte) (val >> 56),
                        (byte) (val >> 48),
                        (byte) (val >> 40),
                        (byte) (val >> 32),
                        (byte) (val >> 24),
                        (byte) (val >> 16),
                        (byte) (val >> 8),
                        (byte) (val)
                };
    }

    public static long byteArrayToLong(byte[] bytes) {
        long returnVal = 0;
        for (int i = 0; i < bytes.length; i++) {
            returnVal = (bytes[i] & 0xff) + (returnVal << 8);
        }
        return returnVal;
    }

    public static void writeToMessage(byte[] message, int index, float val) {
        byte[] floatByteArray = floatToByteArray(val);
        for (int i = 0; i < floatByteArray.length && i < Float.BYTES; i++) {
            message[index + i] = floatByteArray[i];
        }
    }

    public static void writeToMessage(byte[] message, int index, int val) {
        byte[] intToByteArray = intToByteArray(val);
        for (int i = 0; i < intToByteArray.length && i < Integer.BYTES; i++) {
            message[index + i] = intToByteArray[i];
        }
    }

    public static void writeToMessage(byte[] message, int index, long val) {
        byte[] longToByteArray = longToByteArray(val);
        for (int i = 0; i < longToByteArray.length && i < Long.BYTES; i++) {
            message[index + i] = longToByteArray[i];
        }
    }

    public static void writeStringToMessage(byte[] message, int index, String text, int maxLength) {
        byte[] bytes = new byte[text.length()];
        for (int i = 0; i < text.length(); i++) {
            bytes[i] = (byte) text.charAt(i);
        }

        writeBytesToMessage(message, index, bytes, maxLength);
    }

    public static void writeBytesToMessage(byte[] message, int index, byte[] bytes, int maxLength) {
        for (int i = 0; i < bytes.length && i + index < message.length && i < maxLength; i++) {
            message[i + index] = bytes[i];
        }
    }

    public static void putBytes(byte[] dest, int destOffset, byte[] src, int srcOffset, int len) {
        for (int i = 0; i < len; i++) {
            dest[destOffset + i] = src[srcOffset + i];
        }
    }

    public static void writeToMessage(byte[] message, int index, UUID uuid) {
        writeToMessage(message, index, uuid.getMostSignificantBits());
        writeToMessage(message, index + Long.BYTES, uuid.getLeastSignificantBits());
    }

    public static UUID readUuid(byte[] bytes, int offset) {
        return new UUID(readLong(bytes, offset), readLong(bytes, offset + Long.BYTES));
    }

    public static float readFloat(byte[] bytes, int offset) {
        byte[] floatBytes = new byte[Float.BYTES];
        putBytes(floatBytes, 0, bytes, offset, Float.BYTES);
        return byteArrayToFloat(floatBytes);
    }

    public static int readInt(byte[] bytes, int offset) {
        byte[] intBytes = new byte[Integer.BYTES];
        putBytes(intBytes, 0, bytes, offset, Integer.BYTES);
        return byteArrayToInt(intBytes);
    }

    public static long readLong(byte[] bytes, int offset) {
        byte[] longBytes = new byte[Long.BYTES];
        putBytes(longBytes, 0, bytes, offset, Long.BYTES);
        return byteArrayToLong(longBytes);
    }

    public static String readString(byte[] bytes, int index, int maxLength) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < maxLength; i++) {
            if (bytes[i + index] == 0x00) {
                break;
            }

            stringBuilder.append((char) (bytes[i + index] & 0xFF));
        }
        return stringBuilder.toString();
    }

    public static byte[] readBytes(byte[] bytes, int index, int maxLength) {
        byte[] retBytes = new byte[maxLength];
        for (int i = 0; i < maxLength; i++) {
            retBytes[i] = bytes[index + i];
        }

        return retBytes;
    }

    // Same patch version signifies compatibility
    public static Compatibility isVersionCompatible(byte firstMajor,
                                                    byte firstMinor,
                                                    byte secondMajor,
                                                    byte secondMinor) {
        if (firstMajor < secondMajor) {
            return Compatibility.OLDER;
        } else if (firstMajor > secondMajor) {
            return Compatibility.NEWER;
        } else {
            if (firstMinor < secondMinor) {
                return Compatibility.OLDER;
            } else if (firstMinor > secondMinor) {
                return Compatibility.NEWER;
            } else {
                return Compatibility.COMPATIBLE;
            }
        }
    }

    public enum Compatibility {
        OLDER,
        NEWER,
        COMPATIBLE
    }
}
