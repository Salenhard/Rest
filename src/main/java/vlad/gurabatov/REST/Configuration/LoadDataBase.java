package vlad.gurabatov.REST.Configuration;

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
            //userRepository.save(new User("Ivan", "Ivanov", "Ivanovich", LocalDate.now(), "test@gmail.com"));
            //userRepository.save(new User("Alecsander", "Alecsandrov", "Alecsandrovich", LocalDate.now(), "test@gmail.com"));
            //bookRepository.save(new Book(userRepository.findById(1L).get(), List.of(Genre.Comedy, Genre.Drama), "description", "name"));
            //commentRepository.save(new Comment(1L, "texts", LocalDate.now(), bookRepository.findById(1L).get(), userRepository.findById(1L).get()));
            userRepository.findAll().forEach(user -> log.info("Preloaded:" + user));
            bookRepository.findAll().forEach(book -> log.info("Preloaded:" + book));
            commentRepository.findAll().forEach(comment -> log.info("Preloaded:" + comment));
        };
    }
}
