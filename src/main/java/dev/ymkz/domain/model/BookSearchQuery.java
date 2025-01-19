package dev.ymkz.domain.model;

import dev.ymkz.domain.value.BookOrder;
import dev.ymkz.domain.value.BookStatus;
import dev.ymkz.domain.value.Isbn;
import dev.ymkz.domain.value.RangeInteger;
import dev.ymkz.domain.value.RangeTime;

public record BookSearchQuery(
    Isbn isbn,
    String title,
    RangeInteger priceRange,
    BookStatus status,
    RangeTime publishedTimeRange,
    BookOrder order,
    Integer offset,
    Integer limit) {}
