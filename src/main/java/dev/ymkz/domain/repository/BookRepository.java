package dev.ymkz.domain.repository;

import dev.ymkz.domain.model.Book;
import java.util.List;

public interface BookRepository {
  List<Book> findMany(String isbn, String title);

  Book findOne(String isbn);

  void create(Book book);

  void update(Book book);

  void delete(String isbn);
}
