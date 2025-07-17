package com.alc.bookstore.shared.domain.vo;

import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public abstract class IntValueObject {
    private final Integer value;
}
