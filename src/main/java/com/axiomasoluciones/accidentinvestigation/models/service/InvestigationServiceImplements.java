package com.axiomasoluciones.accidentinvestigation.models.service;

import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.dao.IInvestigationDao;
import com.axiomasoluciones.accidentinvestigation.models.entity.Investigation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InvestigationServiceImplements implements IInvestigationService{

    @Autowired
    private IInvestigationDao investigationDao;

    @Transactional(readOnly = true)
    @Override
    public List<Investigation> findAll() {
        return (List<Investigation>) investigationDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Investigation> findById(Long id) {
        return investigationDao.findById(id);
    }

    @Override
    @Transactional
    public Investigation save(Investigation investigation) {
        return investigationDao.save(investigation);
    }

    @Override
    public void deleteByIdEvent(Long id) {
        Investigation existInvestigation = investigationDao.findById(id)
                .orElseThrow(() ->new RegistroNoEncontradoException("No se encontró ningún registro con el ID: \" + id"));
       investigationDao.delete(existInvestigation);
    }

    @Override
    @Transactional
    public Investigation editEvent(Long id, Investigation editedInvestigation) {
        Investigation existInvestigation = investigationDao.findById(id)
                .orElseThrow(() ->new RegistroNoEncontradoException("No se encontró ningún registro con el ID: \" + id"));

        existInvestigation.setClimate(editedInvestigation.getClimate());
        existInvestigation.setWorkEquipment(editedInvestigation.getWorkEquipment());
        existInvestigation.setWorkOccasionDetails(editedInvestigation.getWorkOccasionDetails());
        existInvestigation.setWorkOccasion(editedInvestigation.getWorkOccasion());
        existInvestigation.setAuthorizationDetails(editedInvestigation.getAuthorizationDetails());
        existInvestigation.setRiskDetails(editedInvestigation.getRiskDetails());
        existInvestigation.setRisk(editedInvestigation.getRisk());
        existInvestigation.setPtsDetails(editedInvestigation.getPtsDetails());
        existInvestigation.setPts(editedInvestigation.getPts());
        existInvestigation.setTrainingDetails(editedInvestigation.getTrainingDetails());
        existInvestigation.setTraining(editedInvestigation.getTraining());
        existInvestigation.setExpectedBehaviorDetails(editedInvestigation.getExpectedBehaviorDetails());
        existInvestigation.setExpectedBehavior(editedInvestigation.getExpectedBehavior());
        existInvestigation.setDefenseFailedDetails(editedInvestigation.getDefenseFailedDetails());
        existInvestigation.setDefenseFailed(editedInvestigation.getDefenseFailed());

        return investigationDao.save(existInvestigation);
    }
}
