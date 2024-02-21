package com.axiomasoluciones.accidentinvestigation.services;

import com.axiomasoluciones.accidentinvestigation.models.entity.Event;
import com.axiomasoluciones.accidentinvestigation.models.entity.Risk;

import java.util.List;
import java.util.Optional;

public interface IRiskService {

    public List<Risk> findAll();

    Optional<Risk> findById(String id);

    Risk save(Risk risk);

    public void deleteById(String id);

    Risk editRisk(String id, Risk editedRisk);

    String extractUserEmailFromToken(String token);

    List<Risk> findByUserId(String userId);

}
