package vlad.gurabatov.REST.entity.model;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import vlad.gurabatov.REST.controller.CommentController;
import vlad.gurabatov.REST.entity.Comment;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CommentModelAssembler implements RepresentationModelAssembler<Comment, EntityModel<Comment>> {
    @Override
    public EntityModel<Comment> toModel(Comment entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(CommentController.class).getCommentById(entity.getId())).withSelfRel(),
                linkTo(methodOn(CommentController.class).getComments()).withRel("comments"));
    }

    @Override
    public CollectionModel<EntityModel<Comment>> toCollectionModel(Iterable<? extends Comment> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
