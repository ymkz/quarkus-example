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
            books as b
        INNER JOIN
            (
                SELECT id, author_name
                FROM authors
                WHERE deleted_at IS NULL
            ) as a ON b.author_id = a.id
        INNER JOIN
            (
                SELECT id, publisher_name
                FROM publishers
                WHERE deleted_at IS NULL
            ) as p ON b.publisher_id = p.id
        WHERE
            b.deleted_at IS NULL
            <if test="isbn != null">
                AND b.isbn = #{isbn.value}
            </if>
            <if test="title != null">
                AND b.title LIKE '%' || #{title} || '%'
            </if>
            <if test="priceRange.min != null">
                AND b.price &gt;= #{priceRange.min}
            </if>
            <if test="priceRange.max != null">
                AND b.price &lt;= #{priceRange.max}
            </if>
            <if test="status != null">
                AND b.status = #{status}
            </if>
            <if test="publishedTimeRange.start != null">
                AND b.published_at &gt;= #{publishedTimeRange.start}
            </if>
            <if test="publishedTimeRange.end != null">
                AND b.published_at &lt;= #{publishedTimeRange.end}
            </if>
        </script>
    """)
  int count(BookSearchQuery query);

  @Select(
      """
        <script>
        SELECT
            b.id,
            b.isbn,
            b.title,
            b.price,
            b.status,
            b.author_id,
            a.author_name,
            b.publisher_id,
            p.publisher_name,
            b.published_at,
            b.created_at,
            b.updated_at,
            b.deleted_at
        FROM
            books as b
        INNER JOIN
            (
                SELECT id, author_name
                FROM authors
                WHERE deleted_at IS NULL
            ) as a ON b.author_id = a.id
        INNER JOIN
            (
                SELECT id, publisher_name
                FROM publishers
                WHERE deleted_at IS NULL
            ) as p ON b.publisher_id = p.id
        WHERE
            b.deleted_at IS NULL
            <if test="isbn != null">
                AND b.isbn = #{isbn.value}
            </if>
            <if test="title != null">
                AND b.title LIKE '%' || #{title} || '%'
            </if>
            <if test="priceRange.min != null">
                AND b.price &gt;= #{priceRange.min}
            </if>
            <if test="priceRange.max != null">
                AND b.price &lt;= #{priceRange.max}
            </if>
            <if test="status != null">
                AND b.status = #{status}
            </if>
            <if test="publishedTimeRange.start != null">
                AND b.published_at &gt;= #{publishedTimeRange.start}
            </if>
            <if test="publishedTimeRange.end != null">
                AND b.published_at &lt;= #{publishedTimeRange.end}
            </if>
        ORDER BY
            #{order.orderBy}
        LIMIT
            #{limit}
        OFFSET
            #{offset}
        </script>
    """)
  List<BookEntity> list(BookSearchQuery query);
}
