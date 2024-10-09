package vlad.gurabatov.REST.entity.model;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import vlad.gurabatov.REST.controller.BookController;
import vlad.gurabatov.REST.entity.Book;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BookModelAssembler implements RepresentationModelAssembler<Book, EntityModel<Book>> {
    @Override
    public EntityModel<Book> toModel(Book book) {
        return EntityModel.of(book,
                linkTo(methodOn(BookController.class).getBook(book.getId())).withSelfRel(),
                linkTo(methodOn(BookController.class).getBooks()).withRel("books"));
    }

    @Override
    public CollectionModel<EntityModel<Book>> toCollectionModel(Iterable<? extends Book> books) {
        return RepresentationModelAssembler.super.toCollectionModel(books);
    }
}
