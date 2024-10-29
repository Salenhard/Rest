package vlad.gurabatov.REST.repository.datajpa;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vlad.gurabatov.REST.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
@Primary
public interface CrudBookRepository extends JpaRepository<Book, Long> {
}
