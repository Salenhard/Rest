package vlad.gurabatov.REST.service.Impl;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vlad.gurabatov.REST.service.BookService;

@Component
@AllArgsConstructor
public class ScheduledTasksService {
    private final BookService bookService;
    private final static Logger log = LoggerFactory.getLogger(ScheduledTasksService.class);

//    @Scheduled(fixedRate = 1000)
//    public void increaseViews() {
//        bookService.getAll().forEach(bookService::increaseViews);
//        log.info("Increasing views...");
//    }
}
