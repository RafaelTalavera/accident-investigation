package com.axiomasoluciones.accidentinvestigation.models.dao;


import com.axiomasoluciones.accidentinvestigation.models.entity.Extinguisher;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IExtinguisherDAO extends CrudRepository<Extinguisher, String> {
    @Query("{'userId':  {$regex : ?0, $options: 'i'}}")
    List<Extinguisher> findExtinguisherByUserId(String userId);

    @Query(value = "distinct('nameOrganization')")
    List<String> findAllCompanies();

    List<Extinguisher> findExtinguisherByNameOrganization(String nameOrganization);

    List<Extinguisher> findExtinguisherByNameOrganizationAndSector(String nameOrganization, String sector);

    List<Extinguisher> findExtinguisherByTipo(String tipo);

    List<Extinguisher> findExtinguisherByTipoAndEnabled(String tipo, boolean enabled);

}
