package dev.ymkz.domain.model;

import dev.ymkz.domain.value.BookOrder;
import dev.ymkz.domain.value.Isbn13;
import dev.ymkz.domain.value.RangeInteger;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.util.Optional;

public record BookSearchQuery(
    Optional<Isbn13> isbn,
    Optional<String> title,
    RangeInteger priceRange,
    BookOrder order,
    @Min(1) Integer offset,
    @Min(1) @Max(100) Integer limit) {}
