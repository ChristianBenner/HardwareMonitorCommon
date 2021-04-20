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

import java.io.*;

/**
 * Static utility functions for operating system related tasks such as determining the current operating system.
 *
 * @author      Christian Benner
 * @version     %I%, %G%
 * @since       1.0
 */
public class OSUtils
{
    private final static String RASPBERRY_PI_OS_STRING = "Raspberry Pi";
    private final static String OPERATING_SYSTEM = System.getProperty("os.name").toLowerCase();

    public static String getOperatingSystemString()
    {
        if (isRaspberryPi())
        {
            return RASPBERRY_PI_OS_STRING;
        }

        return OPERATING_SYSTEM;
    }

    public static OperatingSystem getOperatingSystem()
    {
        if (isWindows())
        {
            return OperatingSystem.WINDOWS;
        }

        if (isMac())
        {
            return OperatingSystem.MAC;
        }

        if (isRaspberryPi())
        {
            return OperatingSystem.RASPBERRY_PI;
        }

        if (isLinux())
        {
            return OperatingSystem.LINUX;
        }

        return OperatingSystem.UNDEFINED;
    }

    public static boolean isWindows()
    {
        return OPERATING_SYSTEM.indexOf("windows") >= 0;
    }

    public static boolean isMac()
    {
        return OPERATING_SYSTEM.indexOf("mac") >= 0;
    }

    public static boolean isLinux()
    {
        return OPERATING_SYSTEM.indexOf("linux") >= 0;
    }

    public static boolean isRaspberryPi()
    {
        if (isLinux())
        {
            try
            {
                FileInputStream fileInputStream = new FileInputStream(new File("/etc/os-release"));
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

                String line = null;
                while ((line = bufferedReader.readLine()) != null)
                {
                    if (line.toLowerCase().contains("name") && line.toLowerCase().contains("raspbian"))
                    {
                        return true;
                    }
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return false;
    }

    public enum OperatingSystem
    {
        WINDOWS,
        MAC,
        LINUX,
        RASPBERRY_PI,
        UNDEFINED
    }
}
