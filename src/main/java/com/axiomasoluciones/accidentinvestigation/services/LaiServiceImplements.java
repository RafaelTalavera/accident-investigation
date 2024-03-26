package com.axiomasoluciones.accidentinvestigation.services;

import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.dao.ILaiDAO;
import com.axiomasoluciones.accidentinvestigation.models.entity.Lai;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LaiServiceImplements implements ILaiSevice {

    @Autowired
    private ILaiDAO laiDAO;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    @Override
    @Transactional(readOnly = true)
    public List<Lai> findAll() {
        return (List<Lai>) laiDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Lai> findAll(String id) {

        return laiDAO.findById(id);
    }

    @Override
    @Transactional
    public Lai save(Lai lai) {

        int damage = lai.getDamage();
        int frequency = lai.getFrequency();

        int resultado = damage * frequency;

        // Verificar condiciones para clasificar según las categorías dadas
        if (resultado >= 1 && resultado <= 4 && resultado >= 1 && resultado <= 4) {
            risk.setEvaluacion("Aceptable");
        } else if (resultado >= 5 && resultado <= 9 && resultado >= 5 && resultado <= 9) {
            risk.setEvaluacion("Adecuado");
        } else if (resultado >= 10 && resultado <= 16 && resultado >= 10 && resultado <= 16) {
            risk.setEvaluacion("Tolerable");
        } else if (resultado >= 17 && resultado <= 25 && resultado >= 17 && resultado <= 25) {
            risk.setEvaluacion("Inaceptable");
        }

        return laiDAO.save(lai);
    }

    @Override
    @Transactional
    public void deteleById(String id) {

        Lai existingLai = laiDAO.findById(id)
                .orElseThrow(() -> new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id));
        laiDAO.deleteById(id);

    }

    @Override
    @Transactional
    public Lai editLai(String id, Lai editeLai) {
        return null;
    }

    @Override
    @Transactional
    public String extractUserEmailFromToken(String token) {
        try {
            // Remover la palabra "Bearer " del inicio del token
            String jwtToken = token.replace("Bearer ", "");
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwtToken).getBody();
            return claims.get("mail", String.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al extraer el correo electrónico del token", e);
        }
    }

    @Override
    @Transactional
    public List<Lai> findByUserId(String userId) {
        return laiDAO.findByUserId(userId);
    }
}
