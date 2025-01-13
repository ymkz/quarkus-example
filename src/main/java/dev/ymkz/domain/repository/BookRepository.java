package dev.ymkz.domain.repository;

import dev.ymkz.domain.condition.BookSearchCondition;
import dev.ymkz.domain.model.Book;
import dev.ymkz.domain.model.Pagination;

public interface BookRepository {
  Pagination<Book> findMany(BookSearchCondition condition);

  Book findByIsbn(String isbn);

  void create(Book book);

  void update(Book book);

  void delete(String isbn);
}
