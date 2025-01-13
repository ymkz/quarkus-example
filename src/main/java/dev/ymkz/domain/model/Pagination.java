package dev.ymkz.domain.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.List;

public record Pagination<T>(
    List<T> content, @PositiveOrZero int total, @Min(1) int offset, @Min(1) @Max(100) int limit) {}
