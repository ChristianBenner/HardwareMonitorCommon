/*
 * ============================================ GNU GENERAL PUBLIC LICENSE =============================================
 * Hardware Monitor for the remote monitoring of a systems hardware information
 * Copyright (C) 2021  Christian Benner
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * Additional terms included with this license are to:
 * - Preserve legal notices and author attributions such as this one. Do not remove the original author license notices
 *   from the program
 * - Preserve the donation button and its link to the original authors donation page (christianbenner35@gmail.com)
 * - Only break the terms if given permission from the original author christianbenner35@gmail.com
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <https://www.gnu.org/licenses/>.
 * =====================================================================================================================
 */

package com.bennero.common.messages;

import java.util.UUID;

/**
 * SensorCreateMessage stores the data of a sensor creation request. The SensorCreateMessage is sent by a connected
 * client only. The sensor will be created at a specified location of a specified page. If a sensor already exists on
 * the same page, it will be updated with the new information (allowing the change of attributes such as name, position,
 * max value, threshold values, row span and column span).
 *
 * @author Christian Benner
 * @version %I%, %G%
 * @since 1.1
 */
public class SensorCreateMessage extends Message {
    private static final int TITLE_STR_NUM_BYTES = 64;

    private byte sensorId;
    private byte pageId;
    private byte row;
    private byte column;
    private byte type;
    private byte skin;
    private float max;
    private float threshold;
    private boolean averageEnabled;
    private int averagingPeriodMs;
    private byte rowSpan;
    private byte columnSpan;
    private byte averageColourR;
    private byte averageColourG;
    private byte averageColourB;
    private byte needleColourR;
    private byte needleColourG;
    private byte needleColourB;
    private byte valueColourR;
    private byte valueColourG;
    private byte valueColourB;
    private byte unitColourR;
    private byte unitColourG;
    private byte unitColourB;
    private byte knobColourR;
    private byte knobColourG;
    private byte knobColourB;
    private byte barColourR;
    private byte barColourG;
    private byte barColourB;
    private byte thresholdColourR;
    private byte thresholdColourG;
    private byte thresholdColourB;
    private byte titleColourR;
    private byte titleColourG;
    private byte titleColourB;
    private byte barBackgroundColourR;
    private byte barBackgroundColourG;
    private byte barBackgroundColourB;
    private byte foregroundColourR;
    private byte foregroundColourG;
    private byte foregroundColourB;
    private byte tickLabelColourR;
    private byte tickLabelColourG;
    private byte tickLabelColourB;
    private byte tickMarkColourR;
    private byte tickMarkColourG;
    private byte tickMarkColourB;
    private float initialValue;
    private String title;

