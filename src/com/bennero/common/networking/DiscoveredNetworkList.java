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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DiscoveredNetworkList extends ArrayList<DiscoveredNetwork>
{
    private boolean errorOccurred;
    private int errorCode;
    private String errorMessage;

    public DiscoveredNetworkList()
    {
        errorOccurred = false;
        errorCode = 0;
        errorMessage = null;
    }

    public void setError(final String errorMessage, final int errorCode)
    {
        this.errorCode = errorCode;
        this.errorOccurred = true;
        this.errorMessage = errorMessage;
    }

    public boolean hasErrorOccurred()
    {
        return errorOccurred;
    }

    public int getErrorCode()
    {
        return errorCode;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public Set<String> getDeviceList()
    {
        Set<String> networkDeviceList = new HashSet<>();

        for(int i = 0; i < super.size(); i++)
        {
            networkDeviceList.add(super.get(i).getNetworkDevice());
        }

        return networkDeviceList;
    }

    public ArrayList<String> getNetworkList()
    {
        ArrayList<String> networkSsidList = new ArrayList<>();

        for(int i = 0; i < super.size(); i++)
        {
            networkSsidList.add(super.get(i).getNetworkSsid());
        }

        return networkSsidList;
    }

    public int getNumberOfDevices()
    {
        return getDeviceList().size();
    }
}