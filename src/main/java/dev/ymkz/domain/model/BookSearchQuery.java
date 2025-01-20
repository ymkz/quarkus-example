package dev.ymkz.domain.model;

import dev.ymkz.domain.value.BookOrder;
import dev.ymkz.domain.value.BookStatus;
import dev.ymkz.domain.value.Isbn;
import dev.ymkz.domain.value.RangeInteger;
import dev.ymkz.domain.value.RangeTime;
import java.util.List;

public record BookSearchQuery(
    Isbn isbn,
    String title,
    RangeInteger priceRange,
    List<BookStatus> statuses,
    RangeTime publishedTimeRange,
    BookOrder order,
    Integer offset,
    Integer limit) {}
