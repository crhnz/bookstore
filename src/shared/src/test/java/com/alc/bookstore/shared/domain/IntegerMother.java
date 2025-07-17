package com.alc.bookstore.shared.domain;

import java.util.concurrent.ThreadLocalRandom;

public final class IntegerMother {
    public static Integer random() {
        return MotherCreator.random().number().randomDigit();
    }

    public static Integer random(int max) {
        return ThreadLocalRandom.current().nextInt(max);
    }
}
