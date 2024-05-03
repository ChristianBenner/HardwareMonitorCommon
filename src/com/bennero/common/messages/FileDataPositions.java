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
 * Defines a connection request messages data structure (position of each value within the message data)
 *
 * @author Christian Benner
 * @version %I%, %G%
 * @since 1.0
 */
public class FileDataPositions {
    public final static byte TYPE_IMAGE = 0x01;
    public final static byte TYPE_SOFTWARE_UPDATE = 0x02;

    public final static int SIZE_POS = 1;   // 4 bytes
    public final static int TYPE_POS = 5;   // 1 byte
    public final static int NAME_POS = 6;   // 48 bytes (36 for the UUID and 12 spare for extension)
}
