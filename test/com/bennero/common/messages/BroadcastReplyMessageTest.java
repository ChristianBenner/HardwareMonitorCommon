package com.bennero.common.messages;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

public class BroadcastReplyMessageTest {
    @Test
    public void testWriteRead() {
        Random random = new Random(System.currentTimeMillis());

        UUID uuid = UUID.randomUUID();
        boolean ok = true;
        long systemIdentifier = random.nextLong();
        byte[] ipv4 = TestUtil.randomIp4();
        byte[] macAddress = TestUtil.randomMacAddress();
        byte versionMajor = (byte)random.nextInt();
        byte versionMinor = (byte)random.nextInt();
        byte versionPatch = (byte)random.nextInt();
        String hostname = "hellohost";

        BroadcastReplyMessage out = new BroadcastReplyMessage(uuid, ok, systemIdentifier, versionMajor, versionMinor,
                versionPatch, macAddress, ipv4, hostname);
        byte[] bytes = out.write();

        Assert.assertEquals(Message.getType(bytes), MessageType.BROADCAST_REPLY);

        BroadcastReplyMessage in = new BroadcastReplyMessage(bytes);

        Assert.assertEquals(uuid, in.getSenderUuid());
        Assert.assertEquals(ok ? Message.STATUS_OK : Message.STATUS_BAD_RECEIVE, in.getStatus());
        Assert.assertEquals(systemIdentifier, in.getSystemIdentifier());
        Assert.assertEquals(versionMajor, in.getVersionMajor());
        Assert.assertEquals(versionMinor, in.getVersionMinor());
        Assert.assertEquals(versionPatch, in.getVersionPatch());
        Assert.assertArrayEquals(macAddress, in.getMacAddress());
        Assert.assertArrayEquals(ipv4, in.getIp4Address());
        Assert.assertEquals(hostname, in.getHostName());
    }

    @Test
    public void testWriteReadCorrupt() {
        Random random = new Random(System.currentTimeMillis());

        UUID uuid = UUID.randomUUID();
        boolean ok = true;
        long systemIdentifier = random.nextLong();
        byte[] ipv4 = TestUtil.randomIp4();
        byte[] macAddress = TestUtil.randomMacAddress();
        byte versionMajor = (byte)random.nextInt();
        byte versionMinor = (byte)random.nextInt();
        byte versionPatch = (byte)random.nextInt();
        String hostname = "hellohost";

        BroadcastReplyMessage out = new BroadcastReplyMessage(uuid, ok, systemIdentifier, versionMajor, versionMinor,
                versionPatch, macAddress, ipv4, hostname);
        byte[] bytes = out.write();

        Random rand = new Random(System.currentTimeMillis());

        // Randomly corrupt a byte at every position to a different value for the length of the message
        for (int i = 0; i < Message.NUM_BYTES; i++) {
            bytes[i] += rand.nextInt(254); // ensure the byte will be a random value except for its current value

            BroadcastReplyMessage in = new BroadcastReplyMessage(bytes);
            Assert.assertEquals(Message.STATUS_FAILED_READ, in.getStatus());
        }
    }
}
