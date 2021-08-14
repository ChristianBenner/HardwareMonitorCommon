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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LinuxNetUtils
{
    public static final String CLASS_NAME = LinuxNetUtils.class.getName();

    private static final int NMCLI_CODE_SUCCESS = 0;
    private static final int NMCLI_CODE_INVALID_PASSWORD = 1;
    private static final int NMCLI_NO_PASSWORD_PROVIDED = 2;

    public static ConnectionAttemptStatus connectToWifi(String networkDevice, String ssid, String password)
    {
        Logger.log(LogLevel.INFO, CLASS_NAME, "Attempting to connect to network " + ssid);

        ConnectionAttemptStatus connectionAttemptStatus;

        try
        {
            final String CONNECT_COMMAND = "nmcli dev wifi connect " + ssid + " password " + password;
            Logger.log(LogLevel.DEBUG, CLASS_NAME, "Running command '" + CONNECT_COMMAND + "'");

            Runtime runtime = Runtime.getRuntime();
            Process connectProcess = runtime.exec(CONNECT_COMMAND);

            // Fetch and print out any errors that have occured
            BufferedReader errorStream = new BufferedReader(new InputStreamReader(connectProcess.getErrorStream()));
            String error;
            while ((error = errorStream.readLine()) != null)
            {
                Logger.log(LogLevel.ERROR, CLASS_NAME, "Connect command output << " + error);
            }

            int executionStatus = connectProcess.waitFor();
            System.out.println("EXECUTION STATUS: " + executionStatus);

            switch (executionStatus)
            {
                case NMCLI_CODE_SUCCESS:
                    Logger.log(LogLevel.INFO, CLASS_NAME, "Connected to the network " + ssid);
                    connectionAttemptStatus = ConnectionAttemptStatus.SUCCESS;
                    break;
                case NMCLI_CODE_INVALID_PASSWORD:
                    Logger.log(LogLevel.WARNING, CLASS_NAME, "Incorrect password entered for " + ssid);
                    connectionAttemptStatus = ConnectionAttemptStatus.INCORRECT_PASSWORD;
                    break;
                case NMCLI_NO_PASSWORD_PROVIDED:
                    Logger.log(LogLevel.WARNING, CLASS_NAME, ssid + " requires a password");
                    connectionAttemptStatus = ConnectionAttemptStatus.PASSWORD_REQUIRED;
                    break;
                default:
                    Logger.log(LogLevel.WARNING, CLASS_NAME, "Failed to connect to the network " + ssid +
                            ", (nmcli returned error code: " + executionStatus + ")");
                    connectionAttemptStatus = ConnectionAttemptStatus.FAILED_TO_CONNECT;
                    break;
            }
        }
        catch (IOException | InterruptedException e)
        {
            Logger.log(LogLevel.ERROR, CLASS_NAME, "Failed to run connect command");
            Logger.log(LogLevel.ERROR, CLASS_NAME, e.getMessage());
            connectionAttemptStatus = ConnectionAttemptStatus.FAILED_TO_RUN_CONNECT_COMMAND;
        }

       return connectionAttemptStatus;
    }
}
