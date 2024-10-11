package vlad.gurabatov.REST.service;

import org.springframework.stereotype.Service;
import vlad.gurabatov.REST.entity.User;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    Optional<User> getUser(Long id);

    List<User> getAllUsers();

    User addUser(User user);

    void deleteUser(Long id);

    User updateUser(User user);

    List<User> getUsersByName(String name);
}
