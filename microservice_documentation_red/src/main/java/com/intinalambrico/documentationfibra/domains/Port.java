package com.intinalambrico.documentationfibra.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import jakarta.validation.constraints.NotNull;

public class Port {
    @NotNull(message = "El número de puerto es requerido")
    @JsonPropertyDescription("number_port")
    private Integer numberPort;

    @JsonProperty("id_contrato")
    private String idContrato;

    private String status; // "available", "occupied", "damaged"



    private String observation;
    // Constructors
    public Port() {
    }

    public Port(Integer numberPort, String idContrato) {
        this.numberPort = numberPort;
        this.idContrato = idContrato;
        this.status = "available";
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    // Getters and Setters
    public Integer getNumberPort() {
        return numberPort;
    }

    public void setNumberPort(Integer numberPort) {
        this.numberPort = numberPort;
    }

    public String getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(String idContrato) {
        this.idContrato = idContrato;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
