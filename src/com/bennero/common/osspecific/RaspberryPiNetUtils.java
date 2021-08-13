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

package com.bennero.common.osspecific;

import com.bennero.common.logging.LogLevel;
import com.bennero.common.logging.Logger;
import com.bennero.common.networking.ConnectionAttemptStatus;

import java.io.*;

/**
 * Raspberry Pi OS specific network utility static functions. Provides the ability to command the OS to connect to a
 * specific network.
 *
 * @author      Christian Benner
 * @version     %I%, %G%
 * @since       1.0
 */
public class RaspberryPiNetUtils
{
    public static final String CLASS_NAME = RaspberryPiNetUtils.class.getName();

    private final static String WIRELESS_CONFIG_DIR = "/etc/wpa_supplicant";
    private final static String WIRELESS_NETWORK_DATA_FILE = WIRELESS_CONFIG_DIR + "/wpa_supplicant.conf";
    private final static String RECONFIGURE_NETWORK_COMMAND = "wpa_cli -i wlan0 reconfigure";
    private final static String CHECK_CONNECTION_COMMAND = "ifconfig wlan0";
    private final static String INET_FIELD = "inet";
    private final static int NUMBERS_PER_IP4_ADDRESS = 4;

    public static ConnectionAttemptStatus connectToWifi(String ssid, String password)
    {
        // Connection status
        ConnectionAttemptStatus connectionAttemptStatus = ConnectionAttemptStatus.UNKNOWN;

        // Check if the configuration directory exist before trying to create or overwrite a file in it. This is also a
        // good indication/check that we are reading/writing in a Raspberry Pi file system
        final File WIRELESS_CONFIG_DIR_FILE = new File(WIRELESS_CONFIG_DIR);
        if (WIRELESS_CONFIG_DIR_FILE.exists())
        {
            // Attempt to write the network data to the network data file
            if (writeNetworkDataFile(ssid, password))
            {
                // Reconfigure the network so that the device uses the new network data file
                if (reconfigureNetwork())
                {
                    if(isConnected())
                    {
                        connectionAttemptStatus = ConnectionAttemptStatus.SUCCESS;
                    }
                    else
                    {
                        connectionAttemptStatus = ConnectionAttemptStatus.FAILED_TO_CONNECT;
                    }
                }
                else
                {
                    connectionAttemptStatus = ConnectionAttemptStatus.FAILED_TO_RECONFIGURE_NETWORK;
                }
            }
            else
            {
                connectionAttemptStatus = ConnectionAttemptStatus.FAILED_TO_WRITE_NETWORK_DATA_FILE;
            }
        }
        else
        {
            Logger.log(LogLevel.ERROR, CLASS_NAME, "Wireless config directory non existent '" +
                    WIRELESS_CONFIG_DIR + "'");

            connectionAttemptStatus = ConnectionAttemptStatus.NETWORK_DATA_FILE_NOT_FOUND;
        }

        return connectionAttemptStatus;
    }

    private static boolean writeNetworkDataFile(String ssid, String password)
    {
        boolean dataFileWritten = false;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ctrl_interface=DIR=/var/run/wpa_supplicant GROUP=netdev\n");
        stringBuilder.append("update_config=1\n");
        stringBuilder.append("country=us\n");
        stringBuilder.append("network={\n");
        stringBuilder.append("ssid=\"" + ssid + "\"\n");
        stringBuilder.append("psk=\"" + password + "\"\n");
        stringBuilder.append("}\n");
        Logger.log(LogLevel.DEBUG, CLASS_NAME, "Created new network file string:\n" + stringBuilder.toString());

        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(WIRELESS_NETWORK_DATA_FILE));
            Logger.log(LogLevel.DEBUG, CLASS_NAME, "Created new network file: " + WIRELESS_NETWORK_DATA_FILE);
            fileOutputStream.write(stringBuilder.toString().getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
            Logger.log(LogLevel.DEBUG, CLASS_NAME, "Closed network file: " + WIRELESS_NETWORK_DATA_FILE);
            dataFileWritten = true;
        }
        catch (FileNotFoundException e)
        {
            Logger.log(LogLevel.ERROR, CLASS_NAME, "Cannot create or open file '" + WIRELESS_NETWORK_DATA_FILE +
                    "'");
            Logger.log(LogLevel.ERROR, CLASS_NAME, e.getMessage());
        }
        catch (IOException e)
        {
            Logger.log(LogLevel.ERROR, CLASS_NAME, e.getMessage());
        }

