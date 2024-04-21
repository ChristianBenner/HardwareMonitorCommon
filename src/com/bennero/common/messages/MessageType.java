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

/**
 * Constants for different types of messages. Each message has a unique ID represented as a byte for efficient usage in
 * networking or other message protocols.
 *
 * @author Christian Benner
 * @version %I%, %G%
 * @since 1.0
 */
public class MessageType {
    public static final int DATA = 0x02;
    public static final int PAGE_SETUP = 0x03;
    public static final byte SENSOR_SETUP = 0x04;
    public static final byte BROADCAST_MESSAGE = 0x05;
    public static final byte BROADCAST_REPLY_MESSAGE = 0x06;
    public static final byte REMOVE_PAGE = 0x07;
    public static final byte REMOVE_SENSOR = 0x08;
    public static final byte HEARTBEAT_MESSAGE = 0x09;
    public static final byte CONNECTION_REQUEST_MESSAGE = 0x0A;
    public static final byte CONNECTION_REQUEST_RESPONSE_MESSAGE = 0x0B;
    public static final byte DISCONNECT_MESSAGE = 0x0C;
    public static final byte SENSOR_TRANSFORMATION_MESSAGE = 0x0D;
    public static final byte VERSION_PARITY_MESSAGE = 0x0E;
}
