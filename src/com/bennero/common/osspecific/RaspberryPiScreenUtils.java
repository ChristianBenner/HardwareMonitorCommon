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

import java.io.IOException;

/**
 * Raspberry Pi OS specific screen utility static functions. Provides the ability to enable or disable the display
 * connected to a Raspberry Pi device.
 *
 * @author Christian Benner
 * @version %I%, %G%
 * @since 1.0
 */
public class RaspberryPiScreenUtils {
    private final static String TURN_DISPLAY_ON = "xset -display :0.0 dpms force on";
    private final static String TURN_DISPLAY_OFF = "xset -display :0.0 dpms force off";
    private static boolean s_display_enabled = true;
    // if turning on the display goes back to black use 'sleep 1 && xset -display :0.0 dpms force off '

    public static boolean isDisplayEnabled() {
        return s_display_enabled;
    }

    public static void setDisplayEnabled(boolean state) {
        final String command = state ? TURN_DISPLAY_ON : TURN_DISPLAY_OFF;

        if (OSUtils.getOperatingSystem() == OSUtils.OperatingSystem.RASPBERRY_PI) {
            try {
                Runtime runtime = Runtime.getRuntime();
                Process reconnectCommand = runtime.exec(command);
                int reconnectCommandVal = reconnectCommand.waitFor();

                Logger.log(LogLevel.DEBUG, OSNetworkUtils.class.getName(), "Command '" + command +
                        "' returned with exit val: " + reconnectCommandVal);

                if (reconnectCommandVal != 0) {
                    Logger.log(LogLevel.ERROR, OSNetworkUtils.class.getName(), "Failed to turn display " +
                            (state ? "on" : "off") + "using command '" + command + "'");
                } else {
                    s_display_enabled = state;
                }
            } catch (IOException | InterruptedException e) {
                Logger.log(LogLevel.ERROR, OSNetworkUtils.class.getName(), "Failed to run display command '" +
                        command + "'");
                e.printStackTrace();
            }
        } else {
            Logger.log(LogLevel.ERROR, OSNetworkUtils.class.getName(), "Attempting to run a Raspberry Pi OS " +
                    "command on " + OSUtils.getOperatingSystemString());
        }
    }
}
