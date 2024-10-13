package vlad.gurabatov.REST.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vlad.gurabatov.REST.entity.User;
import vlad.gurabatov.REST.entity.model.UserModelAssembler;
import vlad.gurabatov.REST.exception.UserNotFoundException;
import vlad.gurabatov.REST.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService service;
    private final UserModelAssembler assembler;

    @GetMapping("")
    public CollectionModel<EntityModel<User>> getAll() {
        List<EntityModel<User>> model = service.getAllUsers().stream().map(assembler::toModel).toList();
        return CollectionModel.of(model);
    }

    @GetMapping("/{id}")
    public EntityModel<User> getUserById(@PathVariable Long id) {
        User user = service.getUser(id).orElseThrow(() -> new UserNotFoundException(id));
        return assembler.toModel(user);
    }

    @GetMapping("/search")
    public CollectionModel<EntityModel<User>> getUserByName(@RequestParam String name) {
        List<EntityModel<User>> model = service.getUsersByName(name).stream().map(assembler::toModel).toList();
        return CollectionModel.of(model);
    }

    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody @Valid User user) {
        EntityModel<User> model = assembler.toModel(service.addUser(user));
        return ResponseEntity.created(model.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody @Valid User newUser) {
        User updatedUser = service.getUser(id).map(user -> {
            user.setName(newUser.getName());
            user.setSurname(newUser.getSurname());
            user.setLastName(user.getLastName());
            user.setEmail(newUser.getEmail());
            user.setBirthday(newUser.getBirthday());
            user.setBooks(newUser.getBooks());
            return service.updateUser(user);
        }).orElseThrow(() -> new UserNotFoundException(id));

        EntityModel<User> model = assembler.toModel(updatedUser);

        return ResponseEntity.created(model.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(model);
    }

}
