package com.axiomasoluciones.accidentinvestigation.services;


import com.axiomasoluciones.accidentinvestigation.models.entity.Risk;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IRiskService {

    public List<Risk> findAll();

    Optional<Risk> findById(String id);

    Risk save(Risk risk);

    public void deleteById(String id);

    Risk editRisk(String id, Risk editedRisk);

    String extractUserEmailFromToken(String token);

    List<Risk> findByUserId(String userId);

    Map<String, Integer> countClasificaMCByAreaAndPuesto(String area, String puesto);

    Map<String, Map<String, Integer>> countEvaluacionByAreaAndPuesto(String area, String puesto);

}
