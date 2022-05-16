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

import com.bennero.common.logging.LogLevel;
import com.bennero.common.logging.Logger;
import eu.hansolo.medusa.Gauge;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class Sensor extends BorderPane {
    // Class name used in logging
    public static final String CLASS_NAME = Sensor.class.getName();

    private byte uniqueId;
    private int row;
    private int column;
    private byte type;
    private byte skin;
    private float max;
    private float threshold;
    private String originalName;
    private String title;
    private String hardwareType;
    private float value;
    private boolean averageEnabled;
    private int averagingPeriod;
    private int rowSpan;
    private int columnSpan;

    private Color averageColour;
    private Color needleColour;
    private Color valueColour;
    private Color unitColour;
    private Color knobColour;
    private Color barColour;
    private Color thresholdColour;
    private Color titleColour;
    private Color barBackgroundColour;
    private Color foregroundColour;
    private Color tickLabelColour;
    private Color tickMarkColour;
    private Gauge gauge;

    private ChangeListener<Float> valueChangeListener;

    public Sensor(final byte id,
                  final int row,
                  final int column,
                  final byte type,
                  final byte skin,
                  final float max,
                  final float threshold,
                  final String originalName,
                  final String title,
                  final boolean averageEnabled,
                  final int averagingPeriod,
                  final int rowSpan,
                  final int columnSpan) {
        this.uniqueId = id;
        this.row = row;
        this.column = column;
        this.type = type;
        this.skin = skin;
        this.threshold = threshold;
        setMax(max);
        this.originalName = originalName;
        this.title = title;
        this.hardwareType = "Not Specified";
        this.averageEnabled = averageEnabled;
        this.averagingPeriod = averagingPeriod;
        this.rowSpan = rowSpan;
        this.columnSpan = columnSpan;

        initSensor(true);
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        if (valueChangeListener != null) {
            valueChangeListener.changed(null, null, value);
        }
        this.value = value;

        if (value > max) {
            setMax(value);
        }

        if (gauge != null) {
            gauge.setValue(value);
        } else {
            Logger.log(LogLevel.ERROR, CLASS_NAME, "Sensor GUI is invalid so value could not be set");
        }
    }

    public void setValueChangeListener(ChangeListener<Float> listener) {
        this.valueChangeListener = listener;
    }

    public byte getType() {
        return type;
    }

    public float getThreshold() {
        return threshold;
    }

    public void setThreshold(float value) {
        // threshold must be >25% of max
        threshold = value;

        if (threshold < max * 0.25f) {
            threshold = max * 0.25f;
        }

        if (this.gauge != null) {
            this.gauge.setThreshold(threshold);
        }
    }

    public void setPosition(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.gauge.setTitle(title);
    }

    public byte getUniqueId() {
        return uniqueId;
    }

    public String getHardwareType() {
        return hardwareType;
    }

    public void setHardwareType(String hardwareType) {
        this.hardwareType = hardwareType;
    }

    public int getRowSpan() {
        return rowSpan;
    }

    public void setRowSpan(int rowSpan) {
        this.rowSpan = rowSpan;
    }

    public int getColumnSpan() {
        return columnSpan;
    }

    public void setColumnSpan(int columnSpan) {
        this.columnSpan = columnSpan;
    }

    public byte getSkin() {
        return this.skin;
    }

    public void setSkin(byte skin) {
        this.skin = skin;

        initSensor(true);


        // this.gauge.setSkinType(SkinHelper.getSkinType(skin));
        // this.gauge.reInit();
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        if (max < 1.0f) {
            this.max = 2.0f;
            this.threshold = 1.0f;
        } else {
            this.max = max;
        }

        if (type == SensorType.LOAD || type == SensorType.CONTROL || type == SensorType.LEVEL) {
            this.max = 100.0f;
        }

        // Threshold cannot be less than 25% of max (at <20% some gauges have visual bugs)
        if (threshold < max * 0.25f) {
            threshold = max * 0.25f;
        }

        if (gauge != null) {
            gauge.setMaxValue(this.max);
            gauge.setThreshold(this.max * threshold);
        }
    }

    private void _setAverageEnabled() {
        if (gauge != null && SkinHelper.checkSupport(skin, Skin.AVERAGE_COLOUR_SUPPORTED)) {
            gauge.setAveragingEnabled(averageEnabled);
            gauge.setAverageVisible(averageEnabled);
            _setAverageColour();
        }
    }

    public boolean isAverageEnabled() {
        return this.averageEnabled;
    }

    public void setAverageEnabled(boolean state) {
        this.averageEnabled = state;
        _setAverageEnabled();
    }

    private void _setAveragingPeriod() {
        if (gauge != null && averageEnabled && SkinHelper.checkSupport(skin, Skin.AVERAGE_COLOUR_SUPPORTED)) {
            gauge.setAveragingPeriod(averagingPeriod);
        }
    }

    public int getAveragingPeriod() {
        return averagingPeriod;
    }

    public void setAveragingPeriod(int averagingPeriod) {
        this.averagingPeriod = averagingPeriod;
        _setAveragingPeriod();
    }

    public void setId(byte id) {
        this.uniqueId = id;
    }

    public void setOnGaugeClicked(EventHandler<MouseEvent> eventHandler) {
        if (this.gauge != null) {
            this.gauge.setOnMouseClicked(eventHandler);
        }
    }

    // AVOID FLAGS specifies the values that will not be initialised on init sensor. This is because sometimes when a
    // sensor gauge property is changed, the gauge has to be completely re-initialised in order for that change to
    // visually take effect. I think this is an issue with the gauge library because I could not find a way to fire the
    // event that makes a gauge redraw with its new values. For example, the modern gauge only lets you set bar colour
    // before it is visible on an actual pane
    private void initSensor(boolean resetColours) {
        if (gauge != null) {
            super.getChildren().remove(gauge);
        }

        gauge = new Gauge(SkinHelper.getSkinType(skin));
        gauge.setTitle(title);
        gauge.setUnit(SensorType.getSuffix(type));
        gauge.setCache(true);
        gauge.setCacheHint(CacheHint.SPEED);
        gauge.setAnimated(true);

        gauge.setMajorTickSpace(max / 6);
        gauge.setMinorTickSpace(max / 24);
        gauge.setMaxValue(max);
        gauge.setValue(value);

        if (SkinHelper.checkSupport(skin, Skin.THRESHOLD_COLOUR_SUPPORTED)) {
            gauge.setThreshold(threshold);
        }

        if (gauge != null) {
            BorderPane.setAlignment(gauge, Pos.CENTER);
            super.setCenter(gauge);
        }

        if (resetColours) {
            averageColour = null;
            needleColour = null;
            valueColour = null;
            unitColour = null;
            knobColour = null;
            barColour = null;
            thresholdColour = null;
            titleColour = null;
            barBackgroundColour = null;
            foregroundColour = null;
            tickLabelColour = null;
            tickMarkColour = null;
        }

        _setForegroundColour();
        _setAverageEnabled();
        _setAverageColour();
        _setNeedleColour(resetColours);
        _setValueColour();
        _setUnitColour();
        _setKnobColour();
        _setBarColour(resetColours);
        _setThresholdColour();
        _setTitleColour();
        _setBarBackgroundColour();
        _setTickLabelColour(resetColours);
        _setTickMarkColour();
    }

    public Color getAverageColour() {
        return averageColour;
    }

    public void setAverageColour(Color averageColour) {
        this.averageColour = averageColour;
        _setAverageColour();
    }

    private void _setAverageColour() {
        if (averageEnabled && this.gauge != null && SkinHelper.checkSupport(skin, Skin.AVERAGE_COLOUR_SUPPORTED)) {
            if (averageColour != null) {
                this.gauge.setAverageColor(averageColour);
            } else {
                averageColour = this.gauge.getAverageColor();
            }
        }
    }

    public Color getNeedleColour() {
        return needleColour;
    }

    public void setNeedleColour(Color needleColour) {
        this.needleColour = needleColour;
        _setNeedleColour(true);
    }

    private void _setNeedleColour(boolean resetColours) {
        if (this.gauge != null && SkinHelper.checkSupport(skin, Skin.NEEDLE_COLOUR_SUPPORTED)) {
            if (needleColour != null) {
                this.gauge.setNeedleColor(needleColour);

                // Forces the gauge to re-initialise with specific skins because for some reason they wont apply the
                // colour changes unless it is completely re-initialised. I can't find a way to get the library to
                // redraw/reset the skin so this is the current solution.
                if (resetColours) {
                    switch (skin) {
                        case Skin.AMP:
                        case Skin.PLAIN_AMP:
                            initSensor(false);
                            break;
                    }
                }
            } else {
                needleColour = this.gauge.getNeedleColor();
            }
        }
    }

    public Color getValueColour() {
        return valueColour;
    }

    public void setValueColour(Color valueColour) {
        this.valueColour = valueColour;
        _setValueColour();
    }

    private void _setValueColour() {
        if (this.gauge != null && SkinHelper.checkSupport(skin, Skin.VALUE_COLOUR_SUPPORTED)) {
            if (valueColour != null) {
                this.gauge.setValueColor(valueColour);
            } else {
                valueColour = this.gauge.getValueColor();
            }
        }
    }

    public Color getUnitColour() {
        return unitColour;
    }

    public void setUnitColour(Color unitColour) {
        this.unitColour = unitColour;
        _setUnitColour();
    }

    private void _setUnitColour() {
        if (this.gauge != null && SkinHelper.checkSupport(skin, Skin.UNIT_COLOUR_SUPPORTED)) {
            if (unitColour != null) {
                this.gauge.setUnitColor(unitColour);
            } else {
                unitColour = this.gauge.getUnitColor();
            }
        }
    }

    public Color getKnobColour() {
        return knobColour;
    }

    public void setKnobColour(Color knobColour) {
        this.knobColour = knobColour;
        _setKnobColour();
    }

    private void _setKnobColour() {
        if (this.gauge != null && SkinHelper.checkSupport(skin, Skin.KNOB_COLOUR_SUPPORTED)) {
            if (knobColour != null) {
                this.gauge.setKnobColor(knobColour);
            } else {
                knobColour = this.gauge.getKnobColor();
            }
        }
    }

    public Color getBarColour() {
        return barColour;
    }

    public void setBarColour(Color barColour) {
        this.barColour = barColour;
        _setBarColour(true);
    }

    private void _setBarColour(boolean resetColours) {
        if (this.gauge != null && SkinHelper.checkSupport(skin, Skin.BAR_COLOUR_SUPPORTED)) {
            if (barColour != null) {
                this.gauge.setBarColor(barColour);

                // Forces the gauge to re-initialise with specific skins because for some reason they wont apply the
                // colour changes unless it is completely re-initialised. I can't find a way to get the library to
                // redraw/reset the skin so this is the current solution.
                if (resetColours) {
                    switch (skin) {
                        case Skin.MODERN:
                            initSensor(false);
                            break;
                    }
                }
            } else {
                barColour = this.gauge.getBarColor();
            }
        }
    }

    public Color getThresholdColour() {
        return thresholdColour;
    }

    public void setThresholdColour(Color thresholdColour) {
        this.thresholdColour = thresholdColour;
        _setThresholdColour();
    }

    private void _setThresholdColour() {
        if (this.gauge != null && SkinHelper.checkSupport(skin, Skin.THRESHOLD_COLOUR_SUPPORTED)) {
            if (thresholdColour != null) {
                this.gauge.setThresholdColor(thresholdColour);
            } else {
                thresholdColour = this.gauge.getThresholdColor();
            }
        }
    }

    public Color getTitleColour() {
        return titleColour;
    }

    public void setTitleColour(Color titleColour) {
        this.titleColour = titleColour;
        _setTitleColour();
    }

    private void _setTitleColour() {
        if (this.gauge != null && SkinHelper.checkSupport(skin, Skin.TITLE_COLOUR_SUPPORTED)) {
            if (titleColour != null) {
                this.gauge.setTitleColor(titleColour);
            } else {
                titleColour = this.gauge.getTitleColor();
            }
        }
    }

    public Color getBarBackgroundColour() {
        return barBackgroundColour;
    }

    public void setBarBackgroundColour(Color barBackgroundColour) {
        this.barBackgroundColour = barBackgroundColour;
        _setBarBackgroundColour();
    }

    private void _setBarBackgroundColour() {
        if (this.gauge != null && SkinHelper.checkSupport(skin, Skin.BAR_BACKGROUND_COLOUR_SUPPORTED)) {
            if (barBackgroundColour != null) {
                this.gauge.setBarBackgroundColor(barBackgroundColour);
            } else {
                barBackgroundColour = this.gauge.getBarBackgroundColor();
            }
        }
    }

    public Color getForegroundColour() {
        return foregroundColour;
    }

    public void setForegroundColour(Color foregroundColour) {
        this.foregroundColour = foregroundColour;
        _setForegroundColour();
    }

    private void _setForegroundColour() {
        if (this.gauge != null && SkinHelper.checkSupport(skin, Skin.FOREGROUND_BASE_COLOUR_SUPPORTED)) {
            if (foregroundColour != null) {
                this.gauge.setForegroundBaseColor(foregroundColour);
            } else {
                foregroundColour = this.gauge.getTitleColor();
            }
        }
    }

    public Color getTickLabelColour() {
        return tickLabelColour;
    }

    public void setTickLabelColour(Color tickLabelColour) {
        this.tickLabelColour = tickLabelColour;
        _setTickLabelColour(true);
    }

    private void _setTickLabelColour(boolean resetColours) {
        if (this.gauge != null && SkinHelper.checkSupport(skin, Skin.TICK_LABEL_COLOUR_SUPPORTED)) {
            if (tickLabelColour != null) {
                this.gauge.setTickLabelColor(tickLabelColour);

                if (resetColours) {
                    switch (skin) {
                        case Skin.SIMPLE:
                            initSensor(false);
                            break;
                    }
                }
            } else {
                tickLabelColour = this.gauge.getTickLabelColor();
            }
        }
    }

    public Color getTickMarkColour() {
        return tickMarkColour;
    }

    public void setTickMarkColour(Color tickMarkColour) {
        this.tickMarkColour = tickMarkColour;
        _setTickMarkColour();
    }

    private void _setTickMarkColour() {
        if (this.gauge != null && SkinHelper.checkSupport(skin, Skin.TICK_MARK_COLOUR_SUPPORTED)) {
            if (tickMarkColour != null) {
                this.gauge.setTickMarkColor(tickMarkColour);
            } else {
                tickMarkColour = this.gauge.getTickMarkColor();
            }
        }
    }

    @Override
    public String toString() {
        return title;
    }
}
