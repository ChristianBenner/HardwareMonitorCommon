package com.bennero.common.messages;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

public class ConnectionRequestMessageTest {
    @Test
    public void testWriteRead() {
        Random random = new Random(System.currentTimeMillis());

        UUID uuid = UUID.randomUUID();
        boolean ok = true;
        byte[] ipv4 = TestUtil.randomIp4();
        byte versionMajor = (byte)random.nextInt();
        byte versionMinor = (byte)random.nextInt();
        byte versionPatch = (byte)random.nextInt();
        boolean forceConnection = false;
        String hostname = "hellohost";

        ConnectionRequestMessage out = new ConnectionRequestMessage(uuid, ok, versionMajor, versionMinor, versionPatch,
                forceConnection, ipv4, hostname);
        byte[] bytes = out.write();

        Assert.assertEquals(Message.getType(bytes), MessageType.CONNECTION_REQUEST);

        ConnectionRequestMessage in = new ConnectionRequestMessage(bytes);

        Assert.assertEquals(uuid, in.getSenderUuid());
        Assert.assertEquals(ok ? Message.STATUS_OK : Message.STATUS_BAD_RECEIVE, in.getStatus());
        Assert.assertEquals(versionMajor, in.getVersionMajor());
        Assert.assertEquals(versionMinor, in.getVersionMinor());
        Assert.assertEquals(versionPatch, in.getVersionPatch());
        Assert.assertEquals(forceConnection, in.isForceConnection());
        Assert.assertArrayEquals(ipv4, in.getIp4Address());
        Assert.assertEquals(hostname, in.getHostname());
    }

    @Test
    public void testWriteReadCorrupt() {
        Random random = new Random(System.currentTimeMillis());

        UUID uuid = UUID.randomUUID();
        boolean ok = true;
        byte[] ipv4 = TestUtil.randomIp4();
        byte versionMajor = (byte)random.nextInt();
        byte versionMinor = (byte)random.nextInt();
        byte versionPatch = (byte)random.nextInt();
        boolean forceConnection = false;
        String hostname = "hellohost";

        ConnectionRequestMessage out = new ConnectionRequestMessage(uuid, ok, versionMajor, versionMinor, versionPatch,
                forceConnection, ipv4, hostname);
        byte[] bytes = out.write();

        Random rand = new Random(System.currentTimeMillis());

        // Randomly corrupt a byte at every position to a different value for the length of the message
        for (int i = 0; i < Message.NUM_BYTES; i++) {
            bytes[i] += rand.nextInt(254); // ensure the byte will be a random value except for its current value

            ConnectionRequestMessage in = new ConnectionRequestMessage(bytes);
            Assert.assertEquals(Message.STATUS_FAILED_READ, in.getStatus());
        }
    }
}
