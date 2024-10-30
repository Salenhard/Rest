package vlad.gurabatov.REST.repository.jdbc;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import vlad.gurabatov.REST.entity.Book;
import vlad.gurabatov.REST.repository.BookRepository;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Profile("jdbc")
public class JdbcBookRepository implements BookRepository {
    private final static BeanPropertyRowMapper<Book> ROW_MAPPER = new BeanPropertyRowMapper<>(Book.class);

    private final DataSource dataSource;

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert bookInsert;

    public JdbcBookRepository(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.bookInsert = new SimpleJdbcInsert(dataSource).withTableName("books")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Optional<Book> get(long id) {
        List<Book> books = jdbcTemplate.query("SELECT * FROM books WHERE id = ?", ROW_MAPPER, id);
        return Optional.of(books.getFirst());
    }

    @Override
    public List<Book> getAll() {
        return jdbcTemplate.query("SELECT * FROM books", ROW_MAPPER);
    }

    @Override
    public Book save(Book book, long userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", book.getId())
                .addValue("name", book.getName())
                .addValue("author", userId)
                .addValue("description", book.getDescription())
                .addValue("genres", book.getGenres().stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(",")));
        if (book.getId() == null) {
            Number id = bookInsert.executeAndReturnKey(map);
            book.setId(id.longValue());
            return book;
        }
        else if(namedParameterJdbcTemplate.update("UPDATE books SET name = ?," +
                " author = ?," +
                " description = ?," +
                " genres = ? " +
                "WHERE id = ?", map) == 0) {
            return null;
        }
        return book;
    }

    @Override
    public Book update(Book book, long userId) {
        return save(book, userId);
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM books WHERE id = ?", id);
    }

    @Override
    public void increaseViews(Book book) {
        jdbcTemplate.update("UPDATE books SET views = views + 1 WHERE id = ?", book.getId());
    }
}
