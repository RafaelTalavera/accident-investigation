package com.axiomasoluciones.accidentinvestigation.services;

import com.axiomasoluciones.accidentinvestigation.models.entity.Extinguisher;
import java.util.List;
import java.util.Optional;

public interface IExtinguisherService {

    public List<Extinguisher> findAll();

    Optional<Extinguisher> findById(String id);

    Extinguisher save(Extinguisher extinguisher);

    public void deleteById(String id);

    Extinguisher editExtinguisher(String id, Extinguisher extinguisher);

    Extinguisher chageEnabled(String id, Extinguisher extinguisher);

    String extractUserEmailFromToken(String token);

    public List<Extinguisher> findByUserId(String userId);

    List<Extinguisher> findExtinguisherByEmpresa(String empresa);

    int countByEmpresaAndSector(String empresa, String sector);

    int countVigentesByEmpresaAndSector(String empresa, String sector);

    int countVencidosByEmpresaAndSector(String empresa, String sector);

    int countByTipoAndEnabled(String tipo, boolean enabled);

    int countEnabledByEmpresaAndSector(String empresa, String sector);

    List<String> findAllCompanies();

}
