package vlad.gurabatov.REST.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vlad.gurabatov.REST.entity.Comment;
import vlad.gurabatov.REST.entity.model.CommentModelAssembler;
import vlad.gurabatov.REST.exception.CommentNotFoundException;
import vlad.gurabatov.REST.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@AllArgsConstructor
public class CommentController {
    private final CommentService service;
    private final CommentModelAssembler assembler;

    @GetMapping("")
    public CollectionModel<EntityModel<Comment>> getComments() {
        List<EntityModel<Comment>> comments = service.getAllComments().stream()
                .map(assembler::toModel).toList();
        return CollectionModel.of(comments);
    }

    @GetMapping("/{id}")
    public EntityModel<Comment> getCommentById(@PathVariable Long id) {
        Comment comment = service.getComment(id).orElseThrow(() -> new CommentNotFoundException(id));
        return assembler.toModel(comment);
    }

    @PostMapping("")
    public ResponseEntity<?> addComment(@Valid @RequestBody Comment comment) {
        //if (bindingResult.hasErrors()) return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        EntityModel<Comment> commentModel = assembler.toModel(service.addComment(comment));
        return ResponseEntity.created(commentModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(commentModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@PathVariable Long id, @Valid @RequestBody Comment newComment) {
        Comment updatedComment = service.getComment(id).map(comment ->
        {
            comment.setText(newComment.getText());
            return comment;
        }).orElseThrow(() -> new CommentNotFoundException(id));
        EntityModel<Comment> commentModel = assembler.toModel(updatedComment);
        return ResponseEntity.created(commentModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(commentModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        service.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

}
