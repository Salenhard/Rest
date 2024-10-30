package vlad.gurabatov.REST.repository.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import vlad.gurabatov.REST.entity.Comment;
import vlad.gurabatov.REST.repository.CommentRepository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
@Repository
@Profile("jdbc")
public class JdbcCommentRepository implements CommentRepository {
    private final static BeanPropertyRowMapper<Comment> ROW_MAPPER = new BeanPropertyRowMapper<>(Comment.class);

    private final DataSource dataSource;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final JdbcTemplate jdbcTemplate;

    private final SimpleJdbcInsert commentInsert;

    public JdbcCommentRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.dataSource = dataSource;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;
        this.commentInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("comments")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Optional<Comment> get(long id) {
        List<Comment> comments = jdbcTemplate.query("SELECT * FROM comments WHERE id = ?", ROW_MAPPER, id);
        return Optional.of(comments.getFirst());
    }

    @Override
    public List<Comment> getAll() {
        return jdbcTemplate.query("SELECT * FROM comments", ROW_MAPPER);
    }

    @Override
    public Comment save(Comment comment, long userId, long bookId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", comment.getId())
                .addValue("author", userId)
                .addValue("book", bookId)
                .addValue("text", comment.getText())
                .addValue("createDate", comment.getCreateDate());
        if (comment.getId() == 0) {
            Number id = commentInsert.executeAndReturnKey(map);
            comment.setId(id.longValue());
            return comment;
        } else if (namedParameterJdbcTemplate.update("UPDATE comments SET author = ?," +
                " book = ?," +
                " text = ?," +
                " createDate = ?" +
                " WHERE id = ?", map) == 0) {
            return null;
        }
        return comment;
    }

    @Override
    public Comment update(Comment comment, long userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", comment.getId())
                .addValue("text", comment.getText());
        if (namedParameterJdbcTemplate.update("UPDATE comments SET" +
                " text = ?," +
                " WHERE id = ?", map) == 0) {
            return null;
        }
        return comment;
    }

    @Override
    public void delete(long id, long userId) {
        jdbcTemplate.update("DELETE FROM comments WHERE id = ? and author = ?", id, userId);
    }
}
