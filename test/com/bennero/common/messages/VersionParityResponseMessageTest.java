package com.bennero.common.messages;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

public class VersionParityResponseMessageTest {
    private static VersionParityResponseMessage createMsg() {
        Random random = new Random(System.currentTimeMillis());

        UUID uuid = UUID.randomUUID();
        boolean ok = true;
        byte versionMajor = (byte)random.nextInt();
        byte versionMinor = (byte)random.nextInt();
        byte versionPatch = (byte)random.nextInt();
        boolean accepted = random.nextBoolean();

        VersionParityResponseMessage out = new VersionParityResponseMessage(uuid, ok, versionMajor, versionMinor,
                versionPatch, accepted);

        return out;
    }

    @Test
    public void testWriteRead() {
        VersionParityResponseMessage out = createMsg();
        byte[] bytes = out.write();

        Assert.assertEquals(Message.getType(bytes), MessageType.VERSION_PARITY_RESPONSE);

        VersionParityResponseMessage in = new VersionParityResponseMessage(bytes);

        Assert.assertEquals(out.getSenderUuid(), in.getSenderUuid());
        Assert.assertEquals(out.getStatus(), in.getStatus());
        Assert.assertEquals(out.getVersionMajor(), in.getVersionMajor());
        Assert.assertEquals(out.getVersionMinor(), in.getVersionMinor());
        Assert.assertEquals(out.getVersionPatch(), in.getVersionPatch());
        Assert.assertEquals(out.isAccepted(), in.isAccepted());
    }

    @Test
    public void testWriteReadCorrupt() {
        VersionParityResponseMessage out = createMsg();
        byte[] bytes = out.write();

        Random rand = new Random(System.currentTimeMillis());

        // Randomly corrupt a byte at every position to a different value for the length of the message
        for (int i = 0; i < Message.NUM_BYTES; i++) {
            bytes[i] += rand.nextInt(254); // ensure the byte will be a random value except for its current value

            PageCreateMessage in = new PageCreateMessage(bytes);
            Assert.assertEquals(Message.STATUS_FAILED_READ, in.getStatus());
        }
    }
}
