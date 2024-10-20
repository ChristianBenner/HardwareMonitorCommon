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
 * todo
 *
 * @author Christian Benner
 * @version %I%, %G%
 * @since 1.0
 */
public class VersionParityResponseMessage extends Message {
    private static final int REJECTION_REASON_STR_NUM_BYTES = 96;

    private byte versionMajor;
    private byte versionMinor;
    private byte versionPatch;
    private boolean accepted;
    private String rejectionReason;

    public VersionParityResponseMessage(UUID senderUuid, boolean ok, byte versionMajor, byte versionMinor,
                                        byte versionPatch, boolean accepted, String rejectionReason) {
        super(senderUuid, ok, MessageType.VERSION_PARITY_RESPONSE);
        this.versionMajor = versionMajor;
        this.versionMinor = versionMinor;
        this.versionPatch = versionPatch;
        this.accepted = accepted;
        this.rejectionReason = rejectionReason;
    }

    public VersionParityResponseMessage(byte[] bytes) {
        super(bytes);
    }

    @Override
    protected void readData() {
        versionMajor = readByte();
        versionMinor = readByte();
        versionPatch = readByte();
        accepted = readBool();
        rejectionReason = readString(REJECTION_REASON_STR_NUM_BYTES);
    }

    @Override
    protected void writeData() {
        writeByte(versionMajor);
        writeByte(versionMinor);
        writeByte(versionPatch);
        writeBool(accepted);
        writeString(rejectionReason, REJECTION_REASON_STR_NUM_BYTES);
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

    public boolean isAccepted() {
        return accepted;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }
}
