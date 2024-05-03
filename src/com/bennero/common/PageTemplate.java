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

package com.bennero.common;

import javafx.scene.paint.Color;

import java.util.List;

/**
 * Defines the required methods for a GUI component to present user configured page information.
 *
 * @author Christian Benner
 * @version %I%, %G%
 * @since 1.0
 */
public interface PageTemplate {
    byte getUniqueId();

    void setUniqueId(byte id);

    Color getColour();

    void setColour(Color colour);

    Color getTitleColour();

    void setTitleColour(Color titleColour);

    Color getSubtitleColour();

    void setSubtitleColour(Color subtitleColour);

    int getRows();

    void setRows(int rows);

    int getColumns();

    void setColumns(int columns);

    byte getNextPageId();

    void setNextPageId(byte nextPageId);

    int getTransitionType();

    void setTransitionType(int transitionType);

    int getTransitionTime();

    void setTransitionTime(int transitionTime);

    int getDurationMs();

    void setDurationMs(int durationMs);

    String getTitle();

    void setTitle(String title);

    boolean isTitleEnabled();

    void setTitleEnabled(boolean titleEnabled);

    int getTitleAlignment();

    void setTitleAlignment(int titleAlignment);

    String getSubtitle();

    void setSubtitle(String subtitle);

    boolean isSubtitleEnabled();

    void setSubtitleEnabled(boolean subtitleEnabled);

    int getSubtitleAlignment();

    void setSubtitleAlignment(int subtitleAlignment);

    String getBackgroundImage();

    void setBackgroundImage(String fileName);

    List<Sensor> getSensorList();

    void addSensor(Sensor sensor);

    void removeSensor(Sensor sensor);

    boolean containsSensor(Sensor sensor);

    boolean isSpaceFree(Sensor sensor);
}
