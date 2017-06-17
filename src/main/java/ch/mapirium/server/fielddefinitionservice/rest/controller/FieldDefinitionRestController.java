package ch.mapirium.server.fielddefinitionservice.rest.controller;

import ch.mapirium.server.common.springmvc.exceptions.NotFoundException;
import ch.mapirium.server.fielddefinitionservice.model.FieldDefinitionEntity;
import ch.mapirium.server.fielddefinitionservice.repo.FieldDefinitionRepository;
import ch.mapirium.server.fielddefinitionservice.rest.model.FieldDefinitionListMapper;
import ch.mapirium.server.fielddefinitionservice.rest.model.FieldDefinitionListResource;
import ch.mapirium.server.fielddefinitionservice.rest.model.FieldDefinitionMapper;
import ch.mapirium.server.fielddefinitionservice.rest.model.FieldDefinitionResource;
import ch.mapirium.server.fielddefinitionservice.service.FieldDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST-Schnittstelle für die Feld-Definition
 */
@RestController
@RequestMapping(path = "/map/{mapId}/pointdefinition/{pointDefId}/fielddefinition")
public class FieldDefinitionRestController {

    @Autowired
    private FieldDefinitionRepository fieldDefinitionRepository;

    @Autowired
    private FieldDefinitionMapper fieldDefinitionMapper;

    @Autowired
    private FieldDefinitionListMapper fieldDefinitionListMapper;

    @Autowired
    private FieldDefinitionService fieldDefinitionService;

    @RequestMapping(method = RequestMethod.GET)
    public FieldDefinitionListResource getAll(@PathVariable("mapId") String mapId, @PathVariable("pointDefId") String pointDefId) {
        // Daten laden
        List<FieldDefinitionEntity> fields = fieldDefinitionRepository.findByMapId(mapId);

        // Mappen
        FieldDefinitionListResource result = fieldDefinitionListMapper.fromEntity(fields, mapId, pointDefId);
        return result;
    }

    @RequestMapping(path = "/{publicId}", method = RequestMethod.GET)
    public FieldDefinitionResource findByPublicId(@PathVariable("mapId") String mapId, @PathVariable("pointDefId") String pointDefId, @PathVariable("publicId") String publicId) {
        // Daten laden
        FieldDefinitionEntity field = fieldDefinitionRepository.findByPublicId(mapId, publicId);

        if (field == null) {
            // Wenn wir nichts gefunden haben geben wir einen 404 zurück
            throw new NotFoundException("Keine Feld-Definition mit der ID '" + publicId + "' gefunden");
        } else {
            // Mappen
            FieldDefinitionResource result = fieldDefinitionMapper.fromEntity(field);
            return result;
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public FieldDefinitionResource create(@PathVariable("mapId") String mapId, @PathVariable("pointDefId") String pointDefId, @RequestBody FieldDefinitionResource fieldDefinition) {
        // IDs aus URL übernehmen
        fieldDefinition.setMapId(mapId);
        fieldDefinition.setPointDefinitionId(pointDefId);

        // Entität erstellen
        FieldDefinitionEntity entity = fieldDefinitionMapper.toEntity(fieldDefinition);

        // Speichern
        FieldDefinitionEntity savedEntity = fieldDefinitionService.createDefinition(entity);

        // Mappen und zurückgeben
        FieldDefinitionResource result = fieldDefinitionMapper.fromEntity(savedEntity);
        return result;
    }
}
