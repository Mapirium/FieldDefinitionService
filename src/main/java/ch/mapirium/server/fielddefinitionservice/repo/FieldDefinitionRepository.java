package ch.mapirium.server.fielddefinitionservice.repo;

import ch.mapirium.server.fielddefinitionservice.model.FieldDefinitionEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository für den Zugriff auf die Feld-Definitionen
 */
public interface FieldDefinitionRepository extends CrudRepository<FieldDefinitionEntity, Long> {
}
