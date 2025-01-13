package dev.ymkz.presenter.book;

import dev.ymkz.domain.condition.BookSearchCondition;
import dev.ymkz.domain.value.Isbn13;
import dev.ymkz.domain.value.RangeInteger;
import dev.ymkz.usecase.book.BookSearchUsecase;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.util.Optional;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/books")
@Tag(name = "book")
public class BookResource {

  @Inject BookSearchUsecase bookSearchUsecase;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(operationId = "findBooks", summary = "booksを検索する")
  public FindBooksResponse findBooks(
      @QueryParam("isbn") String isbn,
      @QueryParam("title") String title,
      @QueryParam("priceFrom") Integer priceFrom,
      @QueryParam("priceTo") Integer priceTo,
      @QueryParam("sort") String sort,
      @QueryParam("offset") String offset,
      @QueryParam("limit") String limit) {

    var data =
        bookSearchUsecase.execute(
            new BookSearchCondition(
                new Isbn13(isbn),
                title,
                new RangeInteger(priceFrom, priceTo),
                Optional.ofNullable(offset).map(Integer::valueOf).orElse(null),
                Optional.ofNullable(limit).map(Integer::valueOf).orElse(null)));

    return FindBooksResponse.of(data);
  }
}
