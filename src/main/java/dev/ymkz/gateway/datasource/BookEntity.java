package dev.ymkz.gateway.datasource;

import dev.ymkz.domain.model.Book;
import dev.ymkz.domain.value.Isbn13;

public record BookEntity(int id, String isbn, String title, int price) {

  public static Book toBook(BookEntity entity) {
    return new Book(new Isbn13(entity.isbn()), entity.title(), entity.price());
  }
}
