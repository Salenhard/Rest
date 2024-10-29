package vlad.gurabatov.REST.repository.datajpa;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vlad.gurabatov.REST.entity.User;

import java.util.List;

@Repository
@Primary
public interface CrudUserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
}
