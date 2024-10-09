package vlad.gurabatov.REST.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vlad.gurabatov.REST.entity.Book;
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
    public Optional<User> getUser(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<User> allUsers() {
        return repository.findAll();
    }

    @Override
    public User addUser(User user) {
        return repository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUser(id).orElseThrow();
        List<Book> books = user.getBooks();
        if (!books.isEmpty())
            books.forEach(book -> bookService.deleteBook(book.getId()));
        repository.deleteById(id);
    }

    @Override
    public User updateUser(User user) {
        return repository.save(user);
    }
}
