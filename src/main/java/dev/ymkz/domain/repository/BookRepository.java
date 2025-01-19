package dev.ymkz.domain.repository;

import dev.ymkz.domain.model.Book;
import dev.ymkz.domain.model.BookSearchQuery;
import dev.ymkz.domain.value.Isbn;
import dev.ymkz.domain.value.Pagination;

public interface BookRepository {
  Pagination<Book> findMany(BookSearchQuery query);

  Book findByIsbn(Isbn isbn);

  void create(Book book);

  void update(Book book);

  void delete(Isbn isbn);
}
