package vlad.gurabatov.REST.repository.datajpa;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vlad.gurabatov.REST.entity.Book;
import vlad.gurabatov.REST.entity.Comment;
import vlad.gurabatov.REST.entity.User;
import vlad.gurabatov.REST.repository.CommentRepository;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Repository
@Primary
public class DataJpaCommentRepository implements CommentRepository {
    private final CrudCommentRepository crudCommentRepository;
    private final CrudUserRepository crudUserRepository;
    private final CrudBookRepository crudBookRepository;
    @Override
    public Optional<Comment> get(long id) {
        return crudCommentRepository.findById(id);
    }

    @Override
    public List<Comment> getAll() {
        return crudCommentRepository.findAll();
    }

    @Override
    public Comment save(Comment comment, long userId, long bookId) {
        User user = crudUserRepository.getReferenceById(userId);
        Book book = crudBookRepository.getReferenceById(bookId);
        comment.setAuthor(user);
        comment.setBook(book);
        return crudCommentRepository.save(comment);
    }

    @Override
    public Comment update(Comment comment, long userId) {
        User user = crudUserRepository.getReferenceById(userId);
        comment.setAuthor(user);
        return crudCommentRepository.save(comment);
    }

    @Override
    public void delete(long id, long userId) {
        crudCommentRepository.deleteByIdAndAuthor_Id(id, userId);
    }
}
