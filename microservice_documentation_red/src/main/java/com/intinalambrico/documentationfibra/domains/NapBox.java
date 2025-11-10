package com.intinalambrico.documentationfibra.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.bson.codecs.pojo.annotations.BsonId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@MongoEntity(collection = "nap_boxes")
public class NapBox extends PanacheMongoEntityBase {



    @BsonId
    public String id;

    @NotBlank(message = "El nombre de la NAP es requerido")
    @JsonProperty("name_nap")
    private String nameNap;

    @NotNull(message = "Reference es importante")
    private Integer reference;

    @NotNull(message = "Estación es importante")
    private Integer idEstacion;

    @NotBlank(message = "El tipo de NAP es requerido")
    private String type; // "aerial", "underground", "wall-mounted", etc.

    @NotNull(message = "Los puertos son requeridos")
    private List<Port> ports = new ArrayList<>();

    @JsonProperty("color_in")
    private String colorIn; // Color de identificación de la fibra de entrada

    @NotNull(message = "La posición es requerida")
    private Position position;

    private String imageUrl; // URL de la imagen de la caja NAP

    private String description;

    private String address;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // Constructors
    public NapBox() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameNap() {
        return nameNap;
    }

    public void setNameNap(String nameNap) {
        this.nameNap = nameNap;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Port> getPorts() {
        return ports;
    }

    public void setPorts(List<Port> ports) {
        this.ports = ports;
    }

    public String getColorIn() {
        return colorIn;
    }

    public void setColorIn(String colorIn) {
        this.colorIn = colorIn;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getReference() {
        return reference;  }

    public void setReference(Integer reference) {
        this.reference = reference;}

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getIdEstacion() {
        return idEstacion;
    }

    public void setIdEstacion(Integer idEstacion) {
        this.idEstacion = idEstacion;
    }
}
