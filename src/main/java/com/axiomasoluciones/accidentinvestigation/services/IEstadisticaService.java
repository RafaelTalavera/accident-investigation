package com.axiomasoluciones.accidentinvestigation.services;

import com.axiomasoluciones.accidentinvestigation.models.entity.Accidents;
import com.axiomasoluciones.accidentinvestigation.models.entity.Estadistica;
import com.axiomasoluciones.accidentinvestigation.models.entity.EstadisticaMonthlySummary;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IEstadisticaService {

    public List<Estadistica> findAll();

    Optional<Estadistica> findById(String id);

    Estadistica save (Estadistica statistics);

    String editStatistics(String id, Estadistica editedStatistics);

    String extractUserEmailFromToken(String token);

    List<Estadistica> findByUserId(String userId);

    List<Estadistica> findDistinctOrganization();


    List<EstadisticaMonthlySummary> getMonthlyEstadisticaSummary(String nameOrganization);

}
