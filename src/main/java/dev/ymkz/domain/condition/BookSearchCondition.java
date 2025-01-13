package dev.ymkz.domain.condition;

import dev.ymkz.domain.value.Isbn13;
import dev.ymkz.domain.value.RangeInteger;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record BookSearchCondition(
    Isbn13 isbn13,
    String title,
    RangeInteger priceRange,
    @Min(1) Integer offset,
    @Min(1) @Max(100) Integer limit) {}
