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
 * Contains constants that are used by both editor and monitor.
 *
 * @author      Christian Benner
 * @version     %I%, %G%
 * @since       1.0
 */
public class Constants
{
    public static final int SENSOR_POLL_RATE_MS = 1500;

    public static final int EXIT_ERROR_CODE_NATIVE_SENSOR_UPDATE_FAILED = 1;
    public static final int EXIT_ERROR_CODE_NATIVE_GET_SENSOR_FAILED = 2;

    public final static int MESSAGE_NUM_BYTES = 256;

    // Message type is only one byte 0-255
    public final static int MESSAGE_TYPE_POS = 0;

    public final static int PORT = 5392;
    public final static int BROADCAST_REPLY_PORT = 5393;
    public final static int BROADCAST_RECEIVE_PORT = 5393;
    public final static int HEARTBEAT_PORT = 5394;

    public final static int TEXT_ALIGNMENT_LEFT = 0;
    public final static int TEXT_ALIGNMENT_CENTER = 1;
    public final static int TEXT_ALIGNMENT_RIGHT = 2;

    public final static int NAME_STRING_NUM_BYTES = 64;
    public final static int MAC_ADDRESS_NUM_BYTES = 6;
    public final static int IP4_ADDRESS_NUM_BYTES = 4;

    public final static long HW_EDITOR_SYSTEM_UNIQUE_CONNECTION_ID = 2068354079750733463L;
    public final static long HW_MONITOR_SYSTEM_UNIQUE_CONNECTION_ID = 7229626662156094589L;

    public final static int HW_HEARTBEAT_VALIDATION_NUMBER_POS = 1;
    public final static long HW_HEARTBEAT_VALIDATION_NUMBER = 2703806547261752716L;

    public final static int HEARTBEAT_TIMEOUT_MS = 10000;
    public final static int HEARTBEAT_FREQUENCY_MS = 1000;
}
