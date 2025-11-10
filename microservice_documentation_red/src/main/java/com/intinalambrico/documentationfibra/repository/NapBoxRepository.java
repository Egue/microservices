package com.intinalambrico.documentationfibra.repository;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.intinalambrico.documentationfibra.domains.NapBox;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class NapBoxRepository implements PanacheMongoRepository<NapBox > {

    public List<NapBox> findByTypeAnIdEstacion(String type , Integer idEstacion) {
        return list("type = ?1 and idEstacion = ?2", type, idEstacion);
    }

    public NapBox findByNameNap(String nameNap) {
        return find("nameNap", nameNap).firstResult();
    }

    public List<NapBox> findByArea(Double minLat, Double maxLat, Double minLng, Double maxLng) {
        return find("position.lat >= ?1 and position.lat <= ?2 and position.lng >= ?3 and position.lng <= ?4",
                minLat, maxLat, minLng, maxLng).list();
    }

    public long countAvailablePorts(String napBoxId) {
        NapBox napBox = findById(new org.bson.types.ObjectId(napBoxId));
        if (napBox != null && napBox.getPorts() != null) {
            return napBox.getPorts().stream()
                    .filter(port -> "available".equals(port.getStatus()))
                    .count();
        }
        return 0;
    }
}
