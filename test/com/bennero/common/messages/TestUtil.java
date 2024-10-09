package com.bennero.common.messages;

import java.util.Random;

public class TestUtil {
    public static byte[] randomIp4() {
        Random random = new Random(System.currentTimeMillis());
        byte[] bytes = new byte[4];
        random.nextBytes(bytes);
        return bytes;
    }

    public static byte[] randomMacAddress() {
        Random random = new Random(System.currentTimeMillis());
        byte[] bytes = new byte[6];
        random.nextBytes(bytes);
        return bytes;
    }
}
