package com.intinalambrico.documentationfibra.domains;

import jakarta.validation.constraints.NotNull;

public class Position {
    @NotNull(message = "La latitud es requerida")
    private Double lat;

    @NotNull(message = "La longitud es requerida")
    private Double lng;

    // Constructors
    public Position() {
    }

    public Position(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    // Getters and Setters
    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
