package com.axiomasoluciones.accidentinvestigation.models.dao;


import com.axiomasoluciones.accidentinvestigation.models.entity.Extinguisher;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IExtinguisherDao extends CrudRepository<Extinguisher, String> {
    @Query("{'userId':  {$regex : ?0, $options: 'i'}}")
    List<Extinguisher> findExtinguisherByUserId(String userId);

    @Query(value = "distinct('empresa')")
    List<String> findAllCompanies();

    List<Extinguisher> findExtinguisherByEmpresa(String empresa);

    List<Extinguisher> findExtinguisherByEmpresaAndSector(String empresa, String sector);

    List<Extinguisher> findExtinguisherByTipo(String tipo);

    List<Extinguisher> findExtinguisherByTipoAndEnabled(String tipo, boolean enabled);

}
