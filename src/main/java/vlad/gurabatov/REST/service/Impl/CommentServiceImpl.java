package vlad.gurabatov.REST.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public Comment add(Comment comment, long userId, long bookId) {
        comment.setCreateDate(LocalDate.now());
        return repository.save(comment, userId, bookId);
    }

    @Override
    public Optional<Comment> get(long id) {
        return repository.get(id);
    }

    @Override
    public List<Comment> getAll() {
        return repository.getAll();
    }

    @Transactional
    public void delete(long id, long userId) {
        repository.delete(id, userId);
    }

    @Override
    @Transactional
    public Comment update(Comment comment, long userId) {
        return repository.update(comment, userId);
    }
}