    public SensorCreateMessage(UUID senderUuid, boolean ok, byte sensorId, byte pageId, byte row, byte column, byte type,
                               byte skin, float max, float threshold, boolean averageEnabled, int averagingPeriodMs,
                               byte rowSpan, byte columnSpan, byte averageColourR, byte averageColourG,
                               byte averageColourB, byte needleColourR, byte needleColourG, byte needleColourB,
                               byte valueColourR, byte valueColourG, byte valueColourB, byte unitColourR, byte unitColourG,
                               byte unitColourB, byte knobColourR, byte knobColourG, byte knobColourB, byte barColourR,
                               byte barColourG, byte barColourB, byte thresholdColourR, byte thresholdColourG,
                               byte thresholdColourB, byte titleColourR, byte titleColourG, byte titleColourB,
                               byte barBackgroundColourR, byte barBackgroundColourG, byte barBackgroundColourB,
                               byte foregroundColourR, byte foregroundColourG, byte foregroundColourB, byte tickLabelColourR,
                               byte tickLabelColourG, byte tickLabelColourB, byte tickMarkColourR, byte tickMarkColourG,
                               byte tickMarkColourB, float initialValue, String title) {
        super(senderUuid, ok, MessageType.SENSOR_CREATE);
        this.sensorId = sensorId;
        this.pageId = pageId;
        this.row = row;
        this.column = column;
        this.type = type;
        this.skin = skin;
        this.max = max;
        this.threshold = threshold;
        this.averageEnabled = averageEnabled;
        this.averagingPeriodMs = averagingPeriodMs;
        this.rowSpan = rowSpan;
        this.columnSpan = columnSpan;
        this.averageColourR = averageColourR;
        this.averageColourG = averageColourG;
        this.averageColourB = averageColourB;
        this.needleColourR = needleColourR;
        this.needleColourG = needleColourG;
        this.needleColourB = needleColourB;
        this.valueColourR = valueColourR;
        this.valueColourG = valueColourG;
        this.valueColourB = valueColourB;
        this.unitColourR = unitColourR;
        this.unitColourG = unitColourG;
        this.unitColourB = unitColourB;
        this.knobColourR = knobColourR;
        this.knobColourG = knobColourG;
        this.knobColourB = knobColourB;
        this.barColourR = barColourR;
        this.barColourG = barColourG;
        this.barColourB = barColourB;
        this.thresholdColourR = thresholdColourR;
        this.thresholdColourG = thresholdColourG;
        this.thresholdColourB = thresholdColourB;
        this.titleColourR = titleColourR;
        this.titleColourG = titleColourG;
        this.titleColourB = titleColourB;
        this.barBackgroundColourR = barBackgroundColourR;
        this.barBackgroundColourG = barBackgroundColourG;
        this.barBackgroundColourB = barBackgroundColourB;
        this.foregroundColourR = foregroundColourR;
        this.foregroundColourG = foregroundColourG;
        this.foregroundColourB = foregroundColourB;
        this.tickLabelColourR = tickLabelColourR;
        this.tickLabelColourG = tickLabelColourG;
        this.tickLabelColourB = tickLabelColourB;
        this.tickMarkColourR = tickMarkColourR;
        this.tickMarkColourG = tickMarkColourG;
        this.tickMarkColourB = tickMarkColourB;
        this.initialValue = initialValue;
        this.title = title;
    }

    public SensorCreateMessage(byte[] bytes) {
        super(bytes);
    }

    @Override
    protected void readData() {
        sensorId = readByte();
        pageId = readByte();
        row = readByte();
        column = readByte();
        type = readByte();
        skin = readByte();
        max = readFloat();
        threshold = readFloat();
        averageEnabled = readBool();
        averagingPeriodMs = readInt();
        rowSpan = readByte();
        columnSpan = readByte();
        averageColourR = readByte();
        averageColourG = readByte();
        averageColourB = readByte();
        needleColourR = readByte();
        needleColourG = readByte();
        needleColourB = readByte();
        valueColourR = readByte();
        valueColourG = readByte();
        valueColourB = readByte();
        unitColourR = readByte();
        unitColourG = readByte();
        unitColourB = readByte();
        knobColourR = readByte();
        knobColourG = readByte();
        knobColourB = readByte();
        barColourR = readByte();
        barColourG = readByte();
        barColourB = readByte();
        thresholdColourR = readByte();
        thresholdColourG = readByte();
        thresholdColourB = readByte();
        titleColourR = readByte();
        titleColourG = readByte();
        titleColourB = readByte();
        barBackgroundColourR = readByte();
        barBackgroundColourG = readByte();
        barBackgroundColourB = readByte();
        foregroundColourR = readByte();
        foregroundColourG = readByte();
        foregroundColourB = readByte();
        tickLabelColourR = readByte();
        tickLabelColourG = readByte();
        tickLabelColourB = readByte();
        tickMarkColourR = readByte();
        tickMarkColourG = readByte();
        tickMarkColourB = readByte();
        initialValue = readFloat();
        title = readString(TITLE_STR_NUM_BYTES);
    }

