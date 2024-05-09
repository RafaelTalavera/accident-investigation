package com.axiomasoluciones.accidentinvestigation.services;

import com.axiomasoluciones.accidentinvestigation.models.entity.Lai;


import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ILaiSevice {

    public List<Lai> findAll();

    public Optional<Lai> findById(String id);

    public Lai save(Lai lai);

    public void deteleById(String id);

    public Lai editLai(String id, Lai editeLai);

    String extractUserEmailFromToken(String token);

    List<Lai> findByUserId(String userId);

    List<Lai> findDistinctOrganizationByUserId(String userId);

    List<Lai> findDistinctAreaByNameOrganization(String nameOrganization);

    Map<String, Integer> countTypeOfControlByNameOrganizationAndArea(String nameOrganization, String area);

    Map<String, Map<String, Integer>> countMeaningfulnessByNameOrganizationAndArea(String nameOrganization,String area);

    List<Lai> findLaiByUserIdAndNameOrganization(String userId, String nameOrganization);

}
