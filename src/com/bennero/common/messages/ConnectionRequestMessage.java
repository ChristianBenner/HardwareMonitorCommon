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
 * ConnectionRequestMessage stores the data associated to a connection request sent by a client. This includes the
 * version of the client, its IP4 address (so that the server can communicate with it on its approval/rejection of
 * connection), and its hostname.
 *
 * @author Christian Benner
 * @version %I%, %G%
 * @since 1.0
 */
public class ConnectionRequestMessage extends Message {
    private final static int NAME_STRING_NUM_BYTES = 64;

    private byte versionMajor;
    private byte versionMinor;
    private byte versionPatch;
    private boolean forceConnection;
    private byte[] ip4Address;
    private String hostname;

    public ConnectionRequestMessage(UUID senderUuid, boolean ok, byte versionMajor, byte versionMinor,
                                    byte versionPatch, boolean forceConnection, byte[] ip4Address, String hostname) {
        super(senderUuid, ok, MessageType.CONNECTION_REQUEST);
        this.versionMajor = versionMajor;
        this.versionMinor = versionMinor;
        this.versionPatch = versionPatch;
        this.forceConnection = forceConnection;
        this.ip4Address = ip4Address;
        this.hostname = hostname;
    }

    public ConnectionRequestMessage(byte[] bytes) {
        super(bytes);
    }

    @Override
    protected void readData() {
        versionMajor = readByte();
        versionMinor = readByte();
        versionPatch = readByte();
        forceConnection = readBool();
        ip4Address = readBytes(Constants.IP4_ADDRESS_NUM_BYTES);
        hostname = readString(NAME_STRING_NUM_BYTES);
    }

    @Override
    protected void writeData() {
        writeByte(versionMajor);
        writeByte(versionMinor);
        writeByte(versionPatch);
        writeBool(forceConnection);
        writeBytes(ip4Address, Constants.IP4_ADDRESS_NUM_BYTES);
        writeString(hostname, NAME_STRING_NUM_BYTES);
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

    public boolean isForceConnection() {
        return forceConnection;
    }

    public byte[] getIp4Address() {
        return ip4Address;
    }

    public String getHostname() {
        return hostname;
    }
}
