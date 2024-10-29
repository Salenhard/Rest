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
    Optional<User> get(Long id);

    List<User> getAll();

    @CachePut(value = "users", key = "#user.id")
    User add(User user);

    @CacheEvict(value = "users", key = "#user.id")
    void delete(Long id);

    User update(User user);

    List<User> getAllByName(String name);
}
