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
 * BroadcastAnnouncementMessage stores data from a received broadcast message such as the IP4 address that it came from.
 * A broadcast message should be sent out by a client that is looking for Hardware Monitor servers to connect to. It
 * contains the IP4 address of the broadcasting device so that the server can send a response back to it (approving or
 * denying). A BroadcastAnnouncementMessage is to be sent on a broadcast address so that all Hardware Monitor servers
 * can see it.
 *
 * @author Christian Benner
 * @version %I%, %G%
 * @since 1.1
 */
public class BroadcastAnnouncementMessage extends Message {
    private final static int IP4_ADDRESS_NUM_BYTES = 4;

    private long systemIdentifier;
    private byte[] ip4Address;

    public BroadcastAnnouncementMessage(UUID senderUuid, boolean ok, long systemIdentifier, byte[] ip4Address) {
        super(senderUuid, ok, MessageType.BROADCAST);
        this.systemIdentifier = systemIdentifier;
        this.ip4Address = ip4Address;
    }

    public BroadcastAnnouncementMessage(byte[] bytes) {
        super(bytes);
    }

    @Override
    protected void readData() {
        systemIdentifier = readLong();
        ip4Address = readBytes(IP4_ADDRESS_NUM_BYTES);
    }

    @Override
    protected void writeData() {
        writeLong(systemIdentifier);
        writeBytes(ip4Address, IP4_ADDRESS_NUM_BYTES);
    }

    public long getSystemIdentifier() {
        return systemIdentifier;
    }

    public byte[] getIp4Address() {
        return ip4Address;
    }
}
