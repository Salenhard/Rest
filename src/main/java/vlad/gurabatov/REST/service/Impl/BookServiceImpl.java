package vlad.gurabatov.REST.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
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

    @Override
    @CachePut(value = "books", key = "#comment.id")
    public Book addBook(Book book) {
        return repository.save(book);
    }

    @Override
    @Cacheable(value = "books", key = "#id")
    public Optional<Book> getBook(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    @Override
    @CacheEvict(value = "books", key = "#comment.id")
    public void deleteBook(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Book updateBook(Book book) {
        return repository.save(book);
    }

    @Override
    public List<Book> getBooksByName(String name) {
        return repository.findAll().stream()
                .filter(book -> book.getName().contains(name)).toList();
    }


}
