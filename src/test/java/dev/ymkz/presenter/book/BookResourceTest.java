package dev.ymkz.presenter.book;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import dev.ymkz.domain.condition.BookSearchCondition;
import dev.ymkz.domain.model.Pagination;
import dev.ymkz.usecase.book.BookSearchUsecase;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import java.util.List;
import org.junit.jupiter.api.Test;

@QuarkusTest
class BookResourceTest {

  @InjectMock BookSearchUsecase mockBookSearchUsecase;

  @Test
  void testFindBooks() {
    when(mockBookSearchUsecase.execute(any(BookSearchCondition.class)))
        .thenReturn(new Pagination<>(List.of(), 0, 0, 0));

    given()
        // .queryParam("isbn", "9781234567897")
        // .queryParam("title", "Test Book")
        // .queryParam("priceFrom", 1000)
        // .queryParam("priceTo", 2000)
        // .queryParam("sort", "title")
        // .queryParam("offset", 0)
        // .queryParam("limit", 10)
        .when()
        .get("/api/v1/books")
        .then()
        .assertThat()
        .statusCode(200);
  }
}
