package ch.mapirium.server.fielddefinitionservice.repo;

import ch.mapirium.server.fielddefinitionservice.model.FieldDefinitionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository für den Zugriff auf die Feld-Definitionen
 */
public interface FieldDefinitionRepository extends CrudRepository<FieldDefinitionEntity, Long> {

    @Query("SELECT f FROM FieldDefinitionEntity f WHERE f.mapId = :mapId")
    List<FieldDefinitionEntity> findByMapId(@Param("mapId") String mapId);

    @Query("SELECT f FROM FieldDefinitionEntity f WHERE f.mapId = :mapId AND f.publicId = :publicId")
    FieldDefinitionEntity findByPublicId(@Param("mapId") String mapId, @Param("publicId") String publicId);
}
