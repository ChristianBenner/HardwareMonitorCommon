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

import java.util.HashMap;
import java.util.Map;

/**
 * Constants for different types of messages. Each message has a unique ID represented as a byte for efficient usage in
 * networking or other message protocols.
 *
 * @author Christian Benner
 * @version %I%, %G%
 * @since 1.0
 */
public class MessageType {
    public static final byte SENSOR_UPDATE = 0x02;
    public static final byte PAGE_CREATE = 0x03;
    public static final byte SENSOR_CREATE = 0x04;
    public static final byte BROADCAST = 0x05;
    public static final byte BROADCAST_REPLY = 0x06;
    public static final byte PAGE_REMOVE = 0x07;
    public static final byte SENSOR_REMOVE = 0x08;
    public static final byte HEARTBEAT = 0x09;
    public static final byte CONNECTION_REQUEST = 0x0A;
    public static final byte CONNECTION_REQUEST_RESPONSE = 0x0B;
    public static final byte DISCONNECT = 0x0C;
    public static final byte SENSOR_TRANSFORM = 0x0D;
    public static final byte VERSION_PARITY = 0x0E;
    public static final byte VERSION_PARITY_RESPONSE = 0x0F;
    public static final byte FILE_TRANSFER = 0x10;
    public static final byte CONFIRMATION = 0x11;

    public static String asString(byte type) {
        switch (type) {
            case SENSOR_UPDATE: return "SENSOR_UPDATE";
            case PAGE_CREATE: return "PAGE_CREATE";
            case SENSOR_CREATE: return "SENSOR_CREATE";
            case BROADCAST: return "BROADCAST";
            case BROADCAST_REPLY: return "BROADCAST_REPLY";
            case PAGE_REMOVE: return "PAGE_REMOVE";
            case SENSOR_REMOVE: return "SENSOR_REMOVE";
            case HEARTBEAT: return "HEARTBEAT";
            case CONNECTION_REQUEST: return "CONNECTION_REQUEST";
            case CONNECTION_REQUEST_RESPONSE: return "CONNECTION_REQUEST_RESPONSE";
            case DISCONNECT: return "DISCONNECT";
            case SENSOR_TRANSFORM: return "SENSOR_TRANSFORM";
            case VERSION_PARITY: return "VERSION_PARITY";
            case VERSION_PARITY_RESPONSE: return "VERSION_PARITY_RESPONSE";
            case FILE_TRANSFER: return "FILE_TRANSFER";
            case CONFIRMATION: return "CONFIRMATION";
            default: return "UNDEFINED";
        }
    }
}
