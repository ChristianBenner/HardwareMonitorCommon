/*
 * ============================================ GNU GENERAL PUBLIC LICENSE =============================================
 * Hardware Monitor for the remote monitoring of a systems hardware information
 * Copyright (C) 2021  Christian Benner
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * An additional term included with this license is the requirement to preserve legal notices and author attributions
 * such as this one. Do not remove the original author license notices from the program unless given permission from
 * the original author: christianbenner35@gmail.com
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

import java.util.ArrayList;
import java.util.List;

import static com.bennero.common.Skin.*;

/**
 * Associates a Gauge skin enum value (defined in the medusa library) with its unique ID (defined in Skin class). The
 * SkinHelper also provides names for each skin, and flags that define each skins configurable properties.
 *
 * @see         Skin
 * @author      Christian Benner
 * @version     %I%, %G%
 * @since       1.0
 */
public class SkinHelper
{
    private static SkinHelper instance = null;

    private List<Skin> skins;

    private SkinHelper()
    {
        skins = new ArrayList<>();

        skins.add(new Skin(Skin.SPACE, "Space", Gauge.SkinType.SPACE_X, VALUE_COLOUR_SUPPORTED | UNIT_COLOUR_SUPPORTED | BAR_COLOUR_SUPPORTED | THRESHOLD_COLOUR_SUPPORTED | TITLE_COLOUR_SUPPORTED | FOREGROUND_BASE_COLOUR_SUPPORTED));
        skins.add(new Skin(Skin.GRAPH, "Graph", Gauge.SkinType.TILE_SPARK_LINE, AVERAGE_COLOUR_SUPPORTED | VALUE_COLOUR_SUPPORTED | BAR_COLOUR_SUPPORTED | TITLE_COLOUR_SUPPORTED | FOREGROUND_BASE_COLOUR_SUPPORTED));
        skins.add(new Skin(Skin.FLAT, "Flat", Gauge.SkinType.FLAT, VALUE_COLOUR_SUPPORTED | UNIT_COLOUR_SUPPORTED | BAR_COLOUR_SUPPORTED | TITLE_COLOUR_SUPPORTED | FOREGROUND_BASE_COLOUR_SUPPORTED));
        skins.add(new Skin(Skin.MODERN, "Modern", Gauge.SkinType.MODERN, NEEDLE_COLOUR_SUPPORTED | VALUE_COLOUR_SUPPORTED | BAR_COLOUR_SUPPORTED | TITLE_COLOUR_SUPPORTED | FOREGROUND_BASE_COLOUR_SUPPORTED | TICK_LABEL_COLOUR_SUPPORTED | TICK_MARK_COLOUR_SUPPORTED));
        skins.add(new Skin(Skin.AMP, "Amp", Gauge.SkinType.AMP, NEEDLE_COLOUR_SUPPORTED | UNIT_COLOUR_SUPPORTED | TITLE_COLOUR_SUPPORTED | FOREGROUND_BASE_COLOUR_SUPPORTED | TICK_LABEL_COLOUR_SUPPORTED | TICK_MARK_COLOUR_SUPPORTED));
        skins.add(new Skin(Skin.PLAIN_AMP, "Plain Amp", Gauge.SkinType.PLAIN_AMP, NEEDLE_COLOUR_SUPPORTED | UNIT_COLOUR_SUPPORTED | FOREGROUND_BASE_COLOUR_SUPPORTED | TICK_LABEL_COLOUR_SUPPORTED | TICK_MARK_COLOUR_SUPPORTED));
        skins.add(new Skin(Skin.BULLET_CHART, "Bullet Chart", Gauge.SkinType.BULLET_CHART, BAR_COLOUR_SUPPORTED | THRESHOLD_COLOUR_SUPPORTED | FOREGROUND_BASE_COLOUR_SUPPORTED | TICK_LABEL_COLOUR_SUPPORTED | TICK_MARK_COLOUR_SUPPORTED));
        skins.add(new Skin(Skin.DASHBOARD, "Dashboard", Gauge.SkinType.DASHBOARD, VALUE_COLOUR_SUPPORTED | UNIT_COLOUR_SUPPORTED | BAR_COLOUR_SUPPORTED | TITLE_COLOUR_SUPPORTED | BAR_BACKGROUND_COLOUR_SUPPORTED | FOREGROUND_BASE_COLOUR_SUPPORTED));
        skins.add(new Skin(Skin.GAUGE, "Gauge", Gauge.SkinType.GAUGE, NEEDLE_COLOUR_SUPPORTED | VALUE_COLOUR_SUPPORTED | UNIT_COLOUR_SUPPORTED | KNOB_COLOUR_SUPPORTED | TITLE_COLOUR_SUPPORTED | FOREGROUND_BASE_COLOUR_SUPPORTED | TICK_LABEL_COLOUR_SUPPORTED));
        skins.add(new Skin(Skin.INDICATOR, "Indicator", Gauge.SkinType.INDICATOR, NEEDLE_COLOUR_SUPPORTED | BAR_COLOUR_SUPPORTED | BAR_BACKGROUND_COLOUR_SUPPORTED));
        skins.add(new Skin(Skin.KPI, "KPI", Gauge.SkinType.KPI, NEEDLE_COLOUR_SUPPORTED | VALUE_COLOUR_SUPPORTED | BAR_COLOUR_SUPPORTED | THRESHOLD_COLOUR_SUPPORTED | FOREGROUND_BASE_COLOUR_SUPPORTED));
        skins.add(new Skin(Skin.SIMPLE, "Simple", Gauge.SkinType.SIMPLE, NEEDLE_COLOUR_SUPPORTED | VALUE_COLOUR_SUPPORTED | TITLE_COLOUR_SUPPORTED | FOREGROUND_BASE_COLOUR_SUPPORTED | TICK_LABEL_COLOUR_SUPPORTED));
        skins.add(new Skin(Skin.SLIM, "Slim", Gauge.SkinType.SLIM, VALUE_COLOUR_SUPPORTED | UNIT_COLOUR_SUPPORTED | BAR_COLOUR_SUPPORTED | TITLE_COLOUR_SUPPORTED | BAR_BACKGROUND_COLOUR_SUPPORTED | FOREGROUND_BASE_COLOUR_SUPPORTED));
        skins.add(new Skin(Skin.QUARTER, "Quarter", Gauge.SkinType.QUARTER, AVERAGE_COLOUR_SUPPORTED | NEEDLE_COLOUR_SUPPORTED | VALUE_COLOUR_SUPPORTED | UNIT_COLOUR_SUPPORTED | KNOB_COLOUR_SUPPORTED | TITLE_COLOUR_SUPPORTED | FOREGROUND_BASE_COLOUR_SUPPORTED | TICK_LABEL_COLOUR_SUPPORTED));
        skins.add(new Skin(Skin.HORIZONTAL, "Horizontal", Gauge.SkinType.HORIZONTAL, AVERAGE_COLOUR_SUPPORTED | NEEDLE_COLOUR_SUPPORTED | VALUE_COLOUR_SUPPORTED | UNIT_COLOUR_SUPPORTED | KNOB_COLOUR_SUPPORTED | TITLE_COLOUR_SUPPORTED | FOREGROUND_BASE_COLOUR_SUPPORTED | TICK_LABEL_COLOUR_SUPPORTED));
        skins.add(new Skin(Skin.VERTICAL, "Vertical", Gauge.SkinType.VERTICAL, AVERAGE_COLOUR_SUPPORTED | NEEDLE_COLOUR_SUPPORTED | VALUE_COLOUR_SUPPORTED | UNIT_COLOUR_SUPPORTED | KNOB_COLOUR_SUPPORTED | TITLE_COLOUR_SUPPORTED | FOREGROUND_BASE_COLOUR_SUPPORTED | TICK_LABEL_COLOUR_SUPPORTED));
        skins.add(new Skin(Skin.LCD, "LCD", Gauge.SkinType.LCD, NO_SUPPORT));
        skins.add(new Skin(Skin.TINY, "Tiny", Gauge.SkinType.TINY, NEEDLE_COLOUR_SUPPORTED | BAR_BACKGROUND_COLOUR_SUPPORTED | FOREGROUND_BASE_COLOUR_SUPPORTED));
        skins.add(new Skin(Skin.BATTERY, "Battery", Gauge.SkinType.BATTERY, VALUE_COLOUR_SUPPORTED | BAR_COLOUR_SUPPORTED | BAR_BACKGROUND_COLOUR_SUPPORTED | FOREGROUND_BASE_COLOUR_SUPPORTED));
        skins.add(new Skin(Skin.LEVEL, "Level", Gauge.SkinType.LEVEL, VALUE_COLOUR_SUPPORTED | BAR_COLOUR_SUPPORTED | TITLE_COLOUR_SUPPORTED | FOREGROUND_BASE_COLOUR_SUPPORTED));
        skins.add(new Skin(Skin.LINEAR, "Linear", Gauge.SkinType.LINEAR, VALUE_COLOUR_SUPPORTED | UNIT_COLOUR_SUPPORTED | BAR_COLOUR_SUPPORTED | TITLE_COLOUR_SUPPORTED | FOREGROUND_BASE_COLOUR_SUPPORTED | TICK_LABEL_COLOUR_SUPPORTED | TICK_MARK_COLOUR_SUPPORTED));
        skins.add(new Skin(Skin.DIGITAL, "Digital", Gauge.SkinType.DIGITAL, VALUE_COLOUR_SUPPORTED | UNIT_COLOUR_SUPPORTED | BAR_COLOUR_SUPPORTED | TITLE_COLOUR_SUPPORTED | FOREGROUND_BASE_COLOUR_SUPPORTED | TICK_LABEL_COLOUR_SUPPORTED | TICK_MARK_COLOUR_SUPPORTED));
        skins.add(new Skin(Skin.SIMPLE_DIGITAL, "Simple Digital", Gauge.SkinType.SIMPLE_DIGITAL, VALUE_COLOUR_SUPPORTED | UNIT_COLOUR_SUPPORTED | BAR_COLOUR_SUPPORTED | TITLE_COLOUR_SUPPORTED | BAR_BACKGROUND_COLOUR_SUPPORTED | FOREGROUND_BASE_COLOUR_SUPPORTED));
        skins.add(new Skin(Skin.SECTION, "Section", Gauge.SkinType.SECTION, NEEDLE_COLOUR_SUPPORTED | KNOB_COLOUR_SUPPORTED | TITLE_COLOUR_SUPPORTED | FOREGROUND_BASE_COLOUR_SUPPORTED));
        skins.add(new Skin(Skin.BAR, "Bar", Gauge.SkinType.BAR, VALUE_COLOUR_SUPPORTED | BAR_COLOUR_SUPPORTED | TITLE_COLOUR_SUPPORTED | FOREGROUND_BASE_COLOUR_SUPPORTED));
        skins.add(new Skin(Skin.WHITE, "White", Gauge.SkinType.WHITE, VALUE_COLOUR_SUPPORTED | UNIT_COLOUR_SUPPORTED | FOREGROUND_BASE_COLOUR_SUPPORTED));
        skins.add(new Skin(Skin.CHARGE, "Charge", Gauge.SkinType.CHARGE, NO_SUPPORT));
        skins.add(new Skin(Skin.SIMPLE_SELECTION, "Simple Section", Gauge.SkinType.SIMPLE_SECTION, VALUE_COLOUR_SUPPORTED | UNIT_COLOUR_SUPPORTED | BAR_COLOUR_SUPPORTED | TITLE_COLOUR_SUPPORTED | BAR_BACKGROUND_COLOUR_SUPPORTED | FOREGROUND_BASE_COLOUR_SUPPORTED));
        skins.add(new Skin(Skin.TILE_KPI, "Tile KPI", Gauge.SkinType.TILE_KPI, NEEDLE_COLOUR_SUPPORTED | VALUE_COLOUR_SUPPORTED | BAR_COLOUR_SUPPORTED | THRESHOLD_COLOUR_SUPPORTED | TITLE_COLOUR_SUPPORTED | FOREGROUND_BASE_COLOUR_SUPPORTED));
        skins.add(new Skin(Skin.TILE_TEXT_KPI, "Tile Text KPI", Gauge.SkinType.TILE_TEXT_KPI, BAR_COLOUR_SUPPORTED | THRESHOLD_COLOUR_SUPPORTED | BAR_BACKGROUND_COLOUR_SUPPORTED | FOREGROUND_BASE_COLOUR_SUPPORTED));
    }

