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
 * SensorTransformationMessage stores the data of a sensor relocation request. The SensorTransformationMessage is sent
 * by a connected client only. A given existing sensor will be relocated to a stated position on a specified page. The
 * message also includes information for the row and column spans allowing it to control the size.
 *
 * @author Christian Benner
 * @version %I%, %G%
 * @since 1.1
 */
public class SensorTransformationMessage extends Message {
    private byte sensorId;
    private byte pageId;
    private byte row;
    private byte column;
    private byte rowSpan;
    private byte columnSpan;

    public SensorTransformationMessage(UUID sender, boolean ok, byte sensorId, byte pageId, byte row, byte column,
                                       byte rowSpan, byte columnSpan) {
        super(sender, ok, MessageType.SENSOR_TRANSFORM);
        this.sensorId = sensorId;
        this.pageId = pageId;
        this.row = row;
        this.column = column;
        this.rowSpan = rowSpan;
        this.columnSpan = columnSpan;
    }

    public SensorTransformationMessage(byte[] bytes) {
        super(bytes);
    }

    @Override
    protected void readData() {
        sensorId = readByte();
        pageId = readByte();
        row = readByte();
        column = readByte();
        rowSpan = readByte();
        columnSpan = readByte();
    }

    @Override
    protected void writeData() {
        writeByte(sensorId);
        writeByte(pageId);
        writeByte(row);
        writeByte(column);
        writeByte(rowSpan);
        writeByte(columnSpan);
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

    public byte getRowSpan() {
        return rowSpan;
    }

    public byte getColumnSpan() {
        return columnSpan;
    }
}
