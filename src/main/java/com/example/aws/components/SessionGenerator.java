package com.example.aws.components;

import java.security.SecureRandom;

public class SessionGenerator {

    private static final String CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom random = new SecureRandom();

    public static String generateSessionString() {
        StringBuilder stringBuilder = new StringBuilder(128);
        for (int i = 0; i < 128; i++) {
            stringBuilder.append(CHARSET.charAt(random.nextInt(CHARSET.length())));
        }
        return stringBuilder.toString();
    }
}