    public static SkinHelper getInstance()
    {
        if (instance == null)
        {
            // Init
            instance = new SkinHelper();
        }

        return instance;
    }

    public static boolean containsSkin(String skin)
    {
        List<Skin> skins = SkinHelper.getInstance().getSkins();

        // Search array
        boolean found = false;

        for (int i = 0; i < skins.size() && !found; i++)
        {
            if (skins.get(i).getName() == skin)
            {
                found = true;
            }
        }

        return found;
    }

    public static boolean containsSkin(byte skin)
    {
        List<Skin> skins = SkinHelper.getInstance().getSkins();

        // Search array
        boolean found = false;

        for (int i = 0; i < skins.size() && !found; i++)
        {
            if (skins.get(i).getCode() == skin)
            {
                found = true;
            }
        }

        return found;
    }

    public static byte getByteCode(String skin)
    {
        List<Skin> skins = SkinHelper.getInstance().getSkins();

        for (int i = 0; i < skins.size(); i++)
        {
            if (skins.get(i).getName() == skin)
            {
                return skins.get(i).getCode();
            }
        }

        return Skin.SPACE;
    }

    public static String getString(byte skin)
    {
        List<Skin> skins = SkinHelper.getInstance().getSkins();

        for (int i = 0; i < skins.size(); i++)
        {
            if (skins.get(i).getCode() == skin)
            {
                return skins.get(i).getName();
            }
        }

        return new String();
    }

