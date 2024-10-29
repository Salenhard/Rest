package vlad.gurabatov.REST.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import vlad.gurabatov.REST.entity.Comment;

import java.util.List;
import java.util.Optional;

@Service
public interface CommentService {
    @CachePut(value = "comments", key = "#comment.id")
    public Comment addComment(Comment comment, long userId, long bookId);

    @Cacheable(value = "comments", key = "#id")
    public Optional<Comment> getComment(long id);

    public List<Comment> getAllComments();

    @CacheEvict(value = "comments", key = "#comment.id")
    public void deleteComment(long id, long userId);

    public Comment updateComment(Comment comment, long userId);
}
