package ch.mapirium.server.fielddefinitionservice.model;

import ch.mapirium.server.common.jpa.model.CreateTimeTrackEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Repräsentiert den möglichen Typ eines Feldes
 */
@Entity
@Table(name = "fieldtype")
public class FieldTypeEntity extends CreateTimeTrackEntity {

    /** Eindeutiges Schlüsselwort für diesen Typ */
    @Column(name = "keyword", nullable = false, unique = true)
    private String keyword;

    /** Name dieses Feld-Types */
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    /** Beschreibung dieses Types */
    @Column(name = "description", nullable = false)
    private String description;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
