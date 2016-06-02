package ch.mapirium.server.fielddefinitionservice.rest.model;

import org.springframework.hateoas.ResourceSupport;

import java.util.Date;

/**
 * Definiert die REST-Resource für den Feld-Typ
 */
public class FieldTypeResource extends ResourceSupport {

    private Date createdAt;

    private String keyword;

    /** Name dieses Feld-Types */
    private String name;

    /** Beschreibung dieses Types */
    private String description;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

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
