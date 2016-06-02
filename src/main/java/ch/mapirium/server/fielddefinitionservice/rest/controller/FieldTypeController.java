package ch.mapirium.server.fielddefinitionservice.rest.controller;

import ch.mapirium.server.common.springmvc.exceptions.NotFoundException;
import ch.mapirium.server.fielddefinitionservice.model.FieldTypeEntity;
import ch.mapirium.server.fielddefinitionservice.repo.FieldTypeRepository;
import ch.mapirium.server.fielddefinitionservice.rest.model.FieldTypeMapper;
import ch.mapirium.server.fielddefinitionservice.rest.model.FieldTypeResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * REST-Schnittstelle für den Feld-Typ
 */
@RestController
@RequestMapping(path = "/fieldtype")
public class FieldTypeController {

    @Autowired
    private FieldTypeRepository fieldTypeRepository;

    @Autowired
    private FieldTypeMapper fieldTypeMapper;

    @RequestMapping(method = RequestMethod.GET)
    public List<FieldTypeResource> getAll(){
        // Alle Typen laden
        Iterable<FieldTypeEntity> all = fieldTypeRepository.findAll();

        // Mappen
        List<FieldTypeResource> result = StreamSupport.stream(all.spliterator(), false).map(fieldTypeMapper::fromEntity).collect(Collectors.toList());
        return result;
    }

    @RequestMapping(path = "/{keyword}", method = RequestMethod.GET)
    public FieldTypeResource getByKeyword(@PathVariable("keyword") String keyword) {
        // Typ suchen
        FieldTypeEntity result = fieldTypeRepository.findByKeyword(keyword);

        // Wenn wir nichts gefunden haben geben wir einen 404 zurück
        if (result == null) {
            throw new NotFoundException("Feld-Type '" + keyword + "' nicht gefunden");
        } else {
            return fieldTypeMapper.fromEntity(result);
        }
    }
}
