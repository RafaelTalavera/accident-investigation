package com.axiomasoluciones.accidentinvestigation.services;

import com.axiomasoluciones.accidentinvestigation.models.dao.IWorkEquipmetDao;
import com.axiomasoluciones.accidentinvestigation.models.entity.WorkEquipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class WorkEquipmentServiceImplements implements IWorkEquipmetService{

    @Autowired
    private IWorkEquipmetDao workEquipmetDao;


    @Override
    @Transactional(readOnly = true)
    public List<WorkEquipment> findAll() {
        return (List<WorkEquipment>) workEquipmetDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WorkEquipment> findById(Long id) {
        return workEquipmetDao.findById(id);
    }

    @Override
    @Transactional
    public WorkEquipment save(WorkEquipment workEquipment) {
        return workEquipmetDao.save(workEquipment);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
       workEquipmetDao.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(WorkEquipment workEquipment) {
        workEquipmetDao.delete(workEquipment);

    }


}
