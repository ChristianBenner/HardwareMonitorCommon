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

package com.bennero.networking;

import javafx.event.Event;

import static com.bennero.networking.NetworkUtils.stringToMacAddress;

/**
 * Holds connection information of a hardware monitor or editor node such as version and AddressInformation
 *
 * @see         AddressInformation
 * @author      Christian Benner
 * @version     %I%, %G%
 * @since       1.0
 */
public class ConnectionInformation extends Event
{
    private final byte majorVersion;
    private final byte minorVersion;
    private final byte patchVersion;
    private final AddressInformation addressInformation;

    public ConnectionInformation(final byte majorVersion,
                                 final byte minorVersion,
                                 final byte patchVersion,
                                 final String macAddressStr,
                                 final String ip4AddressStr,
                                 final String hostname)
    {
        super(hostname, null, null);
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion;
        this.patchVersion = patchVersion;
        addressInformation = new AddressInformation(stringToMacAddress(macAddressStr),stringToMacAddress(macAddressStr),
                hostname);
    }

    public ConnectionInformation(final byte majorVersion,
                                 final byte minorVersion,
                                 final byte patchVersion,
                                 final byte[] macAddress,
                                 final byte[] ip4Address,
                                 final String hostname)
    {
        super(hostname, null, null);
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion;
        this.patchVersion = patchVersion;
        addressInformation = new AddressInformation(macAddress, ip4Address, hostname);
    }

    public byte getMajorVersion()
    {
        return majorVersion;
    }

    public byte getMinorVersion()
    {
        return minorVersion;
    }

    public byte getPatchVersion()
    {
        return patchVersion;
    }

    public final byte[] getMacAddress()
    {
        return addressInformation.getMacAddress();
    }

    public final byte[] getIp4Address()
    {
        return addressInformation.getIp4Address();
    }

    public final String getHostname()
    {
        return addressInformation.getHostname();
    }

    @Override
    public String toString()
    {
        return getHostname() + " (v" + majorVersion + "." + minorVersion + "." + patchVersion + ")";
    }
}