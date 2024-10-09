package com.bennero.common.messages;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

public class FileTransferMessageTest {
    private static FileTransferMessage createMsg() {
        Random random = new Random(System.currentTimeMillis());

        UUID uuid = UUID.randomUUID();
        boolean ok = true;
        byte transferType = (byte)random.nextInt();
        String filename = UUID.randomUUID() + ".png";
        int length = 100 + random.nextInt(2000);

        FileTransferMessage out = new FileTransferMessage(uuid, ok, transferType, length, filename);

        return out;
    }

    @Test
    public void testWriteRead() {
        FileTransferMessage out = createMsg();
        byte[] bytes = out.write();

        Assert.assertEquals(Message.getType(bytes), MessageType.FILE_TRANSFER);

        FileTransferMessage in = new FileTransferMessage(bytes);

        Assert.assertEquals(out.getSenderUuid(), in.getSenderUuid());
        Assert.assertEquals(out.getStatus(), in.getStatus());
        Assert.assertEquals(out.getTransferType(), in.getTransferType());
        Assert.assertEquals(out.getNumBytes(), in.getNumBytes());
        Assert.assertEquals(out.getFilename(), in.getFilename());
    }

    @Test
    public void testWriteReadCorrupt() {
        FileTransferMessage out = createMsg();
        byte[] bytes = out.write();

        Random rand = new Random(System.currentTimeMillis());

        // Randomly corrupt a byte at every position to a different value for the length of the message
        for (int i = 0; i < Message.NUM_BYTES; i++) {
            bytes[i] += rand.nextInt(254); // ensure the byte will be a random value except for its current value

            FileTransferMessage in = new FileTransferMessage(bytes);
            Assert.assertEquals(Message.STATUS_FAILED_READ, in.getStatus());
        }
    }
}
