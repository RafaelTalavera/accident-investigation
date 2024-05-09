package com.axiomasoluciones.accidentinvestigation.services.implemets;



import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.dao.IUserDAO;
import com.axiomasoluciones.accidentinvestigation.models.entity.Risk;
import com.axiomasoluciones.accidentinvestigation.models.entity.User;
import com.axiomasoluciones.accidentinvestigation.services.IUserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplements implements IUserService {

    @Autowired
    private IUserDAO userDao;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;



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

    @Override
    public User editeUser(String id, User editeUser) {
        User existUser = userDao.findById(id)
                .orElseThrow(() -> new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id));
        existUser.setFirebase(editeUser.getFirebase());

        return userDao.save(existUser);
    }

    @Override
    public String extractUserEmailFromToken(String token) {
        try {
            String jwtToken = token.replace("Bearer ", "");
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwtToken).getBody();
            return claims.get("mail", String.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al extraer el correo electrónico del token", e);
        }
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }
}
