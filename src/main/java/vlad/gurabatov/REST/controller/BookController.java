package vlad.gurabatov.REST.controller;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vlad.gurabatov.REST.entity.Book;
import vlad.gurabatov.REST.entity.model.BookModelAssembler;
import vlad.gurabatov.REST.exception.BookNotFoundException;
import vlad.gurabatov.REST.service.BookService;
import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService service;
    private final BookModelAssembler assembler;

    @GetMapping("/{id}")
    public EntityModel<Book> getBook(@PathVariable Long id) {
        Book book = service.getBook(id).orElseThrow(() -> new BookNotFoundException(id));
        return assembler.toModel(book);
    }

    @GetMapping("")
    public CollectionModel<EntityModel<Book>> getBooks() {
        List<EntityModel<Book>> books = service.getAllBooks().stream().map(assembler::toModel).toList();
        return CollectionModel.of(books);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        service.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody @Valid Book newBook, BindingResult bindingResult) {
        // валидация книги
        if (bindingResult.hasErrors()) return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        // обновление книги
        Book updatedBook = service.getBook(id).map(book -> {
            book.setName(newBook.getName());
            book.setAuthor(newBook.getAuthor());
            book.setGenres(newBook.getGenres());
            return service.updateBook(book);
        }).orElseThrow(() -> new BookNotFoundException(id));
        // преобразование книги в модель
        EntityModel<Book> model = assembler.toModel(updatedBook);
        // возвращение модели
        return ResponseEntity.created(model.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(model);
    }

    @PostMapping("")
    public ResponseEntity<?> addBook(@RequestBody @Valid Book newBook, BindingResult bindingResult) {
        // валидация книги
        if (bindingResult.hasErrors()) return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        // преобразование книги в модель
        EntityModel<Book> model = assembler.toModel(service.addBook(newBook));
        // возвращение модели
        return ResponseEntity.created(model.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(model);
    }
}
