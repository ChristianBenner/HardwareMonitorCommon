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

import com.bennero.common.PageData;

import java.util.UUID;

/**
 * ConnectionRequestResponseMessage contains the data of a connection request response from a Hardware Monitor. It can
 * contain useful information such as if the connection was rejected and why, alongside the version of the hardware
 * monitor. This is another step in ensuring that an editors cannot connect to incompatible monitors.
 *
 * @author Christian Benner
 * @version %I%, %G%
 * @see PageData
 * @since 1.1
 */
public class ConnectionRequestResponseMessage extends Message {
    private final static int CURRENT_CLIENT_HOSTNAME_STR_NUM_BYTES = 64;

    private byte versionMajor;
    private byte versionMinor;
    private byte versionPatch;
    private boolean connectionAccepted;
    private boolean versionMismatch;
    private boolean currentlyInUse;
    private String currentClientHostname;

    public ConnectionRequestResponseMessage(UUID senderUuid, boolean ok, byte versionMajor, byte versionMinor,
                                            byte versionPatch, boolean connectionAccepted, boolean versionMismatch,
                                            boolean currentlyInUse, String currentClientHostname) {
        super(senderUuid, ok, MessageType.CONNECTION_REQUEST_RESPONSE);
        this.versionMajor = versionMajor;
        this.versionMinor = versionMinor;
        this.versionPatch = versionPatch;
        this.connectionAccepted = connectionAccepted;
        this.versionMismatch = versionMismatch;
        this.currentlyInUse = currentlyInUse;
        this.currentClientHostname = currentClientHostname;
    }

    public ConnectionRequestResponseMessage(byte[] bytes) {
        super(bytes);
    }

    @Override
    protected void readData() {
        versionMajor = readByte();
        versionMinor = readByte();
        versionPatch = readByte();
        connectionAccepted = readBool();
        versionMismatch = readBool();
        currentlyInUse = readBool();
        currentClientHostname = readString(CURRENT_CLIENT_HOSTNAME_STR_NUM_BYTES);
    }

    @Override
    protected void writeData() {
        writeByte(versionMajor);
        writeByte(versionMinor);
        writeByte(versionPatch);
        writeBool(connectionAccepted);
        writeBool(versionMismatch);
        writeBool(currentlyInUse);
        writeString(currentClientHostname, CURRENT_CLIENT_HOSTNAME_STR_NUM_BYTES);
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

    public boolean isConnectionAccepted() {
        return connectionAccepted;
    }

    public boolean isVersionMismatch() {
        return versionMismatch;
    }

    public boolean isCurrentlyInUse() {
        return currentlyInUse;
    }

    public String getCurrentClientHostname() {
        return currentClientHostname;
    }
}
