package com.axiomasoluciones.accidentinvestigation.models.dao;

import com.axiomasoluciones.accidentinvestigation.models.entity.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface IOrganizationDAO extends MongoRepository<Organization, String> {

    @Query("{'userId': {$regex :  ?0, $options:  'i'}}")
    List<Organization> findByUserId(String userId);

    @Query(value = "{}", fields = "{ 'name' : 1}")
    List<Organization> findDistinctOrganization();

}
