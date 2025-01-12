package dev.ymkz.presenter.book;

import dev.ymkz.domain.model.Book;
import dev.ymkz.domain.model.Pagination;
import java.util.List;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "findBooksResponse")
public record FindBookResponse(Pagination pagination, List<FindBookResponseHit> hits) {

  public record FindBookResponseHit(String isbn, String title, int price) {

    private static FindBookResponseHit of(Book book) {
      return new FindBookResponseHit(book.isbn().value(), book.title(), book.price());
    }
  }

  public static FindBookResponse of(List<Book> books) {
    return new FindBookResponse(
        new Pagination(0, 0, 0), books.stream().map(FindBookResponseHit::of).toList());
  }
}
