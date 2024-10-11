package vlad.gurabatov.REST.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vlad.gurabatov.REST.entity.Comment;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    
}
