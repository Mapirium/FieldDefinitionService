package ch.mapirium.server.fielddefinitionservice.rest.model;

import ch.mapirium.server.common.springmvc.exceptions.BadRequestException;
import ch.mapirium.server.fielddefinitionservice.model.FieldDefinitionEntity;
import ch.mapirium.server.fielddefinitionservice.model.FieldTypeEntity;
import ch.mapirium.server.fielddefinitionservice.repo.FieldDefinitionRepository;
import ch.mapirium.server.fielddefinitionservice.repo.FieldTypeRepository;
import ch.mapirium.server.fielddefinitionservice.rest.controller.FieldDefinitionRestController;
import ch.mapirium.server.fielddefinitionservice.rest.controller.FieldTypeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Mapper zwischen der JPA und REST-Entität eines Feld-Types
 */
@Component
public class FieldDefinitionMapper {

    @Autowired
    private FieldTypeMapper fieldTypeMapper;

    @Autowired
    private FieldTypeRepository fieldTypeRepository;

    public FieldDefinitionResource fromEntity(FieldDefinitionEntity entity) {
        FieldDefinitionResource result = new FieldDefinitionResource();
        result.setPublicId(entity.getPublicId());
        result.setLabel(entity.getLabel());
        result.setDescription(entity.getDescription());
        result.setMapId(entity.getMapId());
        result.setCreatedAt(entity.getCreatedAt());
        result.setMandatory(entity.isMandatory());
        result.setFieldType(entity.getFieldType().getKeyword());

        // Link auf uns selbst
        result.add(linkTo(methodOn(FieldDefinitionRestController.class).findByPublicId(result.getMapId(), result.getPublicId())).withSelfRel());

        // Link auf den Feldtype
        result.add(linkTo(methodOn(FieldTypeController.class).getByKeyword(result.getFieldType())).withRel("fieldType"));

        return result;
    }

    public FieldDefinitionEntity toEntity(FieldDefinitionResource resource) {
        FieldDefinitionEntity result = new FieldDefinitionEntity();
        result.setPublicId(resource.getPublicId());
        result.setLabel(resource.getLabel());
        result.setDescription(resource.getDescription());
        result.setMapId(resource.getMapId());
        result.setMandatory(resource.isMandatory());

        // Den Typ auflösen
        FieldTypeEntity fieldType = fieldTypeRepository.findByKeyword(resource.getFieldType());

        // Überprüfen, ob wir einen Typ gefunden haben
        if (fieldType == null) {
            throw new BadRequestException("Unbekannter Feld-Typ: '" + resource.getFieldType() + "'");
        }
        result.setFieldType(fieldType);

        return result;
    }
}
