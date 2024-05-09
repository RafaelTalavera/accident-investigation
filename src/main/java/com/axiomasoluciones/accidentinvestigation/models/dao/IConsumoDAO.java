package com.axiomasoluciones.accidentinvestigation.models.dao;

import com.axiomasoluciones.accidentinvestigation.models.entity.Consumo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface IConsumoDAO extends MongoRepository<Consumo, String> {

    @Query("{'userId':  {$regex : ?0, $options: 'i'}}")
    List<Consumo> findByUserId(String userId);

    @Query(value = "{'userId': ?0}", fields = "{ 'nameOrganization' : 1}")
    List<Consumo> findDistinctOrganizationByUserId(String userId);

    @Query(value = "{'nameOrganization': ?0} {'combustible': {'$regex' : '.*', '$options': 'i'}, '_id': 0}")
    List<String> findDistinctCombustibleByNameOrganization(String nameOrganization);

    @Query(value = "{'nameOrganization': ?0, 'combustible': ?1}", fields = "{'unidad' : 1, '_id' : 0}")
    List<String> findDistinctUnidadByOrganizationAndCombustible(@Param("nameOrganization") String nameOrganization, @Param("combustible") String combustible);

    @Query("{'nameOrganization': ?0, 'combustible': ?1, 'unidad': ?2}")
    List<Consumo> sumConsumoByOrganizationCombustibleAndUnidad(
            String nameOrganization,
            String combustible,
            String unidad
    );

    @Query("{'nameOrganization': ?0, 'combustible': ?1, 'unidad': ?2}")
    List<Consumo> findConsumoByOrganizationCombustibleAndUnidad(
            String nameOrganization,
            String combustible,
            String unidad
    );

    List<Consumo> findConsumoByUserIdAndNameOrganization(String userId, String nameOrganization);

}
