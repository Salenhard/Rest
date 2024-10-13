package vlad.gurabatov.REST.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import vlad.gurabatov.REST.entity.User;
import vlad.gurabatov.REST.repository.UserRepository;
import vlad.gurabatov.REST.service.BookService;
import vlad.gurabatov.REST.service.UserService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final BookService bookService;

    @Override
    @Cacheable(value = "users", key = "#id")
    public Optional<User> getUser(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    @CachePut(value = "users", key = "#user.id")
    public User addUser(User user) {
        return repository.save(user);
    }

    @Override
    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    @Override
    public User updateUser(User user) {
        return repository.save(user);
    }

    @Override
    @Cacheable(value = "users", key = "#name")
    public List<User> getUsersByName(String name) {
        return repository.findAll().stream()
                .filter(user -> user.getName().equals(name)).toList();
    }
}
