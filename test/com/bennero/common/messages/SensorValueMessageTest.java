package com.bennero.common.messages;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

public class SensorValueMessageTest {
    private static SensorValueMessage createMsg() {
        Random random = new Random(System.currentTimeMillis());

        UUID uuid = UUID.randomUUID();
        boolean ok = true;
        byte sensorId = (byte)random.nextInt();
        float value = random.nextFloat();

        SensorValueMessage out = new SensorValueMessage(uuid, ok, sensorId, value);

        return out;
    }

    @Test
    public void testWriteRead() {
        SensorValueMessage out = createMsg();
        byte[] bytes = out.write();

        Assert.assertEquals(Message.getType(bytes), MessageType.SENSOR_UPDATE);

        SensorValueMessage in = new SensorValueMessage(bytes);

        Assert.assertEquals(out.getSenderUuid(), in.getSenderUuid());
        Assert.assertEquals(out.getStatus(), in.getStatus());
        Assert.assertEquals(out.getSensorId(), in.getSensorId());
        Assert.assertEquals(out.getValue(), in.getValue(), 0.01f);
    }

    @Test
    public void testWriteReadCorrupt() {
        SensorValueMessage out = createMsg();
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