        return dataFileWritten;
    }

    private static boolean reconfigureNetwork()
    {
        boolean networkReconfigured = false;

        Logger.log(LogLevel.INFO, CLASS_NAME, "Reconfiguring the devices network");
        Logger.log(LogLevel.DEBUG, CLASS_NAME, "Running '" + RECONFIGURE_NETWORK_COMMAND +
                "' to connect to the new network");

        try
        {
            Runtime runtime = Runtime.getRuntime();
            Process reconnectCommand = runtime.exec(RECONFIGURE_NETWORK_COMMAND);
            int reconnectCommandVal = reconnectCommand.waitFor();
            networkReconfigured = reconnectCommandVal == 0;

            Logger.log(LogLevel.DEBUG, CLASS_NAME, "Command '" + RECONFIGURE_NETWORK_COMMAND +
                    "' returned with exit val: " + reconnectCommandVal);

            if (!networkReconfigured)
            {
                Logger.log(LogLevel.ERROR, CLASS_NAME, "Failed to reconfigure devices " +
                        "network. Command exited with code: " + reconnectCommandVal);
            }
        }
        catch (IOException | InterruptedException e)
        {
            Logger.log(LogLevel.ERROR, CLASS_NAME, "Failed to reconfigure network using command '" +
                    RECONFIGURE_NETWORK_COMMAND + "'");
            Logger.log(LogLevel.ERROR, CLASS_NAME, e.getMessage());
        }

        return networkReconfigured;
    }

    private static boolean isConnected()
    {
        boolean connected = false;

        try
        {
            Logger.log(LogLevel.DEBUG, CLASS_NAME, "Running '" + CHECK_CONNECTION_COMMAND +
                    "' to determine network connectivity");

            Runtime runtime = Runtime.getRuntime();
            Process validateCommand = runtime.exec(CHECK_CONNECTION_COMMAND);
            int validateCommandVal = validateCommand.waitFor();

            Logger.log(LogLevel.DEBUG, CLASS_NAME, "Command '" + CHECK_CONNECTION_COMMAND +
                    "' returned with exit val: " + validateCommandVal);

            // Check that the command ran correctly
            if (validateCommandVal != 0)
            {
                Logger.log(LogLevel.ERROR, CLASS_NAME, "Failed to retrieve network " +
                        "connection status. Command exited with code: " + validateCommandVal);
            }
            else
            {
                BufferedReader inputStream = new BufferedReader(new InputStreamReader(validateCommand.
                        getInputStream()));
                String line = null;
                while ((line = inputStream.readLine()) != null && !connected)
                {
                    // Check if the line contains the whole word 'inet'
                    String[] words = line.split(" ");
                    for (int i = 0; i < words.length; i++)
                    {
                        if (words[i].compareTo(INET_FIELD) == 0)
                        {
                            // We should expect the next word to be an IP address (xxx.xxx.xxx.xxx)
                            final int ip4AddressIndex = i + 1;
                            if (ip4AddressIndex < words.length)
                            {
                                String inetFieldContents = words[ip4AddressIndex];
                                if (inetFieldContents != null && !inetFieldContents.isEmpty())
                                {
                                    Logger.log(LogLevel.DEBUG, CLASS_NAME,
                                            "Located INET field with contents: " + inetFieldContents +
                                                    ". Validating IPv4 address");

                                    // Validation is just checking that four integers have been given in a format
                                    // xxx.xxx.xxx.xxx
                                    if (inetFieldContents.split("\\.").length == NUMBERS_PER_IP4_ADDRESS)
                                    {
                                        Logger.log(LogLevel.INFO, CLASS_NAME,
                                                "Successfully connected to network. Verified that an IPv4 " +
                                                        "address has been assigned");
                                        connected = true;
                                    }
                                    else
                                    {
                                        Logger.log(LogLevel.WARNING, CLASS_NAME, "Invalid " +
                                                "IPv4 address in system information INET field '" + inetFieldContents +
                                                "'");
                                    }
                                }
                            }
                            else
                            {
                                Logger.log(LogLevel.WARNING, CLASS_NAME, "No IPv4 address " +
                                        "found in system information INET field");
                            }
                        }
                    }
                }
            }

        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return connected;
    }
}
