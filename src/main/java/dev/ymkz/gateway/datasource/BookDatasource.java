package dev.ymkz.gateway.datasource;

import dev.ymkz.domain.model.Book;
import dev.ymkz.domain.model.BookSearchQuery;
import dev.ymkz.domain.repository.BookRepository;
import dev.ymkz.domain.value.Isbn13;
import dev.ymkz.domain.value.Pagination;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class BookDatasource implements BookRepository {

  @Inject BookMapper mapper;

  @Override
  public Pagination<Book> findMany(BookSearchQuery query) {
    Log.info(query.toString());
    var total = mapper.count(query);
    var content = mapper.list(query).stream().map(BookEntity::toBook).toList();
    Log.info("total: " + total + ", content: " + content);
    return new Pagination<>(content, total, query.offset(), query.limit());
  }

  @Override
  public Book findByIsbn(Isbn13 isbn) {
    return null;
  }

  @Override
  public void create(Book book) {
    return;
  }

  @Override
  public void update(Book book) {
    return;
  }

  @Override
  public void delete(Isbn13 isbn) {
    return;
  }
}
