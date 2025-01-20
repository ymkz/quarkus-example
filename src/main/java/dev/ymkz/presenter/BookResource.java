package dev.ymkz.presenter;

import dev.ymkz.domain.model.BookSearchQuery;
import dev.ymkz.domain.value.Isbn;
import dev.ymkz.domain.value.RangeInteger;
import dev.ymkz.domain.value.RangeTime;
import dev.ymkz.usecase.BookSearchUsecase;
import jakarta.inject.Inject;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

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
  public FindBooksResponse findBooks(@BeanParam FindBooksQueryParam queryParam) {

    var data =
        bookSearchUsecase.execute(
            new BookSearchQuery(
                queryParam.isbn() != null ? Isbn.of(queryParam.isbn()) : null,
                queryParam.title(),
                RangeInteger.of(queryParam.priceFrom(), queryParam.priceTo()),
                queryParam.status(),
                RangeTime.of(queryParam.publishedTimeStart(), queryParam.publishedTimeEnd()),
                queryParam.order(),
                queryParam.offset(),
                queryParam.limit()));

    return FindBooksResponse.of(data);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Operation(operationId = "addBook", summary = "書籍データを作成する")
  public void addBook(AddBookRequestBody requestBody) {
    // bookAddUsecase.execute(new BookAddCommand(requestBody.title(), requestBody.price()));
  }
}
