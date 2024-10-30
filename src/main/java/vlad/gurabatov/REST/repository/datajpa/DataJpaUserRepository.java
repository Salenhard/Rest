package vlad.gurabatov.REST.repository.datajpa;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import vlad.gurabatov.REST.entity.User;
import vlad.gurabatov.REST.repository.UserRepository;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Repository
@Profile("jpa")
public class DataJpaUserRepository implements UserRepository {
    private final CrudUserRepository crudUserRepository;
    @Override
    public Optional<User> get(long id) {
        return crudUserRepository.findById(id);
    }

    @Override
    public List<User> getAll() {
        return crudUserRepository.findAll();
    }

    @Override
    public User save(User user) {
        return crudUserRepository.save(user);
    }

    @Override
    public void delete(long id) {
        crudUserRepository.deleteById(id);
    }

    @Override
    public User update(User user) {
        return save(user);
    }
}
