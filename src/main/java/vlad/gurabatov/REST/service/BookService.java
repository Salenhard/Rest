package vlad.gurabatov.REST.service;

import org.springframework.stereotype.Service;
import vlad.gurabatov.REST.entity.Book;
import java.util.List;
import java.util.Optional;

@Service
public interface BookService {
    Book addBook(Book book);

    Optional<Book> getBook(Long id);

    List<Book> getAllBooks();

    void deleteBook(Long id);

    Book updateBook(Book book);

    List<Book> getBooksByName(String name);
}
