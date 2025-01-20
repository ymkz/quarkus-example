package dev.ymkz.presenter;

import dev.ymkz.domain.value.BookOrder;
import dev.ymkz.domain.value.BookStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.QueryParam;
import java.time.Instant;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

@Schema(description = "書籍検索クエリパラメータ")
public record FindBooksQueryParam(
    @QueryParam("isbn") @Parameter(description = "ISBN-13:完全一致", example = "9784873115658")
        String isbn,
    @QueryParam("title") @Parameter(description = "書籍タイトル:部分一致") String title,
    @QueryParam("priceFrom") @Parameter(description = "価格:下限") @PositiveOrZero Integer priceFrom,
    @QueryParam("priceTo") @Parameter(description = "価格:上限") @PositiveOrZero Integer priceTo,
    @QueryParam("status") @Parameter(description = "ステータス") BookStatus status,
    @QueryParam("publishedTimeStart") @Parameter(description = "出版日:開始") Instant publishedTimeStart,
    @QueryParam("publishedTimeEnd") @Parameter(description = "出版日:終了") Instant publishedTimeEnd,
    @QueryParam("order") @Parameter(description = "並び順") @DefaultValue("-published_at")
        BookOrder order,
    @QueryParam("offset") @Parameter(description = "取得開始位置") @Min(0) @DefaultValue("0")
        Integer offset,
    @QueryParam("limit") @Parameter(description = "取得数") @Min(1) @Max(100) @DefaultValue("100")
        Integer limit) {}
