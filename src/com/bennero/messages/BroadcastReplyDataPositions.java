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

package com.bennero.messages;

/**
 * Defines a broadcast reply messages data structure (position of each value within the message data)
 *
 * @author      Christian Benner
 * @version     %I%, %G%
 * @since       1.0
 */
public class BroadcastReplyDataPositions
{
    public final static int HW_SYSTEM_IDENTIFIER_POS = 1;   // long (8 bytes)
    public final static int MAJOR_VERSION_POS = 9;          // 1 byte
    public final static int MINOR_VERSION_POS = 10;         // 1 byte
    public final static int PATCH_VERSION_POS = 11;         // 1 byte
    public final static int MAC_ADDRESS_POS = 12;           // 6 bytes
    public final static int IP4_ADDRESS_POS = 18;           // 4 bytes
    public final static int HOSTNAME_POS = 22;              // 64 bytes
    public final static int CURRENTLY_IN_USE = 86;           // 1 byte
    public final static int CURRENT_CLIENT_HOSTNAME = 87;    // 64 bytes
}
