package com.bennero.common.messages;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

public class SensorCreateMessageTest {
    private static SensorCreateMessage createMsg() {
        Random random = new Random(System.currentTimeMillis());

        UUID uuid = UUID.randomUUID();
        boolean ok = true;
        byte sensorId = (byte)random.nextInt();
        byte pageId = (byte)random.nextInt();
        byte row = (byte)random.nextInt();
        byte column = (byte)random.nextInt();
        byte type = (byte)random.nextInt();
        byte skin = (byte)random.nextInt();
        float max = (byte)random.nextInt();
        float threshold = (byte)random.nextInt();
        boolean averageEnabled = random.nextBoolean();
        int averagingPeriodMs = random.nextInt();
        byte rowSpan = (byte)random.nextInt();
        byte columnSpan = (byte)random.nextInt();
        byte averageColourR = (byte)random.nextInt();
        byte averageColourG = (byte)random.nextInt();
        byte averageColourB = (byte)random.nextInt();
        byte needleColourR = (byte)random.nextInt();
        byte needleColourG = (byte)random.nextInt();
        byte needleColourB = (byte)random.nextInt();
        byte valueColourR = (byte)random.nextInt();
        byte valueColourG = (byte)random.nextInt();
        byte valueColourB = (byte)random.nextInt();
        byte unitColourR = (byte)random.nextInt();
        byte unitColourG = (byte)random.nextInt();
        byte unitColourB = (byte)random.nextInt();
        byte knobColourR = (byte)random.nextInt();
        byte knobColourG = (byte)random.nextInt();
        byte knobColourB = (byte)random.nextInt();
        byte barColourR = (byte)random.nextInt();
        byte barColourG = (byte)random.nextInt();
        byte barColourB = (byte)random.nextInt();
        byte thresholdColourR = (byte)random.nextInt();
        byte thresholdColourG = (byte)random.nextInt();
        byte thresholdColourB = (byte)random.nextInt();
        byte titleColourR = (byte)random.nextInt();
        byte titleColourG = (byte)random.nextInt();
        byte titleColourB = (byte)random.nextInt();
        byte barBackgroundColourR = (byte)random.nextInt();
        byte barBackgroundColourG = (byte)random.nextInt();
        byte barBackgroundColourB = (byte)random.nextInt();
        byte foregroundColourR = (byte)random.nextInt();
        byte foregroundColourG = (byte)random.nextInt();
        byte foregroundColourB = (byte)random.nextInt();
        byte tickLabelColourR = (byte)random.nextInt();
        byte tickLabelColourG = (byte)random.nextInt();
        byte tickLabelColourB = (byte)random.nextInt();
        byte tickMarkColourR = (byte)random.nextInt();
        byte tickMarkColourG = (byte)random.nextInt();
        byte tickMarkColourB = (byte)random.nextInt();
        float initialValue = random.nextFloat();
        String title = "titletest";

        SensorCreateMessage out = new SensorCreateMessage(uuid, ok, sensorId, pageId, row, column, type, skin, max, threshold,
                averageEnabled, averagingPeriodMs, rowSpan, columnSpan, averageColourR, averageColourG, averageColourB,
                needleColourR, needleColourG, needleColourB, valueColourR, valueColourG, valueColourB, unitColourR,
                unitColourG, unitColourB, knobColourR, knobColourG, knobColourB, barColourR, barColourG, barColourB,
                thresholdColourR, thresholdColourG, thresholdColourB, titleColourR, titleColourG, titleColourB,
                barBackgroundColourR, barBackgroundColourG, barBackgroundColourB, foregroundColourR, foregroundColourG,
                foregroundColourB, tickLabelColourR, tickLabelColourG, tickLabelColourB, tickMarkColourR,
                tickMarkColourG, tickMarkColourB, initialValue, title);

        return out;
    }

