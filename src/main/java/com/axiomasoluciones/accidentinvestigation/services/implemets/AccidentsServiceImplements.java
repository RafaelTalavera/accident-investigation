package com.axiomasoluciones.accidentinvestigation.services.implemets;

import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.dao.IAccidentsDAO;
import com.axiomasoluciones.accidentinvestigation.models.entity.Accidents;
import com.axiomasoluciones.accidentinvestigation.models.entity.AccidentMonthlySummary;
import com.axiomasoluciones.accidentinvestigation.services.IAccidentsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class AccidentsServiceImplements implements IAccidentsService {

    @Autowired
    IAccidentsDAO accidentsDAO;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    @Override
    @Transactional(readOnly = true)
    public List<Accidents> findAll() {
        return (List<Accidents>) accidentsDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Accidents> findById(String id) {
        return accidentsDAO.findById(id);
    }

    @Override
    @Transactional
    public Accidents save(Accidents accidents) {
        return accidentsDAO.save(accidents);
    }

    @Override
    @Transactional
    public void deteleById(String id) {

        Accidents existngAccidents = accidentsDAO.findById(id).orElseThrow(()
                -> new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id));
        accidentsDAO.deleteById(id);
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
    @Transactional(readOnly = true)
    public List<Accidents> findByUserId(String userId) {
        return accidentsDAO.findByUserId(userId);
    }


    @Override
    @Transactional(readOnly = true)
    public long calcularLostDay(Accidents accident) {
        if (accident.isAlta()) {
            return ChronoUnit.DAYS.between(accident.getDateEvent(), accident.getDateAlta());
        } else {
            return ChronoUnit.DAYS.between(accident.getDateEvent(), LocalDate.now());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Accidents> getAccidentsByOrganization(String organization) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccidentMonthlySummary> getMonthlyAccidentSummary(String nameOrganization) {
        return accidentsDAO.getMonthlyAccidentSummary(nameOrganization);
    }

    @Override
    @Transactional(readOnly = true)
    public Accidents editAccidents(String id, Accidents editedAccidents) {
        Accidents existingAccidents = accidentsDAO.findById(id).orElseThrow(()
                -> new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id));

        existingAccidents.setDateAlta(editedAccidents.getDateAlta());

        existingAccidents.setAlta(editedAccidents.getDateAlta() != null);

        return accidentsDAO.save(existingAccidents);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Accidents> findAccidentsByUserIdAndNameOrganization(String userId, String nameOrganization) {
        return accidentsDAO.findAccidentsByUserIdAndNameOrganization(userId, nameOrganization);
    }
}