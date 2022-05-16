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

import java.util.ArrayList;
import java.util.List;

/**
 * Stores all of the data associated to a customisable Page (the pages that the user can configure to display hardware
 * sensor data).
 *
 * @author Christian Benner
 * @version %I%, %G%
 * @since 1.0
 */
public class PageData implements PageTemplate {
    private byte id;
    private Color colour;
    private Color titleColour;
    private Color subtitleColour;
    private int rows;
    private int columns;
    private byte nextPageId;

    private int transitionType;
    private int transitionTime;
    private int durationMs;
    private String title;
    private boolean titleEnabled;
    private int titleAlignment;
    private String subtitle;
    private boolean subtitleEnabled;
    private int subtitleAlignment;
    private List<Sensor> sensorList;

    public PageData(final byte id,
                    final Color colour,
                    final Color titleColour,
                    final Color subtitleColour,
                    final int rows,
                    final int columns,
                    final byte nextPageId,
                    final int transitionType,
                    final int transitionTime,
                    final int durationMs,
                    final String title,
                    final boolean titleEnabled,
                    final int titleAlignment,
                    final String subheading,
                    final boolean subheadingEnabled,
                    final int subheadingAlignment) {
        this.id = id;
        this.colour = colour;
        this.titleColour = titleColour;
        this.subtitleColour = subtitleColour;
        this.rows = rows;
        this.columns = columns;
        this.nextPageId = nextPageId;
        this.transitionType = transitionType;
        this.transitionTime = transitionTime;
        this.durationMs = durationMs;
        this.title = title;
        this.titleEnabled = titleEnabled;
        this.titleAlignment = titleAlignment;
        this.subtitle = subheading;
        this.subtitleEnabled = subheadingEnabled;
        this.subtitleAlignment = subheadingAlignment;

        this.sensorList = new ArrayList<>();
    }

    @Override
    public byte getUniqueId() {
        return id;
    }

    @Override
    public void setUniqueId(byte id) {
        this.id = id;
    }

    @Override
    public Color getColour() {
        return colour;
    }

    @Override
    public void setColour(Color colour) {
        this.colour = colour;
    }

    @Override
    public Color getTitleColour() {
        return titleColour;
    }

    @Override
    public void setTitleColour(Color titleColour) {
        this.titleColour = titleColour;
    }

    @Override
    public Color getSubtitleColour() {
        return subtitleColour;
    }

    @Override
    public void setSubtitleColour(Color subtitleColour) {
        this.subtitleColour = subtitleColour;
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public int getColumns() {
        return columns;
    }

    @Override
    public void setColumns(int columns) {
        this.columns = columns;
    }

    @Override
    public byte getNextPageId() {
        return nextPageId;
    }

    @Override
    public void setNextPageId(byte nextPageId) {
        this.nextPageId = nextPageId;
    }

    @Override
    public int getTransitionType() {
        return transitionType;
    }

    @Override
    public void setTransitionType(int transitionType) {
        this.transitionType = transitionType;
    }

    @Override
    public int getTransitionTime() {
        return transitionTime;
    }

    @Override
    public void setTransitionTime(int transitionTime) {
        this.transitionTime = transitionTime;
    }

    @Override
    public int getDurationMs() {
        return durationMs;
    }

    @Override
    public void setDurationMs(int durationMs) {
        this.durationMs = durationMs;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean isTitleEnabled() {
        return titleEnabled;
    }

    @Override
    public void setTitleEnabled(boolean titleEnabled) {
        this.titleEnabled = titleEnabled;
    }

    @Override
    public int getTitleAlignment() {
        return titleAlignment;
    }

    @Override
    public void setTitleAlignment(int titleAlignment) {
        this.titleAlignment = titleAlignment;
    }

    @Override
    public boolean isSubtitleEnabled() {
        return subtitleEnabled;
    }

    @Override
    public void setSubtitleEnabled(boolean subtitleEnabled) {
        this.subtitleEnabled = subtitleEnabled;
    }

    @Override
    public String getSubtitle() {
        return subtitle;
    }

    @Override
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    @Override
    public int getSubtitleAlignment() {
        return subtitleAlignment;
    }

    @Override
    public void setSubtitleAlignment(int subtitleAlignment) {
        this.subtitleAlignment = subtitleAlignment;
    }

    @Override
    public List<Sensor> getSensorList() {
        return sensorList;
    }

    @Override
    public void addSensor(Sensor sensor) {
        sensorList.add(sensor);
    }

    @Override
    public void removeSensor(Sensor sensor) {
        sensorList.remove(sensor);
    }

    @Override
    public boolean containsSensor(Sensor sensor) {
        boolean found = false;
        for (int i = 0; i < sensorList.size() && !found; i++) {
            if (sensorList.get(i) == sensor) {
                found = true;
            }
        }

        return found;
    }

    @Override
    public boolean isSpaceFree(Sensor sensor) {
        boolean spaceFree = true;

        for (int i = 0; i < sensorList.size() && spaceFree; i++) {
            Sensor existingSensor = sensorList.get(i);

            int row = sensor.getRow();
            int column = sensor.getColumn();
            int endColumn = sensor.getColumn() + sensor.getColumnSpan();
            int endRow = row + sensor.getRowSpan();
            int placedStartColumn = existingSensor.getColumn();
            int placedEndColumn = placedStartColumn + existingSensor.getColumnSpan();
            int placedStartRow = existingSensor.getRow();
            int placedEndRow = placedStartRow + existingSensor.getRowSpan();

            boolean withinRow = (row >= placedStartRow && row < placedEndRow) ||
                    (endRow > placedStartRow && endRow <= placedEndRow);
            boolean withinColumn = (column >= placedStartColumn && column < placedEndColumn) ||
                    (endColumn > placedStartColumn && endColumn <= placedEndColumn);

            if (withinRow && withinColumn) {
                spaceFree = false;
            }
        }

        return spaceFree;
    }

    @Override
    public String toString() {
        return title;
    }
}
