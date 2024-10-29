package vlad.gurabatov.REST.repository;

import vlad.gurabatov.REST.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Optional<Comment> get(long id);

    List<Comment> getAll();

    Comment save(Comment comment, long userId, long bookId);

    Comment update(Comment comment, long userId);

    void delete(long id, long userId);
}
