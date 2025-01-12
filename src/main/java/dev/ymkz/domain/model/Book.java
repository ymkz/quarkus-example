package dev.ymkz.domain.model;

import dev.ymkz.domain.value.Isbn13;

public record Book(Isbn13 isbn, String title, int price) {}
