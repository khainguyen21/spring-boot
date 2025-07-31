package com.amigoscode.product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record UpdateProductRequest(
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
        String name,
        @Size(min = 5, max = 500, message = "Description must be between 5 and 500 characters")
        String description,

        String imageUrl,

        @DecimalMin(value = "0.01", message = "Price must be greater than 0.01")
        BigDecimal price,

        @Min(value = 1, message = "Min Stock Level is 1")
        Integer stockLevel
) {
}
