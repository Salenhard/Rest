package vlad.gurabatov.REST.service.Impl;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vlad.gurabatov.REST.entity.Book;
import vlad.gurabatov.REST.repository.BookRepository;
import vlad.gurabatov.REST.service.BookService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository repository;
    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    @Override
    public Book addBook(Book book, long userId) {
        return repository.save(book, userId);
    }

    @Override
    public Optional<Book> getBook(Long id) {
        return repository.get(id);
    }

    @Override
    public void increaseViews(Book book) {
        repository.increaseViews(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return repository.getAll();
    }

    @Override
    public void deleteBook(Long id) {
        repository.delete(id);
    }

    @Override
    public Book updateBook(Book book, long userId) {
        return repository.save(book, userId);
    }

    @Override
    public List<Book> getBooksByName(String name) {
        return repository.getAll().stream()
                .filter(book -> book.getName().contains(name)).toList();
    }


}
