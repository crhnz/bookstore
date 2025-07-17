package com.alc.bookstore.shared.domain.vo;

import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public abstract class StringValueObject {
    private final String value;
}
