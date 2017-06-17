package ch.mapirium.server.fielddefinitionservice.rest.model;

import ch.mapirium.server.fielddefinitionservice.model.FieldDefinitionEntity;
import ch.mapirium.server.fielddefinitionservice.rest.controller.FieldDefinitionRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Mappt eine Liste von Feld-Definitionen in eine REST-Resource
 */
@Component
public class FieldDefinitionListMapper {

    @Autowired
    private FieldDefinitionMapper fieldDefinitionMapper;

    public FieldDefinitionListResource fromEntity(Iterable<FieldDefinitionEntity> entities, String mapId, String pointDefId) {
        FieldDefinitionListResource result = new FieldDefinitionListResource();
        result.add(linkTo(methodOn(FieldDefinitionRestController.class).getAll(mapId, pointDefId)).withSelfRel());

        // Die einzelnen Entitäten Mappen
        List<FieldDefinitionResource> resourceList = StreamSupport.stream(entities.spliterator(), false).map(fieldDefinitionMapper::fromEntity).collect(Collectors.toList());
        result.embed("fieldDefinitions", resourceList);
        return result;
    }
}
