package com.amigoscode.product;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record ProductResponse(
        UUID id,
        String name,
        String description,
        BigDecimal price,
        String imageUrl,
        int stockLevel,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt) {
}
