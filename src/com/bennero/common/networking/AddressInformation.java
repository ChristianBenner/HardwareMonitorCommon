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

package com.bennero.common.networking;

/**
 * Holds network address information such as MAC address, IP4 address and hostname. The class provides encapsulation for
 * commonly connection information.
 *
 * @author Christian Benner
 * @version %I%, %G%
 * @since 1.0
 */
public class AddressInformation {
    private final byte[] macAddress;
    private final byte[] ip4Address;
    private final String hostname;

    public AddressInformation(final byte[] macAddressStr,
                              final byte[] ip4AddressStr,
                              final String hostname) {
        this.macAddress = macAddressStr;
        this.ip4Address = ip4AddressStr;
        this.hostname = hostname;
    }

    public final byte[] getMacAddress() {
        return macAddress;
    }

    public final byte[] getIp4Address() {
        return ip4Address;
    }

    public final String getHostname() {
        return hostname;
    }
}