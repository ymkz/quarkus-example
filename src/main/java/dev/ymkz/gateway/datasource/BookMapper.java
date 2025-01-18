package dev.ymkz.gateway.datasource;

import dev.ymkz.domain.model.BookSearchQuery;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BookMapper {

  @Select(
      """
        SELECT
            COUNT(1)
        FROM
            books
        ORDER BY
            price DESC
    """)
  int count(BookSearchQuery query);

  @Select(
      """
        SELECT
            id,
            isbn,
            title,
            price
        FROM
            books
        ORDER BY
            price DESC
    """)
  List<BookEntity> list(BookSearchQuery query);
}