    public static Gauge.SkinType getSkinType(final byte skin)
    {
        List<Skin> skins = SkinHelper.getInstance().getSkins();

        for (int i = 0; i < skins.size(); i++)
        {
            if (skins.get(i).getCode() == skin)
            {
                return skins.get(i).getSkinType();
            }
        }

        return Gauge.SkinType.SPACE_X;
    }

    public static boolean checkSupport(final String skin, final int supportFlag)
    {
        List<Skin> skins = SkinHelper.getInstance().getSkins();

        for (int i = 0; i < skins.size(); i++)
        {
            if (skins.get(i).getName() == skin)
            {
                return skins.get(i).checkSupport(supportFlag);
            }
        }

        return false;
    }

    public static boolean checkSupport(final byte skin, final int supportFlag)
    {
        List<Skin> skins = SkinHelper.getInstance().getSkins();

        for (int i = 0; i < skins.size(); i++)
        {
            if (skins.get(i).getCode() == skin)
            {
                return skins.get(i).checkSupport(supportFlag);
            }
        }

        return false;
    }

    public static List<String> getNames()
    {
        List<Skin> skins = SkinHelper.getInstance().getSkins();

        List<String> names = new ArrayList<>();

        for (int i = 0; i < skins.size(); i++)
        {
            names.add(skins.get(i).getName());
        }

        return names;
    }

    public static List<Gauge.SkinType> getSkinTypes()
    {
        List<Skin> skins = SkinHelper.getInstance().getSkins();

        List<Gauge.SkinType> skinsTypes = new ArrayList<>();

        for (int i = 0; i < skins.size(); i++)
        {
            skinsTypes.add(skins.get(i).getSkinType());
        }

        return skinsTypes;
    }

    private List<Skin> getSkins()
    {
        return skins;
    }
}
