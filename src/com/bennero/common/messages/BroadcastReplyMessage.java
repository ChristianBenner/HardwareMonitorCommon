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

import com.bennero.common.Constants;

import java.util.UUID;

/**
 * Todo
 *
 * @author Christian Benner
 * @version %I%, %G%
 * @since 1.0
 */
public class BroadcastReplyMessage extends Message {
    private final static int CURRENT_CLIENT_HOSTNAME_STR_NUM_BYTES = 64;

    private long systemIdentifier;
    private byte versionMajor;
    private byte versionMinor;
    private byte versionPatch;
    private byte[] macAddress;
    private byte[] ip4Address;
    private String hostName;

    public BroadcastReplyMessage(UUID senderUuid, boolean ok, long systemIdentifier, byte majorVersion,
                                 byte minorVersion, byte patchVersion, byte[] macAddress, byte[] ip4Address,
                                 String hostName) {
        super(senderUuid, ok, MessageType.BROADCAST_REPLY);
        this.systemIdentifier = systemIdentifier;
        this.versionMajor = majorVersion;
        this.versionMinor = minorVersion;
        this.versionPatch = patchVersion;
        this.macAddress = macAddress;
        this.ip4Address = ip4Address;
        this.hostName = hostName;
    }

    @Override
    protected void readData() {
        systemIdentifier = readLong();
        versionMajor = readByte();
        versionMinor = readByte();
        versionPatch = readByte();
        macAddress = readBytes(Constants.MAC_ADDRESS_NUM_BYTES);
        ip4Address = readBytes(Constants.IP4_ADDRESS_NUM_BYTES);
        hostName = readString(CURRENT_CLIENT_HOSTNAME_STR_NUM_BYTES);
    }

    @Override
    protected void writeData() {
        writeLong(systemIdentifier);
        writeByte(versionMajor);
        writeByte(versionMinor);
        writeByte(versionPatch);
        writeBytes(macAddress, Constants.MAC_ADDRESS_NUM_BYTES);
        writeBytes(ip4Address, Constants.IP4_ADDRESS_NUM_BYTES);
        writeString(hostName, CURRENT_CLIENT_HOSTNAME_STR_NUM_BYTES);
    }

    public BroadcastReplyMessage(byte[] bytes) {
        super(bytes);
    }

    public long getSystemIdentifier() {
        return systemIdentifier;
    }

    public byte getVersionMajor() {
        return versionMajor;
    }

    public byte getVersionMinor() {
        return versionMinor;
    }

    public byte getVersionPatch() {
        return versionPatch;
    }

    public byte[] getMacAddress() {
        return macAddress;
    }

    public byte[] getIp4Address() {
        return ip4Address;
    }

    public String getHostName() {
        return hostName;
    }
}
