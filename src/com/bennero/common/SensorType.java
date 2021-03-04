/*
 * ============================================ GNU GENERAL PUBLIC LICENSE =============================================
 * Hardware Monitor for the remote monitoring of a systems hardware information
 * Copyright (C) 2021  Christian Benner
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * An additional term included with this license is the requirement to preserve legal notices and author attributions
 * such as this one. Do not remove the original author license notices from the program unless given permission from
 * the original author: christianbenner35@gmail.com
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <https://www.gnu.org/licenses/>.
 * =====================================================================================================================
 */

package com.bennero.common;

/**
 * Constants for different types of hardware sensors. Each Hardware Sensor type has a unique ID represented as a byte
 * for efficient usage in networking or other message protocols.
 *
 * @author      Christian Benner
 * @version     %I%, %G%
 * @since       1.0
 */
public class SensorType
{
    public final static byte VOLTAGE = 0x01;
    public final static byte CLOCK = 0x02;
    public final static byte TEMPERATURE = 0x03;
    public final static byte LOAD = 0x04;
    public final static byte FAN = 0x05;
    public final static byte FLOW = 0x06;
    public final static byte CONTROL = 0x07;
    public final static byte LEVEL = 0x08;
    public final static byte FACTOR = 0x09;
    public final static byte POWER = 0x0A;
    public final static byte DATA = 0x0B;
    public final static byte SMALL_DATA = 0x0C;
    public final static byte THROUGHPUT = 0x0D;

    public static String getSuffix(final byte sensorType)
    {
        switch (sensorType)
        {
            case VOLTAGE:
                return "V";
            case CLOCK:
                return "MHz";
            case TEMPERATURE:
                return "°C";
            case FAN:
                return "RPM";
            case FLOW:
                return "L/h";
            case LOAD:
            case CONTROL:
            case LEVEL:
                return "%";
            case POWER:
                return "W";
            case DATA:
                return "GB";
            case SMALL_DATA:
                return "MB";
            case THROUGHPUT:
                return "MB/s";
        }

        return "";
    }
}