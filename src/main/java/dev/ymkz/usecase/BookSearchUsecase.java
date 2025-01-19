package dev.ymkz.usecase;

import dev.ymkz.domain.model.Book;
import dev.ymkz.domain.model.BookSearchQuery;
import dev.ymkz.domain.repository.BookRepository;
import dev.ymkz.domain.value.Pagination;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BookSearchUsecase {

  @Inject BookRepository repository;

  public Pagination<Book> execute(BookSearchQuery query) {
    return repository.findMany(query);
  }
}
