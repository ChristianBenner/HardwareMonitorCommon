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

package com.bennero.osspecific;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Static utility functions for operating system specific networking. Java does not have built in API for certain
 * networking features such as fetching a wireless adaptors available networks. This means that for each OS that we want
 * to support this feature on, requests need to be made to the operating system and the responses scanned. This poses a
 * threat of incompatibility with future operating systems.
 *
 * @author      Christian Benner
 * @version     %I%, %G%
 * @since       1.0
 */
public class OSNetworkUtils
{
    private final static int EXIT_VALUE_SUCCESS = 0;

    private final static String WINDOWS_FETCH_NETWORKS_CMD = "netsh wlan show networks mode=Bssid";
    private final static String LINUX_FETCH_NETWORKS_CMD = "sudo iw dev wlan0 scan";
    private final static String SSID_STRING = "SSID";
    private final static String WINDOWS_BSSID_STRING = "BSSID";
    private final static String WINDOWS_SSID_STRING_DELIMETER = " : ";
    private final static String LINUX_SSID_STRING_DELIMETER = ": ";

    public static ArrayList<String> fetchNetworksWindows()
    {
        // List of found SSIDs
        ArrayList<String> foundWirelessNetworks = new ArrayList<>();

        Runtime runtime = Runtime.getRuntime();

        try
        {
            Process process = runtime.exec(WINDOWS_FETCH_NETWORKS_CMD);
            final int EXIT_VAL = process.waitFor();
            if (EXIT_VAL == EXIT_VALUE_SUCCESS)
            {
                System.out.println("Ran Windows command for fetching networks '" + WINDOWS_FETCH_NETWORKS_CMD + "'");
                BufferedReader inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line = null;
                while ((line = inputStream.readLine()) != null)
                {
                    if (line.contains(SSID_STRING) && !line.contains(WINDOWS_BSSID_STRING))
                    {
                        String[] theSsid = line.split(WINDOWS_SSID_STRING_DELIMETER);
                        for (int i = 1; i < theSsid.length; i++)
                        {
                            System.out.println("Found network: " + theSsid[i]);
                            foundWirelessNetworks.add(theSsid[i]);
                        }
                    }
                }
            }
            else
            {
                System.out.println("Error fetching wireless networks. Command '" + WINDOWS_FETCH_NETWORKS_CMD + "' returned non-zero exit value of " + EXIT_VAL);
            }
        }
        catch (IOException | InterruptedException exception)
        {
            exception.printStackTrace();
        }

        return foundWirelessNetworks;
    }

    public static ArrayList<String> fetchNetworksLinux()
    {
        // List of found SSIDs
        ArrayList<String> foundWirelessNetworks = new ArrayList<>();

        Runtime runtime = Runtime.getRuntime();

        try
        {
            Process process = runtime.exec(LINUX_FETCH_NETWORKS_CMD);
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = inputStream.readLine()) != null)
            {
                if (line.contains(SSID_STRING))
                {
                    String[] theSsid = line.split(LINUX_SSID_STRING_DELIMETER);
                    for (int i = 1; i < theSsid.length; i++)
                    {
                        boolean found = false;
                        // Check if the network list already contains the network, if not add it
                        for (int n = 0; n < foundWirelessNetworks.size() && !found; n++)
                        {
                            if (foundWirelessNetworks.get(n).compareTo(theSsid[i]) == 0)
                            {
                                found = true;
                            }
                        }

                        if (!found)
                        {
                            System.out.println("Discovered new network: " + theSsid[i]);
                            foundWirelessNetworks.add(theSsid[i]);
                        }
                    }
                }
            }
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }

        return foundWirelessNetworks;
    }
}
