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
 * SensorValueMessage stores the data of a sensor update. It is not page dependent (any time the sensor with the given
 * ID is updated it is updated across all pages). The message is sent by a connected client only and contains the new
 * value for the sensor, and the ID of the sensor.
 *
 * @author Christian Benner
 * @version %I%, %G%
 * @since 1.1
 */
public class SensorValueMessage extends Message {
    // todo: could this message have many sensor data in one with a terminator ID (255?)

    private byte sensorId;
    private float value;

    public SensorValueMessage(UUID senderUuid, boolean ok, byte sensorId, float value) {
        super(senderUuid, ok, MessageType.SENSOR_UPDATE);
        this.sensorId = sensorId;
        this.value = value;
    }

    public SensorValueMessage(byte[] bytes) {
        super(bytes);
    }

    @Override
    protected void readData() {
        sensorId = readByte();
        value = readFloat();
    }

    @Override
    protected void writeData() {
        writeByte(sensorId);
        writeFloat(value);
    }

    public byte getSensorId() {
        return sensorId;
    }

    public float getValue() {
        return value;
    }
}
