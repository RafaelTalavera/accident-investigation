package com.axiomasoluciones.accidentinvestigation.models.dao;

import com.axiomasoluciones.accidentinvestigation.models.entity.Accidents;

import com.axiomasoluciones.accidentinvestigation.models.entity.AccidentMonthlySummary;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface IAccidentsDAO extends MongoRepository<Accidents,String> {

    @Query("{'userId':  {$regex : ?0, $options: 'i'}}")
    List<Accidents> findByUserId(String userId);

    @Query(value = "{}", fields = "{ 'nameOrganization' : 1}")
    List<Accidents> findDistinctOrganization();

    @Aggregation({
            "{$match: {'nameOrganization': ?0}}",
            "{$group: {_id: {organization: '$nameOrganization', year: '$yearEvent', month: '$monthEvent'}, totalAccidents: {$sum: 1}}}",
            "{$project: {organization: '$_id.organization', year: '$_id.year', month: '$_id.month', totalAccidents: '$totalAccidents'}}"
    })
    List<AccidentMonthlySummary> getMonthlyAccidentSummary(String nameOrganization);

    @Query("{'nameOrganization': ?0, 'yearEvent': ?1, 'monthEvent': ?2}")
    List<Accidents> findByOrganizationAndYearAndMonth(String organization, int year, int month);

    List<Accidents> findAccidentsByUserIdAndNameOrganization(String userId, String nameOrganization);
}