    @Test
    public void testWriteRead() {
        SensorCreateMessage out = createMsg();
        byte[] bytes = out.write();

        Assert.assertEquals(Message.getType(bytes), MessageType.SENSOR_CREATE);

        SensorCreateMessage in = new SensorCreateMessage(bytes);

        Assert.assertEquals(out.getSenderUuid(), in.getSenderUuid());
        Assert.assertEquals(out.getStatus(), in.getStatus());
        Assert.assertEquals(out.getSensorId(), in.getSensorId());
        Assert.assertEquals(out.getPageId(), in.getPageId());

        Assert.assertEquals(out.getRow(), in.getRow());
        Assert.assertEquals(out.getColumn(), in.getColumn());
        Assert.assertEquals(out.getType(), in.getType());
        Assert.assertEquals(out.getSkin(), in.getSkin());
        Assert.assertEquals(out.getMax(), in.getMax(), 0.01f);
        Assert.assertEquals(out.getThreshold(), in.getThreshold(), 0.01f);
        Assert.assertEquals(out.isAverageEnabled(), in.isAverageEnabled());
        Assert.assertEquals(out.getAveragingPeriodMs(), in.getAveragingPeriodMs());
        Assert.assertEquals(out.getRowSpan(), in.getRowSpan());
        Assert.assertEquals(out.getColumnSpan(), in.getColumnSpan());
        Assert.assertEquals(out.getAverageColourR(), in.getAverageColourR());
        Assert.assertEquals(out.getAverageColourG(), in.getAverageColourG());
        Assert.assertEquals(out.getAverageColourB(), in.getAverageColourB());
        Assert.assertEquals(out.getNeedleColourR(), in.getNeedleColourR());
        Assert.assertEquals(out.getNeedleColourG(), in.getNeedleColourG());
        Assert.assertEquals(out.getNeedleColourB(), in.getNeedleColourB());
        Assert.assertEquals(out.getValueColourR(), in.getValueColourR());
        Assert.assertEquals(out.getValueColourG(), in.getValueColourG());
        Assert.assertEquals(out.getValueColourB(), in.getValueColourB());
        Assert.assertEquals(out.getUnitColourR(), in.getUnitColourR());
        Assert.assertEquals(out.getUnitColourG(), in.getUnitColourG());
        Assert.assertEquals(out.getUnitColourB(), in.getUnitColourB());
        Assert.assertEquals(out.getKnobColourR(), in.getKnobColourR());
        Assert.assertEquals(out.getKnobColourG(), in.getKnobColourG());
        Assert.assertEquals(out.getKnobColourB(), in.getKnobColourB());
        Assert.assertEquals(out.getBarColourR(), in.getBarColourR());
        Assert.assertEquals(out.getBarColourG(), in.getBarColourG());
        Assert.assertEquals(out.getBarColourB(), in.getBarColourB());
        Assert.assertEquals(out.getThresholdColourR(), in.getThresholdColourR());
        Assert.assertEquals(out.getThresholdColourG(), in.getThresholdColourG());
        Assert.assertEquals(out.getThresholdColourB(), in.getThresholdColourB());
        Assert.assertEquals(out.getTitleColourR(), in.getTitleColourR());
        Assert.assertEquals(out.getTitleColourG(), in.getTitleColourG());
        Assert.assertEquals(out.getTitleColourB(), in.getTitleColourB());
        Assert.assertEquals(out.getBarBackgroundColourR(), in.getBarBackgroundColourR());
        Assert.assertEquals(out.getBarBackgroundColourG(), in.getBarBackgroundColourG());
        Assert.assertEquals(out.getBarBackgroundColourB(), in.getBarBackgroundColourB());
        Assert.assertEquals(out.getForegroundColourR(), in.getForegroundColourR());
        Assert.assertEquals(out.getForegroundColourG(), in.getForegroundColourG());
        Assert.assertEquals(out.getForegroundColourB(), in.getForegroundColourB());
        Assert.assertEquals(out.getTickLabelColourR(), in.getTickLabelColourR());
        Assert.assertEquals(out.getTickLabelColourG(), in.getTickLabelColourG());
        Assert.assertEquals(out.getTickLabelColourB(), in.getTickLabelColourB());
        Assert.assertEquals(out.getTickMarkColourR(), in.getTickMarkColourR());
        Assert.assertEquals(out.getTickMarkColourG(), in.getTickMarkColourG());
        Assert.assertEquals(out.getTickMarkColourB(), in.getTickMarkColourB());
        Assert.assertEquals(out.getInitialValue(), in.getInitialValue(), 0.01f);
    }

    @Test
    public void testWriteReadCorrupt() {
        SensorCreateMessage out = createMsg();
        byte[] bytes = out.write();

        Random rand = new Random(System.currentTimeMillis());

        // Randomly corrupt a byte at every position to a different value for the length of the message
        for (int i = 0; i < Message.NUM_BYTES; i++) {
            bytes[i] += rand.nextInt(254); // ensure the byte will be a random value except for its current value

            SensorCreateMessage in = new SensorCreateMessage(bytes);
            Assert.assertEquals(Message.STATUS_FAILED_READ, in.getStatus());
        }
    }
}
