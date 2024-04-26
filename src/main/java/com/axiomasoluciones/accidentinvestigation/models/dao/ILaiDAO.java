package com.axiomasoluciones.accidentinvestigation.models.dao;

import com.axiomasoluciones.accidentinvestigation.models.entity.Lai;;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ILaiDAO extends MongoRepository<Lai, String> {
    @Query("{'userId':  {$regex : ?0, $options: 'i'}}")
    List<Lai> findByUserId(String userId);

    List<Lai> findByNameOrganizationAndArea(String nameOrganization,String area);

    @Query(value = "{}", fields = "{ 'nameOrganization' : 1}")
    List<Lai> findDistinctNameOrganization();

    @Query(value = "{'nameOrganization': ?0}", fields = "{'area': 1}")
    List<Lai> findDistinctAreaByNameOrganization(String nameOrganization);

    @Query(value = "{'nameOrganizationorganization': ?0, 'area': ?1}", fields = "{'puesto': 1}")
    List<Lai> findDistinctPuestoByONameOrganizationAndArea(String nameOrganization, String area);
}
