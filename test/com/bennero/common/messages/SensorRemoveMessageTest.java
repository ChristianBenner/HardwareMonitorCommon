package com.bennero.common.messages;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

public class SensorRemoveMessageTest {
    private static SensorRemoveMessage createMsg() {
        Random random = new Random(System.currentTimeMillis());

        UUID uuid = UUID.randomUUID();
        boolean ok = true;
        byte sensorId = (byte)random.nextInt();
        byte pageId = (byte)random.nextInt();

        SensorRemoveMessage out = new SensorRemoveMessage(uuid, ok, sensorId, pageId);

        return out;
    }

    @Test
    public void testWriteRead() {
        SensorRemoveMessage out = createMsg();
        byte[] bytes = out.write();

        Assert.assertEquals(Message.getType(bytes), MessageType.SENSOR_REMOVE);

        SensorRemoveMessage in = new SensorRemoveMessage(bytes);

        Assert.assertEquals(out.getSenderUuid(), in.getSenderUuid());
        Assert.assertEquals(out.getStatus(), in.getStatus());
        Assert.assertEquals(out.getSensorId(), in.getSensorId());
        Assert.assertEquals(out.getPageId(), in.getPageId());
    }

    @Test
    public void testWriteReadCorrupt() {
        SensorRemoveMessage out = createMsg();
        byte[] bytes = out.write();

        Random rand = new Random(System.currentTimeMillis());

        // Randomly corrupt a byte at every position to a different value for the length of the message
        for (int i = 0; i < Message.NUM_BYTES; i++) {
            bytes[i] += rand.nextInt(254); // ensure the byte will be a random value except for its current value

            SensorRemoveMessage in = new SensorRemoveMessage(bytes);
            Assert.assertEquals(Message.STATUS_FAILED_READ, in.getStatus());
        }
    }
}
