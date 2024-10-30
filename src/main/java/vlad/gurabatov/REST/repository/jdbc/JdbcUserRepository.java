package vlad.gurabatov.REST.repository.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import vlad.gurabatov.REST.entity.User;
import vlad.gurabatov.REST.repository.UserRepository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("jdbc")
public class JdbcUserRepository implements UserRepository {
    private static final BeanPropertyRowMapper<User> ROW_MAPPER = new BeanPropertyRowMapper<>(User.class);

    private final DataSource dataSource;

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertUser;

    public JdbcUserRepository(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.insertUser = new SimpleJdbcInsert(dataSource)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Optional<User> get(long id) {
        List<User> user = jdbcTemplate.query("SELECT * FROM users WHERE id = ?", ROW_MAPPER, id);
        return Optional.of(user.getFirst());
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query("SELECT * FROM users", ROW_MAPPER);
    }

    @Override
    public User save(User user) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("name", user.getName())
                .addValue("lastName", user.getLastName())
                .addValue("surname", user.getSurname())
                .addValue("birthday", user.getBirthday())
                .addValue("email", user.getEmail());
        if (user.getId() == null) {
            Number id = insertUser.executeAndReturnKey(map);
            user.setId(id.longValue());
            return user;
        } else if (namedParameterJdbcTemplate.update("UPDATE users SET name = :name," +
                " lastName = :lastName," +
                " surname = :surname," +
                " birthday = :birthday," +
                " email = :email " +
                "WHERE id = :id", map) == 0) {
            return null;
        }
        return user;
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
    }

    @Override
    public User update(User user) {
        return save(user);
    }
}
