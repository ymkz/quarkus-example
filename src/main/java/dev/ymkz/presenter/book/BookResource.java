package dev.ymkz.presenter.book;

import dev.ymkz.domain.model.BookSearchQuery;
import dev.ymkz.domain.value.BookOrder;
import dev.ymkz.domain.value.Isbn13;
import dev.ymkz.domain.value.RangeInteger;
import dev.ymkz.usecase.book.BookSearchUsecase;
import jakarta.inject.Inject;
import jakarta.ws.rs.DefaultValue;
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
      @QueryParam("isbn") Optional<String> isbn,
      @QueryParam("title") Optional<String> title,
      @QueryParam("priceFrom") Integer priceFrom,
      @QueryParam("priceTo") Integer priceTo,
      @QueryParam("order") @DefaultValue("-price") String order,
      @QueryParam("offset") @DefaultValue("1") Integer offset,
      @QueryParam("limit") @DefaultValue("100") Integer limit) {

    var data =
        bookSearchUsecase.execute(
            new BookSearchQuery(
                isbn.map(Isbn13::of),
                title,
                new RangeInteger(priceFrom, priceTo),
                BookOrder.of("-price"),
                offset,
                limit));

    return FindBooksResponse.of(data);
  }
}
