package vlad.gurabatov.REST.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
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
    public Optional<User> getUser(Long id) {
        return repository.get(id);
    }

    @Override
    public List<User> getAllUsers() {
        return repository.getAll();
    }

    @Override
    public User addUser(User user) {
        return repository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        repository.delete(id);
    }

    @Override
    public User updateUser(User user) {
        return repository.save(user);
    }

    @Override
    public List<User> getUsersByName(String name) {
        return repository.getAll().stream()
                .filter(user -> user.getName().equals(name)).toList();
    }
}
