package com.alc.bookstore.shared.domain.vo;

import java.io.Serializable;
import java.util.UUID;
import lombok.*;

@EqualsAndHashCode
@Getter
@ToString
public abstract class Identifier implements Serializable {

    protected final String value;

    public Identifier(final String value) {
        ensureValidUuid(value);

        this.value = value;
    }

    private void ensureValidUuid(final String value) throws IllegalArgumentException {
        if (value == null) {
            throw new IllegalArgumentException("UUID value cannot be null");
        }

        UUID.fromString(value);
    }
}
