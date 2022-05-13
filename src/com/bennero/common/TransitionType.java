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

import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.Random;

/**
 * Constants for different types of transitions. Each transition has a unique ID represented as a byte for efficient
 * usage in networking or other message protocols. The class can also create transition objects that can be used on
 * user interface components.
 *
 * @author Christian Benner
 * @version %I%, %G%
 * @since 1.0
 */
public class TransitionType {
    public final static int NUM_TRANSITIONS = 6;

    public final static byte CUT = 0x00;
    public final static byte SWIPE_LEFT = 0x01;
    public final static byte SWIPE_RIGHT = 0x02;
    public final static byte SWIPE_DOWN = 0x03;
    public final static byte SWIPE_UP = 0x04;
    public final static byte FADE = 0x05;

    public static byte getRandomTransition(Random random) {
        return (byte) random.nextInt(NUM_TRANSITIONS);
    }

    public static Transition getTransition(final int value,
                                           final int durationMs,
                                           final StackPane root,
                                           final Node finalNext) {
        switch (value) {
            default:
            case TransitionType.CUT:
                return null;
            case TransitionType.SWIPE_LEFT:
                TranslateTransition swipeLeft = new TranslateTransition(Duration.millis(durationMs), finalNext);
                swipeLeft.setFromX(root.getWidth());
                swipeLeft.setToX(0);
                return swipeLeft;
            case TransitionType.SWIPE_RIGHT:
                TranslateTransition swipeRight = new TranslateTransition(Duration.millis(durationMs), finalNext);
                swipeRight.setFromX(-root.getWidth());
                swipeRight.setToX(0);
                return swipeRight;
            case TransitionType.SWIPE_DOWN:
                TranslateTransition swipeDown = new TranslateTransition(Duration.millis(durationMs), finalNext);
                swipeDown.setFromY(-root.getHeight());
                swipeDown.setToY(0);
                return swipeDown;
            case TransitionType.SWIPE_UP:
                TranslateTransition swipeUp = new TranslateTransition(Duration.millis(durationMs), finalNext);
                swipeUp.setFromY(root.getHeight());
                swipeUp.setToY(0);
                return swipeUp;
            case TransitionType.FADE:
                FadeTransition ft = new FadeTransition(Duration.millis(durationMs), finalNext);
                ft.setFromValue(0.0);
                ft.setToValue(1.0);
                return ft;
        }
    }
}