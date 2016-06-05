package ch.mapirium.server.fielddefinitionservice.rest.model;

import org.springframework.hateoas.ResourceSupport;

import java.util.Date;

/**
 * Definiert die REST-Resource für eine Feld-Definition
 */
public class FieldDefinitionResource extends ResourceSupport {

    /** Öffentlicher Schlüssel */
    private String publicId;

    /** Typ dieses Feldes */
    private String fieldType;

    /** Öffentlicher Schlüssel der Karte, zu welchem diese Definition gehört. */
    private String mapId;

    /** Öffentlicher Schlüssel der Karte, zu welchem diese Definition gehört. */
    private String pointDefinitionId;

    /** Anschrift dieses Feldes. Wird dem Benutzer nebem dem eigentlichen Feld angezeigt */
    private String label;

    /** Beschreibung dieses Feldes. Wird dem Benutzer zur Erklärung des Inhaltes angezeigt. */
    private String description;

    /** Gibt an, ob dieses Feld zwingend auszufüllen ist */
    private boolean mandatory = false;

    private Date createdAt;

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getMapId() {
        return mapId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

    public String getPointDefinitionId() {
        return pointDefinitionId;
    }

    public void setPointDefinitionId(String pointDefinitionId) {
        this.pointDefinitionId = pointDefinitionId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
