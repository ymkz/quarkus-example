package dev.ymkz.usecase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import dev.ymkz.domain.model.Book;
import dev.ymkz.domain.model.BookSearchQuery;
import dev.ymkz.domain.repository.BookRepository;
import dev.ymkz.domain.value.Pagination;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@Singleton
public class BookSearchUsecaseTest {

  @Inject BookSearchUsecase usecase;

  BookRepository repository;

  @BeforeEach
  void setUp() {
    repository = Mockito.mock(BookRepository.class);
    usecase = new BookSearchUsecase();
    usecase.repository = repository;
  }

  @Test
  void 書籍検索ユースケースが正しく実行される() {
    // given
    BookSearchQuery query = new BookSearchQuery(null, "テストタイトル", null, null, null, null, 0, 10);
    List<Book> books =
        List.of(
            new Book(
                1L, null, "テストタイトル", 1000, null, 1, "テスト著者", 1, "テスト出版社", null, null, null, null));
    Pagination<Book> expected = new Pagination<>(books, 1, 0, 10);
    Mockito.when(repository.findMany(query)).thenReturn(expected);

    // when
    Pagination<Book> actual = usecase.execute(query);

    // then
    assertThat(actual, is(expected));
  }
}
