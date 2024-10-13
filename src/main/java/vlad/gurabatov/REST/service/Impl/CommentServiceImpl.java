package vlad.gurabatov.REST.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import vlad.gurabatov.REST.entity.Comment;
import vlad.gurabatov.REST.repository.CommentRepository;
import vlad.gurabatov.REST.service.CommentService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;

    @Override
    @CachePut(value = "comments", key = "#id")
    public Comment addComment(Comment comment) {
        comment.setCreateDate(LocalDate.now());
        return repository.save(comment);
    }

    @Override
    @Cacheable(value = "comments", key = "#id")
    public Optional<Comment> getComment(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Comment> getAllComments() {
        return repository.findAll();
    }

    @Override
    @CacheEvict(value = "comments", key = "#id")
    public void deleteComment(long id) {
        repository.deleteById(id);
    }

    @Override
    public Comment updateComment(Comment comment) {
        return repository.save(comment);
    }
}
