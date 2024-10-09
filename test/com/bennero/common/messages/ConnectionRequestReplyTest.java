package com.bennero.common.messages;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

public class ConnectionRequestReplyTest {
    @Test
    public void testWriteRead() {
        Random random = new Random(System.currentTimeMillis());

        UUID uuid = UUID.randomUUID();
        boolean ok = true;
        byte versionMajor = (byte)random.nextInt();
        byte versionMinor = (byte)random.nextInt();
        byte versionPatch = (byte)random.nextInt();
        boolean accepted = true;
        boolean mismatch = false;
        boolean inUse = false;
        String currentClientHost = "hellohost";

        ConnectionRequestResponseMessage out = new ConnectionRequestResponseMessage(uuid, ok, versionMajor, versionMinor,
                versionPatch, accepted, mismatch, inUse, currentClientHost);
        byte[] bytes = out.write();

        Assert.assertEquals(Message.getType(bytes), MessageType.CONNECTION_REQUEST_RESPONSE);

        ConnectionRequestResponseMessage in = new ConnectionRequestResponseMessage(bytes);

        Assert.assertEquals(uuid, in.getSenderUuid());
        Assert.assertEquals(ok ? Message.STATUS_OK : Message.STATUS_BAD_RECEIVE, in.getStatus());

        Assert.assertEquals(versionMajor, in.getVersionMajor());
        Assert.assertEquals(versionMinor, in.getVersionMinor());
        Assert.assertEquals(versionPatch, in.getVersionPatch());
        Assert.assertEquals(accepted, in.isConnectionAccepted());
        Assert.assertEquals(mismatch, in.isVersionMismatch());
        Assert.assertEquals(inUse, in.isCurrentlyInUse());
        Assert.assertEquals(currentClientHost, in.getCurrentClientHostname());
    }

    @Test
    public void testWriteReadCorrupt() {
        Random random = new Random(System.currentTimeMillis());

        UUID uuid = UUID.randomUUID();
        boolean ok = true;
        byte versionMajor = (byte)random.nextInt();
        byte versionMinor = (byte)random.nextInt();
        byte versionPatch = (byte)random.nextInt();
        boolean accepted = true;
        boolean mismatch = false;
        boolean inUse = false;
        String currentClientHost = "hellohost";

        ConnectionRequestResponseMessage out = new ConnectionRequestResponseMessage(uuid, ok, versionMajor, versionMinor,
                versionPatch, accepted, mismatch, inUse, currentClientHost);
        byte[] bytes = out.write();

        Random rand = new Random(System.currentTimeMillis());

        // Randomly corrupt a byte at every position to a different value for the length of the message
        for (int i = 0; i < Message.NUM_BYTES; i++) {
            bytes[i] += rand.nextInt(254); // ensure the byte will be a random value except for its current value

            ConnectionRequestResponseMessage in = new ConnectionRequestResponseMessage(bytes);
            Assert.assertEquals(Message.STATUS_FAILED_READ, in.getStatus());
        }
    }
}
