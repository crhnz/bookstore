package com.alc.bookstore.shared.domain;

import java.util.UUID;

public final class UUIDMother {
    public static String random() {
        return UUID.randomUUID().toString();
    }
}
