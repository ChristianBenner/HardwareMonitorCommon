package com.bennero.common.messages.util;

import com.bennero.common.Sensor;
import com.bennero.common.Skin;
import com.bennero.common.SkinHelper;
import com.bennero.common.messages.SensorCreateMessage;

import java.util.UUID;

public class SensorCreateMessageHelper {
    // Creates a message that contains all information required to create a new sensor
    public static SensorCreateMessage create(UUID senderUuid, Sensor sensor, byte pageId) {
        final int R = 0;
        final int G = 1;
        final int B = 2;

        byte[] averageColour = sensor.getAverageColour() != null && SkinHelper.checkSupport(sensor.getSkin(), Skin.AVERAGE_COLOUR_SUPPORTED) ? Common.colorToBytes(sensor.getAverageColour()) : new byte[3];
        byte[] needleColour = sensor.getNeedleColour() != null && SkinHelper.checkSupport(sensor.getSkin(), Skin.NEEDLE_COLOUR_SUPPORTED) ? Common.colorToBytes(sensor.getNeedleColour()) : new byte[3];
        byte[] valueColour = sensor.getValueColour() != null && SkinHelper.checkSupport(sensor.getSkin(), Skin.VALUE_COLOUR_SUPPORTED) ? Common.colorToBytes(sensor.getValueColour()) : new byte[3];
        byte[] unitColour = sensor.getUnitColour() != null && SkinHelper.checkSupport(sensor.getSkin(), Skin.UNIT_COLOUR_SUPPORTED) ? Common.colorToBytes(sensor.getUnitColour()) : new byte[3];
        byte[] knobColour = sensor.getKnobColour() != null && SkinHelper.checkSupport(sensor.getSkin(), Skin.KNOB_COLOUR_SUPPORTED) ? Common.colorToBytes(sensor.getKnobColour()) : new byte[3];
        byte[] barColour = sensor.getBarColour() != null && SkinHelper.checkSupport(sensor.getSkin(), Skin.BAR_COLOUR_SUPPORTED) ? Common.colorToBytes(sensor.getBarColour()) : new byte[3];
        byte[] thresholdColour = sensor.getThresholdColour() != null && SkinHelper.checkSupport(sensor.getSkin(), Skin.THRESHOLD_COLOUR_SUPPORTED) ? Common.colorToBytes(sensor.getThresholdColour()) : new byte[3];
        byte[] titleColour = sensor.getTitleColour() != null && SkinHelper.checkSupport(sensor.getSkin(), Skin.TITLE_COLOUR_SUPPORTED) ? Common.colorToBytes(sensor.getTitleColour()) : new byte[3];
        byte[] barBackgroundColour = sensor.getBarBackgroundColour() != null && SkinHelper.checkSupport(sensor.getSkin(), Skin.BAR_BACKGROUND_COLOUR_SUPPORTED) ? Common.colorToBytes(sensor.getBarBackgroundColour()) : new byte[3];
        byte[] foregroundColour = sensor.getForegroundColour() != null && SkinHelper.checkSupport(sensor.getSkin(), Skin.FOREGROUND_BASE_COLOUR_SUPPORTED) ? Common.colorToBytes(sensor.getForegroundColour()) : new byte[3];
        byte[] tickLabelColour = sensor.getTickLabelColour() != null && SkinHelper.checkSupport(sensor.getSkin(), Skin.TICK_LABEL_COLOUR_SUPPORTED) ? Common.colorToBytes(sensor.getTickLabelColour()) : new byte[3];
        byte[] tickMarkColour = sensor.getTickMarkColour() != null && SkinHelper.checkSupport(sensor.getSkin(), Skin.TICK_MARK_COLOUR_SUPPORTED) ? Common.colorToBytes(sensor.getTickMarkColour()) : new byte[3];


        return new SensorCreateMessage(
                senderUuid, true,
                sensor.getUniqueId(),
                pageId,
                (byte)sensor.getRow(), (byte)sensor.getColumn(),
                sensor.getType(),
                sensor.getSkin(),
                sensor.getMax(),
                sensor.getThreshold(),
                sensor.isAverageEnabled(), sensor.getAveragingPeriod(),
                (byte)sensor.getRowSpan(), (byte)sensor.getColumnSpan(),
                averageColour[R], averageColour[G], averageColour[B],
                needleColour[R], needleColour[G], needleColour[B],
                valueColour[R], valueColour[G], valueColour[B],
                unitColour[R], unitColour[G], unitColour[B],
                knobColour[R], knobColour[G], knobColour[B],
                barColour[R], barColour[G], barColour[B],
                thresholdColour[R], thresholdColour[G], thresholdColour[B],
                titleColour[R], titleColour[G], titleColour[B],
                barBackgroundColour[R], barBackgroundColour[G], barBackgroundColour[B],
                foregroundColour[R], foregroundColour[G], foregroundColour[B],
                tickLabelColour[R], tickLabelColour[G], tickLabelColour[B],
                tickMarkColour[R], tickMarkColour[G], tickMarkColour[B],
                sensor.getValue(),
                sensor.getTitle());
    }
}