    @Override
    protected void writeData() {
        writeByte(sensorId);
        writeByte(pageId);
        writeByte(row);
        writeByte(column);
        writeByte(type);
        writeByte(skin);
        writeFloat(max);
        writeFloat(threshold);
        writeBool(averageEnabled);
        writeInt(averagingPeriodMs);
        writeByte(rowSpan);
        writeByte(columnSpan);
        writeByte(averageColourR);
        writeByte(averageColourG);
        writeByte(averageColourB);
        writeByte(needleColourR);
        writeByte(needleColourG);
        writeByte(needleColourB);
        writeByte(valueColourR);
        writeByte(valueColourG);
        writeByte(valueColourB);
        writeByte(unitColourR);
        writeByte(unitColourG);
        writeByte(unitColourB);
        writeByte(knobColourR);
        writeByte(knobColourG);
        writeByte(knobColourB);
        writeByte(barColourR);
        writeByte(barColourG);
        writeByte(barColourB);
        writeByte(thresholdColourR);
        writeByte(thresholdColourG);
        writeByte(thresholdColourB);
        writeByte(titleColourR);
        writeByte(titleColourG);
        writeByte(titleColourB);
        writeByte(barBackgroundColourR);
        writeByte(barBackgroundColourG);
        writeByte(barBackgroundColourB);
        writeByte(foregroundColourR);
        writeByte(foregroundColourG);
        writeByte(foregroundColourB);
        writeByte(tickLabelColourR);
        writeByte(tickLabelColourG);
        writeByte(tickLabelColourB);
        writeByte(tickMarkColourR);
        writeByte(tickMarkColourG);
        writeByte(tickMarkColourB);
        writeFloat(initialValue);
        writeString(title, TITLE_STR_NUM_BYTES);
    }

    public byte getSensorId() {
        return sensorId;
    }

    public byte getPageId() {
        return pageId;
    }

    public byte getRow() {
        return row;
    }

    public byte getColumn() {
        return column;
    }

    public byte getType() {
        return type;
    }

    public byte getSkin() {
        return skin;
    }

    public float getMax() {
        return max;
    }

    public float getThreshold() {
        return threshold;
    }

    public boolean isAverageEnabled() {
        return averageEnabled;
    }

    public int getAveragingPeriodMs() {
        return averagingPeriodMs;
    }

    public byte getRowSpan() {
        return rowSpan;
    }

    public byte getColumnSpan() {
        return columnSpan;
    }

    public byte getAverageColourR() {
        return averageColourR;
    }

    public byte getAverageColourG() {
        return averageColourG;
    }

    public byte getAverageColourB() {
        return averageColourB;
    }

    public byte getNeedleColourR() {
        return needleColourR;
    }

    public byte getNeedleColourG() {
        return needleColourG;
    }

    public byte getNeedleColourB() {
        return needleColourB;
    }

    public byte getValueColourR() {
        return valueColourR;
    }

    public byte getValueColourG() {
        return valueColourG;
    }

    public byte getValueColourB() {
        return valueColourB;
    }

    public byte getUnitColourR() {
        return unitColourR;
    }

    public byte getUnitColourG() {
        return unitColourG;
    }

    public byte getUnitColourB() {
        return unitColourB;
    }

    public byte getKnobColourR() {
        return knobColourR;
    }

    public byte getKnobColourG() {
        return knobColourG;
    }

    public byte getKnobColourB() {
        return knobColourB;
    }

    public byte getBarColourR() {
        return barColourR;
    }

    public byte getBarColourG() {
        return barColourG;
    }

    public byte getBarColourB() {
        return barColourB;
    }

    public byte getThresholdColourR() {
        return thresholdColourR;
    }

    public byte getThresholdColourG() {
        return thresholdColourG;
    }

    public byte getThresholdColourB() {
        return thresholdColourB;
    }

    public byte getTitleColourR() {
        return titleColourR;
    }

    public byte getTitleColourG() {
        return titleColourG;
    }

    public byte getTitleColourB() {
        return titleColourB;
    }

    public byte getBarBackgroundColourR() {
        return barBackgroundColourR;
    }

    public byte getBarBackgroundColourG() {
        return barBackgroundColourG;
    }

    public byte getBarBackgroundColourB() {
        return barBackgroundColourB;
    }

    public byte getForegroundColourR() {
        return foregroundColourR;
    }

    public byte getForegroundColourG() {
        return foregroundColourG;
    }

    public byte getForegroundColourB() {
        return foregroundColourB;
    }

    public byte getTickLabelColourR() {
        return tickLabelColourR;
    }

    public byte getTickLabelColourG() {
        return tickLabelColourG;
    }

    public byte getTickLabelColourB() {
        return tickLabelColourB;
    }

    public byte getTickMarkColourR() {
        return tickMarkColourR;
    }

    public byte getTickMarkColourG() {
        return tickMarkColourG;
    }

    public byte getTickMarkColourB() {
        return tickMarkColourB;
    }

    public float getInitialValue() {
        return initialValue;
    }

    public String getTitle() {
        return title;
    }
}
