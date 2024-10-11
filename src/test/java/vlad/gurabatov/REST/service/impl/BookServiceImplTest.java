package vlad.gurabatov.REST.service.impl;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import vlad.gurabatov.REST.entity.Book;
import vlad.gurabatov.REST.repository.BookRepository;
import vlad.gurabatov.REST.service.Impl.BookServiceImpl;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    public void getAllBooks() {
        List<Book> books = getBooks();
        Mockito.when(bookRepository.findAll()).thenReturn(books);
        List<Book> result = bookService.getAllBooks();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void getBooksByName() {
        List<Book> books = getBooks();
        Mockito.when(bookRepository.findAll()).thenReturn(getBooks());
        List<Book> result = bookService.getBooksByName("First Book");
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
    }

    private List<Book> getBooks() {
        Book firstBook = new Book();
        Book secondBook = new Book();
        firstBook.setId(1L);
        firstBook.setName("First Book");
        firstBook.setAuthor(null);
        firstBook.setDescription("First Book Description");
        secondBook.setId(2L);
        secondBook.setName("Second Book");
        secondBook.setAuthor(null);
        secondBook.setDescription("Second Book Description");
        return List.of(firstBook, secondBook);

    }
}
