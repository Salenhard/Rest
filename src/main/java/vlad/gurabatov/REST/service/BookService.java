package vlad.gurabatov.REST.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import vlad.gurabatov.REST.entity.Book;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService {

    @CachePut(value = "books", key = "#comment.id")
    Book addBook(Book book,long userId);

    @Cacheable(value = "books", key = "#id")
    Optional<Book> getBook(Long id);

    void increaseViews(Book book);

    List<Book> getAllBooks();

    @CacheEvict(value = "books", key = "#comment.id")
    void deleteBook(Long id);

    Book updateBook(Book book, long userId);

    List<Book> getBooksByName(String name);
}
