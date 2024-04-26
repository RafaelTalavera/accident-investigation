package com.axiomasoluciones.accidentinvestigation.services;

import com.axiomasoluciones.accidentinvestigation.models.entity.Organization;

import java.util.List;
import java.util.Optional;

public interface IOrganizationService {

    public List<Organization> findAll();

    Optional<Organization> findById(String id);

    Organization save(Organization organization);

    public void deleteby(String id);

    Organization editOrganization(String id, Organization editeOrg);

    String extractUserEmailFromToken(String token);

    List<Organization> findByUserId(String userId);

    List<Organization> findDistinctOrganization();



}
