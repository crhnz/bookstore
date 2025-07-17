package com.alc.bookstore.shared.application;

import com.alc.bookstore.shared.domain.bus.query.Response;
import java.util.List;

public record PaginatedResponse<T>(List<T> resources, int total, int offset, int limit)
        implements Response {}
