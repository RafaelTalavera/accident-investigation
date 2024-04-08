package com.axiomasoluciones.accidentinvestigation.models.dao;

import com.axiomasoluciones.accidentinvestigation.models.entity.Lai;;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ILaiDAO extends MongoRepository<Lai, String> {
    @Query("{'userId':  {$regex : ?0, $options: 'i'}}")
    List<Lai> findByUserId(String userId);

    List<Lai> findByOrganizationAndArea(String organization,String area);

    @Query(value = "{}", fields = "{ 'organization' : 1}")
    List<Lai> findDistinctOrganization();

    @Query(value = "{'organization': ?0}", fields = "{'area': 1}")
    List<Lai> findDistinctAreaByOrganization(String organization);

    @Query(value = "{'organization': ?0, 'area': ?1}", fields = "{'puesto': 1}")
    List<Lai> findDistinctPuestoByOrganizationAndArea(String organization, String area);
}
