package vlad.gurabatov.REST.service.Impl;

import lombok.AllArgsConstructor;
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
    public Book addBook(Book book) {
        return repository.save(book);
    }

    @Override
    public Optional<Book> getBook(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    @Override
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
