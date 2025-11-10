package com.intinalambrico.documentationfibra.services;

import com.intinalambrico.documentationfibra.domains.NapBox;
import com.intinalambrico.documentationfibra.domains.Port;
import com.intinalambrico.documentationfibra.repository.NapBoxRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class NapBoxService {
    @Inject
    NapBoxRepository napBoxRepository;

    public List<NapBox> findAll() {
        return napBoxRepository.listAll();
    }

    public NapBox findById(String id) {
        return napBoxRepository.find("_id", id).firstResult();
    }

    public List<NapBox> findByType(String type , String idEstacion) {
        return napBoxRepository.findByTypeAnIdEstacion(type , Integer.parseInt(idEstacion));
    }

    public Optional<NapBox> findByNameNap(String nameNap) {
        return Optional.ofNullable(napBoxRepository.findByNameNap(nameNap));
    }

    public List<NapBox> findByArea(Double minLat, Double maxLat, Double minLng, Double maxLng) {
        return napBoxRepository.findByArea(minLat, maxLat, minLng, maxLng);
    }


    public NapBox create(NapBox napBox) {
        napBox.setCreatedAt(LocalDateTime.now());
        napBox.setUpdatedAt(LocalDateTime.now());
        napBoxRepository.persist(napBox);
        return napBox;
    }


    public Optional<NapBox> update(  NapBox napBox) {
        try {
            NapBox existing = napBoxRepository.find("_id" , napBox.id).firstResult();
            if (existing != null) {
                existing.setNameNap(napBox.getNameNap());
                existing.setType(napBox.getType());
                existing.setPorts(napBox.getPorts());
                existing.setColorIn(napBox.getColorIn());
                existing.setPosition(napBox.getPosition());
                existing.setImageUrl(napBox.getImageUrl());
                existing.setDescription(napBox.getDescription());
                existing.setAddress(napBox.getAddress());
                existing.setUpdatedAt(LocalDateTime.now());
                napBoxRepository.update(existing);
                return Optional.of(existing);
            }
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
        return Optional.empty();
    }


    public boolean delete(String id) {
        try {
            return napBoxRepository.deleteById(new ObjectId(id));
        } catch (IllegalArgumentException e) {
            return false;
        }
    }


    public Optional<NapBox> updatePort(String napBoxId, Integer portNumber, Port updatedPort) {
        try {
            NapBox napBox = napBoxRepository.findById(new ObjectId(napBoxId));
            if (napBox != null && napBox.getPorts() != null) {
                napBox.getPorts().stream()
                        .filter(p -> p.getNumberPort().equals(portNumber))
                        .findFirst()
                        .ifPresent(port -> {
                            port.setIdContrato(updatedPort.getIdContrato());
                            port.setStatus(updatedPort.getStatus());
                        });
                napBox.setUpdatedAt(LocalDateTime.now());
                napBoxRepository.update(napBox);
                return Optional.of(napBox);
            }
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    public long countAvailablePorts(String napBoxId) {
        return napBoxRepository.countAvailablePorts(napBoxId);
    }
}
