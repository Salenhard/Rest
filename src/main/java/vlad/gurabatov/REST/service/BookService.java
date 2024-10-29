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
    Book add(Book book, long userId);

    @Cacheable(value = "books", key = "#id")
    Optional<Book> get(Long id);

    void increaseViews(Book book);

    List<Book> getAll();

    @CacheEvict(value = "books", key = "#comment.id")
    void delete(Long id);

    Book update(Book book, long userId);

    List<Book> getAllByName(String name);
}
