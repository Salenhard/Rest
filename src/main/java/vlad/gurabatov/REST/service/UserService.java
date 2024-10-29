package vlad.gurabatov.REST.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import vlad.gurabatov.REST.entity.User;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    @Cacheable(value = "users", key = "#id")
    Optional<User> getUser(Long id);

    List<User> getAllUsers();

    @CachePut(value = "users", key = "#user.id")
    User addUser(User user);

    @CacheEvict(value = "users", key = "#user.id")
    void deleteUser(Long id);

    User updateUser(User user);

    List<User> getUsersByName(String name);
}
