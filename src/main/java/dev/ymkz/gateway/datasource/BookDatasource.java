package dev.ymkz.gateway.datasource;

import dev.ymkz.domain.condition.BookSearchCondition;
import dev.ymkz.domain.model.Book;
import dev.ymkz.domain.model.Pagination;
import dev.ymkz.domain.repository.BookRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class BookDatasource implements BookRepository {

  @Inject BookMapper mapper;

  @Override
  public Pagination<Book> findMany(BookSearchCondition condition) {
    var total = mapper.count(condition);
    var content = mapper.list(condition).stream().map(BookEntity::toBook).toList();
    return new Pagination<>(content, total, condition.offset(), condition.limit());
  }

  @Override
  public Book findByIsbn(String isbn) {
    return null;
  }

  @Override
  public void create(Book book) {}

  @Override
  public void update(Book book) {}

  @Override
  public void delete(String isbn) {}
}
