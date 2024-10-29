package vlad.gurabatov.REST.repository.datajpa;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vlad.gurabatov.REST.entity.Book;
import vlad.gurabatov.REST.entity.User;
import vlad.gurabatov.REST.repository.BookRepository;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Repository
@Primary
public class DataJpaBookRepository implements BookRepository {
    private final CrudBookRepository crudBookRepository;
    private final CrudUserRepository crudUserRepository;
    @Override
    public Optional<Book> get(long id) {
        return crudBookRepository.findById(id);
    }

    @Override
    public List<Book> getAll() {
        return crudBookRepository.findAll();
    }

    @Override
    public Book save(Book book, long userId) {
        User user = crudUserRepository.getReferenceById(userId);
        book.setAuthor(user);
        return crudBookRepository.save(book);
    }

    @Override
    public Book update(Book book, long userId) {
        return save(book, userId);
    }

    @Override
    public void delete(long id) {
        crudBookRepository.deleteById(id);
    }

    @Override
    public void increaseViews(Book book) {
        book.setViews(book.getViews() + 1);
        crudBookRepository.save(book);
    }


}
