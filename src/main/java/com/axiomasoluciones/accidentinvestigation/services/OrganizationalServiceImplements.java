package com.axiomasoluciones.accidentinvestigation.services;

import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.dao.IOrganizationalDao;
import com.axiomasoluciones.accidentinvestigation.models.entity.Organizational;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationalServiceImplements implements IOrganizationalService {

    @Autowired
    private IOrganizationalDao organizationalDao;

    @Transactional(readOnly = true)
    @Override
    public List<Organizational> findAll() {
        return (List<Organizational>) organizationalDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Organizational> findById(Long id) {
        return organizationalDao.findById(id);
    }

    @Override
    @Transactional
    public Organizational save(Organizational investigation) {
        return organizationalDao.save(investigation);
    }

    @Override
    public void deleteById(Long id) {
        Organizational existInvestigation = organizationalDao.findById(id)
                .orElseThrow(() ->new RegistroNoEncontradoException("No se encontró ningún registro con el ID: \" + id"));
        organizationalDao.delete(existInvestigation);
    }

    @Override
    @Transactional
    public Organizational editOrganizational(Long id, Organizational editedInvestigation) {
        Organizational existInvestigation = organizationalDao.findById(id)
                .orElseThrow(() ->new RegistroNoEncontradoException("No se encontró ningún registro con el ID: \" + id"));
        existInvestigation.setAuthorizationDetails(editedInvestigation.getAuthorizationDetails());
        existInvestigation.setRiskDetails(editedInvestigation.getRiskDetails());
        existInvestigation.setRisk(editedInvestigation.getRisk());
        existInvestigation.setPtsDetails(editedInvestigation.getPtsDetails());
        existInvestigation.setPts(editedInvestigation.getPts());
        existInvestigation.setExpectedBehaviorDetails(editedInvestigation.getExpectedBehaviorDetails());
        existInvestigation.setExpectedBehavior(editedInvestigation.getExpectedBehavior());

        return organizationalDao.save(existInvestigation);
    }
}
