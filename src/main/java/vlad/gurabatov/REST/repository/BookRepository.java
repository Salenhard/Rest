package vlad.gurabatov.REST.repository;

import org.springframework.stereotype.Repository;
import vlad.gurabatov.REST.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
