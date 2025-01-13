package dev.ymkz.usecase.book;

import dev.ymkz.domain.condition.BookSearchCondition;
import dev.ymkz.domain.model.Book;
import dev.ymkz.domain.model.Pagination;
import dev.ymkz.domain.repository.BookRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BookSearchUsecase {

  @Inject BookRepository repository;

  public Pagination<Book> execute(BookSearchCondition condition) {
    return repository.findMany(condition);
  }
}
