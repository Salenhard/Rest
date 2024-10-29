package vlad.gurabatov.REST.service.Impl;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public Book add(Book book, long userId) {
        return repository.save(book, userId);
    }

    @Override
    public Optional<Book> get(Long id) {
        return repository.get(id);
    }

    @Override
    @Transactional
    public void increaseViews(Book book) {
        repository.increaseViews(book);
    }

    @Override
    public List<Book> getAll() {
        return repository.getAll();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    @Transactional
    public Book update(Book book, long userId) {
        return repository.save(book, userId);
    }

    @Override
    public List<Book> getAllByName(String name) {
        return repository.getAll().stream()
                .filter(book -> book.getName().contains(name)).toList();
    }


}
