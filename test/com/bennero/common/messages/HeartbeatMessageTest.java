package com.bennero.common.messages;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

public class HeartbeatMessageTest {
    @Test
    public void testWriteRead() {
        UUID uuid = UUID.randomUUID();
        boolean ok = true;

        HeartbeatMessage out = new HeartbeatMessage(uuid, ok);
        byte[] bytes = out.write();

        Assert.assertEquals(Message.getType(bytes), MessageType.HEARTBEAT);

        HeartbeatMessage in = new HeartbeatMessage(bytes);

        Assert.assertEquals(uuid, in.getSenderUuid());
        Assert.assertEquals(ok ? Message.STATUS_OK : Message.STATUS_BAD_RECEIVE, in.getStatus());
    }

    @Test
    public void testWriteReadCorrupt() {
        UUID uuid = UUID.randomUUID();
        boolean ok = true;

        HeartbeatMessage out = new HeartbeatMessage(uuid, ok);
        byte[] bytes = out.write();

        Random rand = new Random(System.currentTimeMillis());

        // Randomly corrupt a byte at every position to a different value for the length of the message
        for (int i = 0; i < Message.NUM_BYTES; i++) {
            bytes[i] += rand.nextInt(254); // ensure the byte will be a random value except for its current value

            HeartbeatMessage in = new HeartbeatMessage(bytes);
            Assert.assertEquals(Message.STATUS_FAILED_READ, in.getStatus());
        }
    }
}
