package dev.ymkz.presenter.book;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import dev.ymkz.domain.model.BookSearchQuery;
import dev.ymkz.domain.value.Pagination;
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
    when(mockBookSearchUsecase.execute(any(BookSearchQuery.class)))
        .thenReturn(new Pagination<>(List.of(), 0, 1, 100));

    given().when().get("/api/v1/books").then().assertThat().statusCode(200);
  }
}
