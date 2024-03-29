package com.axiomasoluciones.accidentinvestigation.services;



import com.axiomasoluciones.accidentinvestigation.models.dao.IUserDao;
import com.axiomasoluciones.accidentinvestigation.models.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplements implements IUserService{

    @Autowired
    private IUserDao userDao;



    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) userDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(String id) {
        return userDao.findById(id);
    }

    @Override
    @Transactional
    public User createUser(User user) {
        return userDao.save(user);
    }


    @Override
    public void delete(User user) {
        userDao.delete(user);
    }
}
