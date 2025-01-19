package dev.ymkz.presenter;

import dev.ymkz.domain.model.BookSearchQuery;
import dev.ymkz.domain.value.BookOrder;
import dev.ymkz.domain.value.BookStatus;
import dev.ymkz.domain.value.Isbn;
import dev.ymkz.domain.value.RangeInteger;
import dev.ymkz.domain.value.RangeTime;
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
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/books")
@Tag(name = "book")
public class BookResource {

  @Inject BookSearchUsecase bookSearchUsecase;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(operationId = "findBooks", summary = "booksを検索する")
  public FindBooksResponse findBooks(
      @QueryParam("isbn") @Parameter(description = "ISBN-13:完全一致", example = "9784873115658")
          String isbn,
      @QueryParam("title") @Parameter(description = "書籍タイトル:部分一致") String title,
      @QueryParam("priceFrom") @Parameter(description = "価格:下限") @PositiveOrZero Integer priceFrom,
      @QueryParam("priceTo") @Parameter(description = "価格:上限") @PositiveOrZero Integer priceTo,
      @QueryParam("status") @Parameter(description = "ステータス") BookStatus status,
      @QueryParam("publishedTimeStart")
          @Parameter(description = "出版日:開始", example = "2025-01-01T00:00:00Z")
          Instant publishedTimeStart,
      @QueryParam("publishedTimeEnd")
          @Parameter(description = "出版日:終了", example = "2025-01-01T00:00:00Z")
          Instant publishedTimeEnd,
      @QueryParam("order") @Parameter(description = "並び順") @DefaultValue("-price")
          BookOrder order,
      @QueryParam("offset") @Parameter(description = "取得開始位置") @Min(0) @DefaultValue("0")
          Integer offset,
      @QueryParam("limit") @Parameter(description = "取得数") @Min(1) @Max(100) @DefaultValue("100")
          Integer limit) {

    var data =
        bookSearchUsecase.execute(
            new BookSearchQuery(
                isbn != null ? Isbn.of(isbn) : null,
                title,
                RangeInteger.of(priceFrom, priceTo),
                status,
                RangeTime.of(
                    publishedTimeStart != null
                        ? LocalDateTime.ofInstant(publishedTimeStart, ZoneId.of("Asia/Tokyo"))
                        : null,
                    publishedTimeEnd != null
                        ? LocalDateTime.ofInstant(publishedTimeEnd, ZoneId.of("Asia/Tokyo"))
                        : null),
                order,
                offset,
                limit));

    return FindBooksResponse.of(data);
  }
}
