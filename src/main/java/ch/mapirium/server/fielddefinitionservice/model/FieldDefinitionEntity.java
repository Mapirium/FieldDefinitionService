package ch.mapirium.server.fielddefinitionservice.model;

import ch.mapirium.server.common.jpa.model.PublicIdEntity;

import javax.persistence.*;

/**
 * Repräsentiert die Definition eines Feldes
 */
@Entity
@Table(name = "fielddefinition")
public class FieldDefinitionEntity extends PublicIdEntity {

    /** Fremdschlüssel auf den Feld-Typ */
    @ManyToOne
    @JoinColumn(name = "type_fk", nullable = false)
    private FieldTypeEntity fieldType;

    /** Öffentlicher Schlüssel der Karte, zu welchem diese Definition gehört. */
    @Column(name = "map_id", nullable = false)
    private String mapId;

    /** Anschrift dieses Feldes. Wird dem Benutzer nebem dem eigentlichen Feld angezeigt */
    @Column(name = "label", nullable = false)
    private String label;

    /** Beschreibung dieses Feldes. Wird dem Benutzer zur Erklärung des Inhaltes angezeigt. */
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "mandatory", nullable = false)
    private boolean mandatory = false;

    public FieldTypeEntity getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldTypeEntity fieldType) {
        this.fieldType = fieldType;
    }

    public String getMapId() {
        return mapId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
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
}
