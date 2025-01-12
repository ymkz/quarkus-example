package dev.ymkz.presenter.book;

import static io.restassured.RestAssured.given;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
class BookResourceTest {

  @Test
  void testFindBooks() {
    given().when().get("/books").then().statusCode(200);
  }

  // @Test
  // void testFindBooksByIsbn() {
  // given()
  // .queryParam("isbn", "9784621303252")
  // .when().get("/books")
  // .then()
  // .statusCode(200);
  // }

  // @Test
  // void testFindBooksNotFound() {
  // given()
  // .queryParam("isbn", "000-0000000000")
  // .when().get("/books")
  // .then()
  // .statusCode(200)
  // .body("$.size()", is(0));
  // }
}
