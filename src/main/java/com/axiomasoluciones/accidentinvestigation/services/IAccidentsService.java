package com.axiomasoluciones.accidentinvestigation.services;

import com.axiomasoluciones.accidentinvestigation.models.entity.Accidents;
import com.axiomasoluciones.accidentinvestigation.models.entity.AccidentMonthlySummary;


import java.util.List;
import java.util.Optional;

public interface IAccidentsService {

    public List<Accidents> findAll();

    public Optional<Accidents> findById(String id);

    public Accidents save(Accidents accidents);

    public void deteleById(String id);

    String extractUserEmailFromToken(String token);

    List<Accidents> findByUserId(String userId);

    public long calcularLostDay(Accidents accident);

    public List<Accidents> getAccidentsByOrganization(String organization);

    public List<AccidentMonthlySummary> getMonthlyAccidentSummary(String nameOrganization);

    Accidents editAccidents(String id, Accidents editedAccidents);

    List<Accidents> findAccidentsByUserIdAndNameOrganization(String userId, String nameOrganization);

}
