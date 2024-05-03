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

import com.bennero.common.osspecific.LinuxNetUtils;
import com.bennero.common.osspecific.OSNetworkUtils;
import com.bennero.common.osspecific.OSUtils;
import com.bennero.common.osspecific.RaspberryPiNetUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Networking static utility functions. Contains many methods for data conversion (different value types to bytes and
 * vice versa). Includes other useful network utilities such as receiving a list of wireless networks, connecting to
 * WiFi, and getting the local machines IP address.
 *
 * @author Christian Benner
 * @version %I%, %G%
 * @since 1.0
 */
public class NetworkUtils {
    private final static boolean WINDOWS_NETWORK_CHANGE_SUPPORTED = false;
    private final static boolean LINUX_NETWORK_CHANGE_SUPPORTED = true;
    private final static boolean MAC_OS_NETWORK_CHANGE_SUPPORTED = false;
    private final static boolean RASPBERRY_PI_NETWORK_CHANGE_SUPPORTED = true;

    // Returns a list of SSIDs for the found wireless networks
    public static DiscoveredNetworkList getWirelessNetworks() throws Exception {
        if (!isNetworkChangeSupported()) {
            return new DiscoveredNetworkList();
        }

        switch (OSUtils.getOperatingSystem()) {
            case WINDOWS:
                return OSNetworkUtils.fetchNetworksWindows();
            case RASPBERRY_PI:
            case MAC:
            case LINUX:
                return OSNetworkUtils.fetchNetworksLinux();
            default:
            case UNDEFINED:
                throw new Exception("Operating system not supported to fetch networks: " +
                        OSUtils.getOperatingSystemString());
        }
    }

    public static boolean isNetworkChangeSupported() {
        switch (OSUtils.getOperatingSystem()) {
            case RASPBERRY_PI:
                return RASPBERRY_PI_NETWORK_CHANGE_SUPPORTED;
            default:
            case LINUX:
                return LINUX_NETWORK_CHANGE_SUPPORTED;
            case WINDOWS:
                return WINDOWS_NETWORK_CHANGE_SUPPORTED;
            case MAC:
                return MAC_OS_NETWORK_CHANGE_SUPPORTED;
            case UNDEFINED:
                return false;
        }
    }

    public static ConnectionAttemptStatus connectToWifi(String networkDevice, String ssid, String password) {
        if (!isNetworkChangeSupported()) {
            return ConnectionAttemptStatus.OS_NOT_SUPPORTED;
        }

        switch (OSUtils.getOperatingSystem()) {
            case RASPBERRY_PI:
                return RaspberryPiNetUtils.connectToWifi(ssid, password);
            default:
            case LINUX:
                return LinuxNetUtils.connectToWifi(networkDevice, ssid, password);
            case WINDOWS:
                return ConnectionAttemptStatus.OS_NOT_SUPPORTED;
            case MAC:
                return ConnectionAttemptStatus.OS_NOT_SUPPORTED;
        }
    }

    public static boolean isConnected() {
        try {
            getMyIpAddress();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static AddressInformation getMyIpAddress() throws UnknownHostException, SocketException {
        // This seems to be a reliable way to retrieve the hostname of the machine
        String hostName = InetAddress.getLocalHost().getHostName();
        byte[] macAddress = null;
        byte[] ip4Address = null;

        boolean found = false;

        // Find the INET address that contains the site local address (the IP that will be used to communicate accross
        // the local network
        ArrayList<NetworkInterface> networkInterfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
        for (int i = 0; i < networkInterfaces.size() && !found; i++) {
            // Retrieve each address under the network interface
            ArrayList<InetAddress> networkAddresses = Collections.list(networkInterfaces.get(i).getInetAddresses());
            for (int n = 0; n < networkAddresses.size() && !found; n++) {
                if (networkAddresses.get(n).isSiteLocalAddress()) {
                    macAddress = networkInterfaces.get(i).getHardwareAddress();
                    ip4Address = networkAddresses.get(n).getAddress();
                    found = true;
                }
            }
        }

        // If some information couldn't be discovered, throw an error
        if (!found || hostName == null || hostName.isEmpty() || macAddress == null || ip4Address == null) {
            throw new UnknownHostException();
        }

        return new AddressInformation(macAddress, ip4Address, hostName);
    }

    public static byte[] stringToMacAddress(String macAddressStr) {
        // Parse the mac address string e.g. // FF.FF.FF.FF.FF.FF
        String[] macAddressParts = macAddressStr.split("-");

        final int MAC_ADDRESS_LENGTH_BYTES = 6;
        byte[] macAddress = new byte[MAC_ADDRESS_LENGTH_BYTES];
        for (int i = 0; i < macAddressParts.length && i < MAC_ADDRESS_LENGTH_BYTES; i++) {
            // For whatever reason, Byte.parseByte cannot be used (I think when values above 128 cause a parse
            // failure because bytes are signed in Java). Instead, parse it as an Integer and cast to byte)
            macAddress[i] = (byte) Integer.parseInt(macAddressParts[i], 16);
        }

        return macAddress;
    }

    public static String macAddressToString(byte[] macAddressBytes) {
        // Build MAC address string
        StringBuilder macAddressBuilder = new StringBuilder();
        for (int i = 0; i < macAddressBytes.length; i++) {
            macAddressBuilder.append(String.format("%02X%s", macAddressBytes[i],
                    (i < macAddressBytes.length - 1) ? "-" : ""));
        }
        return macAddressBuilder.toString();
    }

    public static byte[] stringToIp4Address(String ip4AddressStr) {
        // Parse the IP address string e.g. // XXX.XXX.XXX.XXX
        String[] ip4AddressParts = ip4AddressStr.split("\\.");

        final int IP4_ADDRESS_LENGTH_BYTES = 4;
        byte[] ip4Address = new byte[IP4_ADDRESS_LENGTH_BYTES];
        for (int i = 0; i < ip4AddressParts.length && i < IP4_ADDRESS_LENGTH_BYTES; i++) {
            // For whatever reason, Byte.parseByte cannot be used (I think when values above 128 cause a parse
            // failure because bytes are signed in Java). Instead, parse it as an Integer and cast to byte)
            ip4Address[i] = (byte) Integer.parseInt(ip4AddressParts[i]);
        }

        return ip4Address;
    }

    public static String ip4AddressToString(byte[] ip4AddressBytes) {
        // Build IP4 address string
        StringBuilder ip4AddressBuilder = new StringBuilder();
        for (int i = 0; i < ip4AddressBytes.length; i++) {
            ip4AddressBuilder.append(String.format("%d%s", ip4AddressBytes[i] & 0xFF,
                    (i < ip4AddressBytes.length - 1) ? "." : ""));
        }
        return ip4AddressBuilder.toString();
    }

    public static boolean doAddressesMatch(byte[] firstAddress, byte[] secondAddress) {
        boolean match = firstAddress.length == secondAddress.length;

        for (int i = 0; match && i < firstAddress.length && i < secondAddress.length; i++) {
            match = firstAddress[i] == secondAddress[i];
        }

        return match;
    }
}