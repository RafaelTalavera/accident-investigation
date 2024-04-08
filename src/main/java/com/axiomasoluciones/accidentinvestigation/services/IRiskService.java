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

    List<Risk> findDistinctOrganization();

    List<Risk> findDistinctAreaByOrganization(String organization);

    List<Risk> findDistinctPuestoByOrganizationAndArea(String organization, String area);

    Map<String, Integer> countClasificaMCByOrganizationAndAreaAndPuesto(String organization, String area, String puesto);

    Map<String, Map<String, Integer>> countEvaluacionByOrganizationAndAreaAndPuesto(String organization,String area, String puesto);

}
