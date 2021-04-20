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

package com.bennero.common.messages;

/**
 * Defines a sensor data messages data structure (position of each value within the message data)
 *
 * @author      Christian Benner
 * @version     %I%, %G%
 * @since       1.0
 */
public class SensorDataPositions
{
    public final static int ID_POS = 1;
    public final static int PAGE_ID_POS = 2;
    public final static int ROW_POS = 3;
    public final static int COLUMN_POS = 4;
    public final static int TYPE_POS = 5;
    public final static int SKIN_POS = 6;
    public final static int MAX_POS = 7;
    public final static int THRESHOLD_POS = 11;
    public final static int AVERAGE_ENABLED_POS = 15;
    public final static int AVERAGING_PERIOD_POS = 16;
    public final static int ROW_SPAN_POS = 20;
    public final static int COLUMN_SPAN_POS = 21;
    public final static int AVERAGE_COLOUR_R_POS = 22;
    public final static int AVERAGE_COLOUR_G_POS = 23;
    public final static int AVERAGE_COLOUR_B_POS = 24;
    public final static int NEEDLE_COLOUR_R_POS = 25;
    public final static int NEEDLE_COLOUR_G_POS = 26;
    public final static int NEEDLE_COLOUR_B_POS = 27;
    public final static int VALUE_COLOUR_R_POS = 28;
    public final static int VALUE_COLOUR_G_POS = 29;
    public final static int VALUE_COLOUR_B_POS = 30;
    public final static int UNIT_COLOUR_R_POS = 31;
    public final static int UNIT_COLOUR_G_POS = 32;
    public final static int UNIT_COLOUR_B_POS = 33;
    public final static int KNOB_COLOUR_R_POS = 34;
    public final static int KNOB_COLOUR_G_POS = 35;
    public final static int KNOB_COLOUR_B_POS = 36;
    public final static int BAR_COLOUR_R_POS = 37;
    public final static int BAR_COLOUR_G_POS = 38;
    public final static int BAR_COLOUR_B_POS = 39;
    public final static int THRESHOLD_COLOUR_R_POS = 40;
    public final static int THRESHOLD_COLOUR_G_POS = 41;
    public final static int THRESHOLD_COLOUR_B_POS = 42;
    public final static int TITLE_COLOUR_R_POS = 43;
    public final static int TITLE_COLOUR_G_POS = 44;
    public final static int TITLE_COLOUR_B_POS = 45;
    public final static int BAR_BACKGROUND_COLOUR_R_POS = 46;
    public final static int BAR_BACKGROUND_COLOUR_G_POS = 47;
    public final static int BAR_BACKGROUND_COLOUR_B_POS = 48;
    public final static int FOREGROUND_COLOUR_R_POS = 49;
    public final static int FOREGROUND_COLOUR_G_POS = 50;
    public final static int FOREGROUND_COLOUR_B_POS = 51;
    public final static int TICK_LABEL_COLOUR_R_POS = 52;
    public final static int TICK_LABEL_COLOUR_G_POS = 53;
    public final static int TICK_LABEL_COLOUR_B_POS = 54;
    public final static int TICK_MARK_COLOUR_R_POS = 55;
    public final static int TICK_MARK_COLOUR_G_POS = 56;
    public final static int TICK_MARK_COLOUR_B_POS = 57;
    public final static int INITIAL_VALUE_POS = 58;
    public final static int TITLE_POS = 70;
}
