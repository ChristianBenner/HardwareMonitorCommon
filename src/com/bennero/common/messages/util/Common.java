package com.bennero.common.messages.util;

import javafx.scene.paint.Color;

public class Common {
    public static byte[] colorToBytes(Color color) {
        return new byte[]{
                (byte)(color.getRed() * 255.0),
                (byte)(color.getGreen() * 255.0),
                (byte)(color.getBlue() * 255.0)
        };
    }
}
