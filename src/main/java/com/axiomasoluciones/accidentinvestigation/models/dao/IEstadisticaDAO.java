package com.axiomasoluciones.accidentinvestigation.models.dao;


import com.axiomasoluciones.accidentinvestigation.models.entity.AccidentMonthlySummary;
import com.axiomasoluciones.accidentinvestigation.models.entity.Estadistica;
import com.axiomasoluciones.accidentinvestigation.models.entity.EstadisticaMonthlySummary;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IEstadisticaDAO extends MongoRepository<Estadistica, String> {

    @Query("{'userId':  {$regex : ?0, $options: 'i'}}")
    List<Estadistica> findByUserId(String userId);

    @Query(value = "{}", fields = "{ 'nameOrganization' : 1}")
    List<Estadistica> findDistinctOrganization();

    @Query("{'nameOrganization': ?0, 'yearEvent': ?1, 'monthEvent': ?2}")
    Optional<Estadistica> findByOrganizationAndYearAndMonth(String organization, int year, int month);

    @Aggregation({
            "{$match: {'nameOrganization': ?0}}",
            "{$group: {_id: {organization: '$nameOrganization', year: '$year', month: '$month'}, totalHours: {$sum: '$hours'}}}",
            "{$project: {organization: '$_id.organization', year: '$_id.year', month: '$_id.month', totalHours: '$totalHours'}}"
    })
    List<EstadisticaMonthlySummary> getMonthlyEstadisticaSummary(String nameOrganization);


}
