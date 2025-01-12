package dev.ymkz.presenter.book;

import dev.ymkz.domain.model.Book;
import dev.ymkz.domain.value.Isbn13;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/books")
public class BookResource {

  private List<Book> books =
      List.of(new Book(new Isbn13("9784621303252"), "Effective Java 第3版", 4400));

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public FindBookResponse findBooks(
      @QueryParam("isbn") String isbn, @QueryParam("title") String title) {
    return FindBookResponse.of(books);
  }
}
