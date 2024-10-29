package vlad.gurabatov.REST.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vlad.gurabatov.REST.entity.User;
import vlad.gurabatov.REST.repository.UserRepository;
import vlad.gurabatov.REST.service.UserService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public Optional<User> get(Long id) {
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    @Transactional
    public User add(User user) {
        return repository.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    @Transactional
    public User update(User user) {
        return repository.save(user);
    }

    @Override
    public List<User> getAllByName(String name) {
        return repository.getAll().stream()
                .filter(user -> user.getName().equals(name)).toList();
    }
}
