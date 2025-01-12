package dev.ymkz.domain.model;

import java.util.List;

public record PaginatedBook(Pagination pagination, List<Book> books) {}
