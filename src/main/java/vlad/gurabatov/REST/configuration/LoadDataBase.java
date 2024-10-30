package vlad.gurabatov.REST.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vlad.gurabatov.REST.entity.Book;
import vlad.gurabatov.REST.entity.Comment;
import vlad.gurabatov.REST.entity.Genre;
import vlad.gurabatov.REST.entity.User;
import vlad.gurabatov.REST.repository.BookRepository;
import vlad.gurabatov.REST.repository.CommentRepository;
import vlad.gurabatov.REST.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class LoadDataBase {
    private static final Logger log = LoggerFactory.getLogger(LoadDataBase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, BookRepository bookRepository, CommentRepository commentRepository) {
        return args -> {
            userRepository.save(new User("Ivan", "Ivanov", "Ivanovich", LocalDate.now(), "test@gmail.com"));
            userRepository.save(new User("Alecsander", "Alecsandrov", "Alecsandrovich", LocalDate.now(), "test@gmail.com"));
            Book book1 = new Book();
            book1.setName("name");
            book1.setDescription("description");
            book1.setGenres(List.of(Genre.Drama, Genre.Horror));
            Book book2 = new Book();
            book2.setName("name");
            book2.setDescription("description");
            book2.setGenres(List.of(Genre.Horror, Genre.Drama));
            bookRepository.save(book1, 1);
            bookRepository.save(book2, 2);
            Comment comment = new Comment();
            comment.setText("test text for comment");
            commentRepository.save(comment, 1, 1);
            userRepository.getAll().forEach(user -> log.info("Preloaded:" + user));
            bookRepository.getAll().forEach(book -> log.info("Preloaded:" + book));
            commentRepository.getAll().forEach(com -> log.info("Preloaded:" + com));
        };
    }
}
