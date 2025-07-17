package com.alc.bookstore.shared.infrastructure;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class JavaUUIDGeneratorShouldTest {

    private final JavaUUIDGenerator generator = new JavaUUIDGenerator();

    @Test
    void it_should_generate_a_valid_uuid() {
        String uuid = generator.generate();

        assertNotNull(uuid);
        assertDoesNotThrow(() -> UUID.fromString(uuid));
    }

    @Test
    void it_should_generate_unique_uuids() {
        Set<String> uuids = new HashSet<>();

        for (int i = 0; i < 1000; i++) {
            String uuid = generator.generate();
            assertTrue(uuids.add(uuid), "Duplicate UUID detected: " + uuid);
        }
    }
}
