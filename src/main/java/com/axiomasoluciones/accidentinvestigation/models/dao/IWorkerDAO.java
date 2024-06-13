package com.axiomasoluciones.accidentinvestigation.models.dao;

import com.axiomasoluciones.accidentinvestigation.models.entity.Worker;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IWorkerDAO extends MongoRepository<Worker, String> {

    @Query("{'userId': {$regex :  ?0, $options:  'i'}}")
    List<Worker> findByUserId(String userId);

    @Query(value = "{'userId': ?0}", fields = "{ 'nameOrganization' : 1}")
    List<Worker> findDistinctOrganizationByUserId(String userId);

    List<Worker> findWorkByUserIdAndNameOrganization(String userId, String nameOrganization);

    Optional<Worker> findByFingerPrint(byte[] fingerPrint);
}
