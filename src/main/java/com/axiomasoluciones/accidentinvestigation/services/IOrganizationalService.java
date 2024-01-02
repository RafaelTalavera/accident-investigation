package com.axiomasoluciones.accidentinvestigation.services;

import com.axiomasoluciones.accidentinvestigation.models.entity.Organizational;

import java.util.List;
import java.util.Optional;

public interface IOrganizationalService {

    public List<Organizational> findAll();

    Optional<Organizational> findById(Long id);

    Organizational save(Organizational organizational);

    public void deleteById(Long id);

    Organizational editOrganizational(Long id, Organizational editedOrganizational);
}
