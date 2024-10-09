package vlad.gurabatov.REST.entity.model;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import vlad.gurabatov.REST.entity.User;


@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {
    @Override
    public EntityModel<User> toModel(User entity) {
        return null;
    }

    @Override
    public CollectionModel<EntityModel<User>> toCollectionModel(Iterable<? extends User> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
