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
    public Comment add(Comment comment, long userId, long bookId);

    @Cacheable(value = "comments", key = "#id")
    public Optional<Comment> get(long id);

    public List<Comment> getAll();

    @CacheEvict(value = "comments", key = "#comment.id")
    public void delete(long id, long userId);

    public Comment update(Comment comment, long userId);
}
