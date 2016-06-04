package ch.mapirium.server.fielddefinitionservice.rest.controller;

import ch.mapirium.server.common.springmvc.exceptions.NotFoundException;
import ch.mapirium.server.fielddefinitionservice.model.FieldDefinitionEntity;
import ch.mapirium.server.fielddefinitionservice.repo.FieldDefinitionRepository;
import ch.mapirium.server.fielddefinitionservice.rest.model.FieldDefinitionMapper;
import ch.mapirium.server.fielddefinitionservice.rest.model.FieldDefinitionResource;
import ch.mapirium.server.fielddefinitionservice.service.FieldDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST-Schnittstelle für die Feld-Definition
 */
@RestController
@RequestMapping(path = "/map/{mapId}/fielddefinition")
public class FieldDefinitionRestController {

    @Autowired
    private FieldDefinitionRepository fieldDefinitionRepository;

    @Autowired
    private FieldDefinitionMapper fieldDefinitionMapper;

    @Autowired
    private FieldDefinitionService fieldDefinitionService;

    @RequestMapping(method = RequestMethod.GET)
    public List<FieldDefinitionResource> getAll(@PathVariable("mapId") String mapId) {
        // Daten laden
        List<FieldDefinitionEntity> fields = fieldDefinitionRepository.findByMapId(mapId);

        // Mappen
        List<FieldDefinitionResource> result = (List<FieldDefinitionResource>) fields.stream().map(fieldDefinitionMapper::fromEntity).collect(Collectors.toList());
        return result;
    }

    @RequestMapping(path = "/{publicId}", method = RequestMethod.GET)
    public FieldDefinitionResource findByPublicId(@PathVariable("mapId") String mapId, @PathVariable("publicId") String publicId) {
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
    public FieldDefinitionResource create(@PathVariable("mapId") String mapId, @RequestBody FieldDefinitionResource fieldDefinition) {
        // Karten-ID übernehmen
        fieldDefinition.setMapId(mapId);

        // Entität erstellen
        FieldDefinitionEntity entity = fieldDefinitionMapper.toEntity(fieldDefinition);

        // Speichern
        FieldDefinitionEntity savedEntity = fieldDefinitionService.createDefinition(entity);

        // Mappen und zurückgeben
        FieldDefinitionResource result = fieldDefinitionMapper.fromEntity(savedEntity);
        return result;
    }
}
