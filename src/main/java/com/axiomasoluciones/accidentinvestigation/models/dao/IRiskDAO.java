package com.axiomasoluciones.accidentinvestigation.models.dao;

import com.axiomasoluciones.accidentinvestigation.models.entity.Risk;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface IRiskDAO extends MongoRepository<Risk, String> {

    @Query("{'userId':  {$regex : ?0, $options: 'i'}}")
    List<Risk> findByUserId(String userId);

    List<Risk> findRiskByNameOrganizationAndAreaAndPuesto(String nameOrganization, String area, String puesto);

    @Query(value = "{'userId': ?0}", fields = "{ 'nameOrganization' : 1}")
    List<Risk> findDistinctOrganizationByUserId(String userId);

    @Query(value = "{'nameOrganization': ?0}", fields = "{'area': 1}")
    List<Risk> findDistinctAreaByNameOrganization(String nameOrganization);

    @Query(value = "{'nameOrganization': ?0, 'area': ?1}", fields = "{'puesto': 1}")
    List<Risk> findDistinctPuestoByNameOrganizationAndArea(String nameOrganization, String area);

    List<Risk> findRiskByUserIdAndNameOrganization(String userId, String nameOrganization);


}
