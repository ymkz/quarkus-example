package dev.ymkz.presenter;

import dev.ymkz.domain.model.BookSearchQuery;
import dev.ymkz.domain.value.BookOrder;
import dev.ymkz.domain.value.Isbn;
import dev.ymkz.domain.value.RangeInteger;
import dev.ymkz.usecase.BookSearchUsecase;
import jakarta.inject.Inject;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
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
      @QueryParam("priceFrom") @PositiveOrZero Integer priceFrom,
      @QueryParam("priceTo") @PositiveOrZero Integer priceTo,
      @QueryParam("order") @DefaultValue("-price") BookOrder order,
      @QueryParam("offset") @Min(0) @DefaultValue("0") Integer offset,
      @QueryParam("limit") @Min(1) @Max(100) @DefaultValue("100") Integer limit) {

    var data =
        bookSearchUsecase.execute(
            new BookSearchQuery(
                isbn != null ? Isbn.of(isbn) : null,
                title,
                RangeInteger.of(priceFrom, priceTo),
                order,
                offset,
                limit));

    return FindBooksResponse.of(data);
  }
}
