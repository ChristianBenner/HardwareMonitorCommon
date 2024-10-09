package com.bennero.common.messages;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

public class SensorTransformationMessageTest {
    private static SensorTransformationMessage createMsg() {
        Random random = new Random(System.currentTimeMillis());

        UUID uuid = UUID.randomUUID();
        boolean ok = true;
        byte sensorId = (byte)random.nextInt();
        byte pageId = (byte)random.nextInt();
        byte row = (byte)random.nextInt();
        byte column = (byte)random.nextInt();
        byte rowSpan = (byte)random.nextInt();
        byte columnSpan = (byte)random.nextInt();

        SensorTransformationMessage out = new SensorTransformationMessage(uuid, ok, sensorId, pageId, row, column,
                rowSpan, columnSpan);

        return out;
    }

    @Test
    public void testWriteRead() {
        SensorTransformationMessage out = createMsg();
        byte[] bytes = out.write();

        Assert.assertEquals(Message.getType(bytes), MessageType.SENSOR_TRANSFORM);

        SensorTransformationMessage in = new SensorTransformationMessage(bytes);

        Assert.assertEquals(out.getSenderUuid(), in.getSenderUuid());
        Assert.assertEquals(out.getStatus(), in.getStatus());

        Assert.assertEquals(out.getSensorId(), in.getSensorId());
        Assert.assertEquals(out.getPageId(), in.getPageId());
        Assert.assertEquals(out.getRow(), in.getRow());
        Assert.assertEquals(out.getColumn(), in.getColumn());
        Assert.assertEquals(out.getRowSpan(), in.getRowSpan());
        Assert.assertEquals(out.getColumnSpan(), in.getColumnSpan());
    }

    @Test
    public void testWriteReadCorrupt() {
        SensorTransformationMessage out = createMsg();
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
