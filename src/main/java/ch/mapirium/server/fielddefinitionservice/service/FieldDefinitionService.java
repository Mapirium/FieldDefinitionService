package ch.mapirium.server.fielddefinitionservice.service;

import ch.mapirium.server.fielddefinitionservice.model.FieldDefinitionEntity;
import ch.mapirium.server.fielddefinitionservice.repo.FieldDefinitionRepository;
import ch.mapirium.server.fielddefinitionservice.rest.gateway.PublicIdGateway;
import ch.mapirium.server.fielddefinitionservice.rest.model.PublicIdResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Verwaltet die Feld-Definitionen
 */
@Component
public class FieldDefinitionService {

    @Autowired
    private FieldDefinitionRepository fieldDefinitionRepository;

    @Autowired
    private PublicIdGateway publicIdGateway;

    public FieldDefinitionEntity createDefinition(FieldDefinitionEntity newDefinition) {
        // Öffentliche ID lösen
        PublicIdResource publicId = publicIdGateway.createNewPublicId();
        newDefinition.setPublicId(publicId.getPublicId());

        // Definition speichern
        FieldDefinitionEntity saved = fieldDefinitionRepository.save(newDefinition);
        return saved;
    }
}
