package dev.ymkz.gateway.datasource;

import dev.ymkz.domain.model.BookSearchQuery;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BookMapper {

  @Select(
      """
        <script>
        SELECT
            COUNT(1)
        FROM
            books
        WHERE
            1 = 1
            <if test="isbn != null">
                AND isbn = #{isbn.value}
            </if>
            <if test="title != null">
                AND title LIKE '%' || #{title} || '%'
            </if>
        ORDER BY
            #{order.orderBy}
        </script>
    """)
  int count(BookSearchQuery query);

  @Select(
      """
        <script>
        SELECT
            id,
            isbn,
            title,
            price
        FROM
            books
        WHERE
            1 = 1
            <if test="isbn != null">
                AND isbn = #{isbn.value}
            </if>
            <if test="title != null">
                AND title LIKE '%' || #{title} || '%'
            </if>
        ORDER BY
            #{order.orderBy}
        </script>
    """)
  List<BookEntity> list(BookSearchQuery query);
}
