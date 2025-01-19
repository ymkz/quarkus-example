package dev.ymkz.domain.model;

import dev.ymkz.domain.value.BookOrder;
import dev.ymkz.domain.value.Isbn;
import dev.ymkz.domain.value.RangeInteger;

public record BookSearchQuery(
    Isbn isbn,
    String title,
    RangeInteger priceRange,
    BookOrder order,
    Integer offset,
    Integer limit) {}
