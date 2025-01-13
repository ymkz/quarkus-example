package dev.ymkz.presenter.book;

import dev.ymkz.domain.model.Book;
import java.util.List;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "findBooksResponse")
public record FindBooksResponse(Pagination pagination, List<Hit> hits) {

  record Pagination(int offset, int limit, int total) {}

  record Hit(
      @Schema(example = "9784621303252") String isbn,
      @Schema(example = "Effective Java 第3版") String title,
      @Schema(example = "4400") int price) {

    private static Hit of(Book book) {
      return new Hit(book.isbn().value(), book.title(), book.price());
    }
  }

  public static FindBooksResponse of(dev.ymkz.domain.model.Pagination<Book> data) {
    return new FindBooksResponse(
        new Pagination(data.offset(), data.limit(), data.total()),
        data.content().stream().map(Hit::of).toList());
  }
}
