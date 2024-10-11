package com.bennero.common.messages;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

public class SensorUpdateMessageTest {
    private static SensorUpdateMessage createMsg() {
        Random random = new Random(System.currentTimeMillis());

        UUID uuid = UUID.randomUUID();
        boolean ok = true;
        SensorUpdateInfo[] sensorUpdateInfo = new SensorUpdateInfo[3];
        sensorUpdateInfo[0] = new SensorUpdateInfo((byte)random.nextInt(), random.nextFloat());
        sensorUpdateInfo[1] = new SensorUpdateInfo((byte)random.nextInt(), random.nextFloat());
        sensorUpdateInfo[2] = new SensorUpdateInfo((byte)random.nextInt(), random.nextFloat());

        SensorUpdateMessage out = new SensorUpdateMessage(uuid, ok, sensorUpdateInfo);

        return out;
    }

    @Test
    public void testWriteRead() {
        SensorUpdateMessage out = createMsg();
        byte[] bytes = out.write();

        Assert.assertEquals(Message.getType(bytes), MessageType.SENSOR_UPDATE);

        SensorUpdateMessage in = new SensorUpdateMessage(bytes);

        Assert.assertEquals(out.getSenderUuid(), in.getSenderUuid());
        Assert.assertEquals(out.getStatus(), in.getStatus());

        SensorUpdateInfo[] outInfo = out.getSensorUpdates();
        Assert.assertNotNull(outInfo);

        SensorUpdateInfo[] inInfo = in.getSensorUpdates();
        Assert.assertNotNull(inInfo);

        Assert.assertEquals(outInfo.length, inInfo.length);

        for (int i = 0; i < outInfo.length; i++) {
            Assert.assertEquals(outInfo[i].getSensorId(), inInfo[i].getSensorId());
            Assert.assertEquals(outInfo[i].getValue(), inInfo[i].getValue(), 0.01f);
        }
    }

    @Test
    public void testWriteReadCorrupt() {
        SensorUpdateMessage out = createMsg();
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
