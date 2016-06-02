package ch.mapirium.server.fielddefinitionservice.repo;

import ch.mapirium.server.fielddefinitionservice.model.FieldTypeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Repository für den Zugriff auf die Feld-Typen
 */
public interface FieldTypeRepository extends CrudRepository<FieldTypeEntity, Long> {

    @Query("SELECT t FROM FieldTypeEntity t WHERE t.keyword = :keyword")
    public FieldTypeEntity findByKeyword(@Param("keyword") String keyword);
}
