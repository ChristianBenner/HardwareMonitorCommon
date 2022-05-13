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

import eu.hansolo.medusa.Gauge;

/**
 * Constants for different types of Gauge skins. Each Gauge Skin has a unique ID represented as a byte for efficient
 * usage in networking or other message protocols.
 *
 * @author Christian Benner
 * @version %I%, %G%
 * @since 1.0
 */
public class Skin {
    public final static byte SPACE = 0x01;
    public final static byte GRAPH = 0x02;
    public final static byte FLAT = 0x03;
    public final static byte MODERN = 0x04;
    public final static byte AMP = 0x05;
    public final static byte PLAIN_AMP = 0x06;
    public final static byte BULLET_CHART = 0x07;
    public final static byte DASHBOARD = 0x08;
    public final static byte GAUGE = 0x09;
    public final static byte INDICATOR = 0x0A;
    public final static byte KPI = 0x0B;
    public final static byte SIMPLE = 0x0C;
    public final static byte SLIM = 0x0D;
    public final static byte QUARTER = 0x0E;
    public final static byte HORIZONTAL = 0x0F;
    public final static byte VERTICAL = 0x10;
    public final static byte LCD = 0x11;
    public final static byte TINY = 0x12;
    public final static byte BATTERY = 0x13;
    public final static byte LEVEL = 0x14;
    public final static byte LINEAR = 0x15;
    public final static byte DIGITAL = 0x16;
    public final static byte SIMPLE_DIGITAL = 0x17;
    public final static byte SECTION = 0x18;
    public final static byte BAR = 0x19;
    public final static byte WHITE = 0x1A;
    public final static byte CHARGE = 0x1B;
    public final static byte SIMPLE_SELECTION = 0x1C;
    public final static byte TILE_KPI = 0x1D;
    public final static byte TILE_TEXT_KPI = 0x1E;

    public final static int NO_SUPPORT = 0;
    public final static int AVERAGE_COLOUR_SUPPORTED = 1;
    public final static int NEEDLE_COLOUR_SUPPORTED = 2;
    public final static int VALUE_COLOUR_SUPPORTED = 4;
    public final static int UNIT_COLOUR_SUPPORTED = 8;
    public final static int KNOB_COLOUR_SUPPORTED = 16;
    public final static int BAR_COLOUR_SUPPORTED = 32;
    public final static int THRESHOLD_COLOUR_SUPPORTED = 64;
    public final static int TITLE_COLOUR_SUPPORTED = 128;
    public final static int BAR_BACKGROUND_COLOUR_SUPPORTED = 256;
    public final static int FOREGROUND_BASE_COLOUR_SUPPORTED = 512;
    public final static int TICK_LABEL_COLOUR_SUPPORTED = 1024;
    public final static int TICK_MARK_COLOUR_SUPPORTED = 2056;

    private byte code;
    private String name;
    private Gauge.SkinType skinType;
    private int colourSupportFlags;

    public Skin(byte code, String name, Gauge.SkinType skinType, int colourSupportFlags) {
        this.code = code;
        this.name = name;
        this.skinType = skinType;
        this.colourSupportFlags = colourSupportFlags;
    }

    public byte getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Gauge.SkinType getSkinType() {
        return skinType;
    }

    public boolean checkSupport(int supportFlag) {
        if ((supportFlag & colourSupportFlags) == supportFlag) {
            return true;
        }

        return false;
    }
}
