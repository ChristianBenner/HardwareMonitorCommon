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
 * Defines a page data messages data structure (position of each value within the message data)
 *
 * @author Christian Benner
 * @version %I%, %G%
 * @since 1.0
 */
public class PageDataPositions {
    public final static int ID_POS = 1;
    public final static int COLOUR_R_POS = 2;
    public final static int COLOUR_G_POS = 3;
    public final static int COLOUR_B_POS = 4;
    public final static int TITLE_COLOUR_R_POS = 5;
    public final static int TITLE_COLOUR_G_POS = 6;
    public final static int TITLE_COLOUR_B_POS = 7;
    public final static int SUBTITLE_COLOUR_R_POS = 8;
    public final static int SUBTITLE_COLOUR_G_POS = 9;
    public final static int SUBTITLE_COLOUR_B_POS = 10;
    public final static int ROWS_POS = 11;
    public final static int COLUMNS_POS = 12;
    public final static int NEXT_ID_POS = 13;
    public final static int TRANSITION_TYPE_POS = 14;
    public final static int TRANSITION_TIME_POS = 15;
    public final static int DURATION_MS_POS = 19;
    public final static int TITLE_POS = 23;
    public final static int TITLE_ENABLED_POS = 87;
    public final static int TITLE_ALIGNMENT_POS = 88;
    public final static int SUBTITLE_POS = 89;
    public final static int SUBTITLE_POS_ENABLED_POS = 153;
    public final static int SUBTITLE_POS_ALIGNMENT_POS = 154;
}