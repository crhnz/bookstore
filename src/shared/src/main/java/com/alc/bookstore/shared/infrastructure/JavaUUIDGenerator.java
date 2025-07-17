package com.alc.bookstore.shared.infrastructure;

import com.alc.bookstore.shared.domain.UUIDGenerator;
import java.util.UUID;

public final class JavaUUIDGenerator implements UUIDGenerator {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
