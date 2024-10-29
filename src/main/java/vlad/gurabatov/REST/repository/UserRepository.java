package vlad.gurabatov.REST.repository;

import vlad.gurabatov.REST.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> get(long id);

    List<User> getAll();

    User save(User user);

    void delete(long id);

    User update(User user);
}
