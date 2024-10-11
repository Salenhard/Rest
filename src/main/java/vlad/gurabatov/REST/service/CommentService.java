package vlad.gurabatov.REST.service;

import org.springframework.stereotype.Service;
import vlad.gurabatov.REST.entity.Comment;

import java.util.List;
import java.util.Optional;

@Service
public interface CommentService {
    public Comment addComment(Comment comment);

    public Optional<Comment> getComment(long id);

    public List<Comment> getAllComments();

    public void deleteComment(long id);

    public Comment updateComment(Comment comment);
}
