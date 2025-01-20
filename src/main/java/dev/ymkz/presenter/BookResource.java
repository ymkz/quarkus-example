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
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.time.Instant;
import java.util.List;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.Separator;

@Path("/books")
@Tag(name = "book")
public class BookResource {

  @Inject BookSearchUsecase bookSearchUsecase;

  @GET
  @Operation(operationId = "findBooks", summary = "書籍データを検索する")
  @APIResponses({
    @APIResponse(
        responseCode = "200",
        description = "正常系",
        content =
            @Content(
                mediaType = MediaType.APPLICATION_JSON,
                schema = @Schema(implementation = FindBooksResponse.class))),
    // @APIResponse(
    //     responseCode = "400",
    //     description = "不正なリクエスト",
    //     content =
    //         @Content(
    //             mediaType = MediaType.APPLICATION_JSON,
    //             schema = @Schema(implementation = RuntimeException.class))),
    // @APIResponse(
    //     responseCode = "500",
    //     description = "サーバーエラー",
    //     content =
    //         @Content(
    //             mediaType = MediaType.APPLICATION_JSON,
    //             schema = @Schema(implementation = RuntimeException.class)))
  })
  public FindBooksResponse findBooks(
      @QueryParam("isbn") @Parameter(description = "ISBN-13:完全一致", example = "9784873115658")
          String isbn,
      @QueryParam("title") @Parameter(description = "書籍タイトル:部分一致") String title,
      @QueryParam("priceFrom") @Parameter(description = "価格:下限") @PositiveOrZero Integer priceFrom,
      @QueryParam("priceTo") @Parameter(description = "価格:上限") @PositiveOrZero Integer priceTo,
      @QueryParam("status") @Parameter(description = "ステータス") @Separator(",")
          List<BookStatus> status,
      @QueryParam("publishedTimeStart") @Parameter(description = "出版日:開始")
          Instant publishedTimeStart,
      @QueryParam("publishedTimeEnd") @Parameter(description = "出版日:終了") Instant publishedTimeEnd,
      @QueryParam("order") @Parameter(description = "並び順") @DefaultValue("-published_at")
          BookOrder order,
      @QueryParam("offset") @Parameter(description = "取得開始位置") @Min(0) @DefaultValue("0")
          Integer offset,
      @QueryParam("limit") @Parameter(description = "取得数") @Min(1) @Max(100) @DefaultValue("100")
          Integer limit) {

    var data =
        bookSearchUsecase.execute(
            new BookSearchQuery(
                Isbn.of(isbn),
                title,
                RangeInteger.of(priceFrom, priceTo),
                status,
                RangeTime.of(publishedTimeStart, publishedTimeEnd),
                order,
                offset,
                limit));

    return FindBooksResponse.of(data);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Operation(operationId = "addBook", summary = "書籍データを作成する")
  public void addBook(AddBookRequestBody requestBody) {
    // bookAddUsecase.execute(new BookAddCommand(requestBody.title(), requestBody.price()));
  }
}
