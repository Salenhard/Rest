package vlad.gurabatov.REST.repository;

import vlad.gurabatov.REST.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Optional<Book> get(long id);

    List<Book> getAll();

    Book save(Book book, long userId);

    Book update(Book book, long userId);

    void delete(long id);

    void increaseViews(Book book);
}
