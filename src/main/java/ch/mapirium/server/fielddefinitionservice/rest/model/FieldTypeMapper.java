package ch.mapirium.server.fielddefinitionservice.rest.model;

import ch.mapirium.server.fielddefinitionservice.model.FieldTypeEntity;
import ch.mapirium.server.fielddefinitionservice.rest.controller.FieldTypeController;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Mapper zwischen der JPA und REST-Entität eines Feld-Types
 */
@Component
public class FieldTypeMapper {
    public FieldTypeResource fromEntity(FieldTypeEntity entity) {
        FieldTypeResource result = new FieldTypeResource();
        result.setName(entity.getName());
        result.setKeyword(entity.getKeyword());
        result.setDescription(entity.getDescription());
        result.setCreatedAt(entity.getCreatedAt());

        result.add(linkTo(methodOn(FieldTypeController.class).getByKeyword(result.getKeyword())).withSelfRel());

        return result;
    }
}
