package vlad.gurabatov.REST.repository.datajpa;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vlad.gurabatov.REST.entity.Comment;
@Repository
@Primary
public interface CrudCommentRepository extends JpaRepository<Comment, Long> {

    void deleteByIdAndAuthor_Id(long id, long userId);
}
