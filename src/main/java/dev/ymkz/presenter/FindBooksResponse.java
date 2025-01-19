package dev.ymkz.presenter;

import dev.ymkz.domain.model.Book;
import dev.ymkz.domain.value.BookStatus;
import java.util.List;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "findBooksResponse")
public record FindBooksResponse(Pagination pagination, List<Hit> hits) {

  record Pagination(int offset, int limit, int total) {}

  record Hit(
      @Schema(example = "1") long id,
      @Schema(example = "9784873115658") String isbn,
      @Schema(example = "リーダブルコード") String title,
      @Schema(example = "2640") Integer price,
      @Schema(example = "PUBLISHED") BookStatus status,
      @Schema(example = "1") int authorId,
      @Schema(example = "Dustin Boswell") String authorName,
      @Schema(example = "1") int publisherId,
      @Schema(example = "O'Reilly") String publisherName) {

    private static Hit of(Book book) {
      return new Hit(
          book.id(),
          book.isbn().value(),
          book.title(),
          book.price(),
          book.status(),
          book.authorId(),
          book.authorName(),
          book.publisherId(),
          book.publisherName());
    }
  }

  public static FindBooksResponse of(dev.ymkz.domain.value.Pagination<Book> data) {
    return new FindBooksResponse(
        new Pagination(data.offset(), data.limit(), data.total()),
        data.content().stream().map(Hit::of).toList());
  }
}
