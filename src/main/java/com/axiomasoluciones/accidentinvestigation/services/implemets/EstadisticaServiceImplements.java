package com.axiomasoluciones.accidentinvestigation.services.implemets;

import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.dao.IAccidentsDAO;
import com.axiomasoluciones.accidentinvestigation.models.dao.IEstadisticaDAO;
import com.axiomasoluciones.accidentinvestigation.models.entity.AccidentMonthlySummary;
import com.axiomasoluciones.accidentinvestigation.models.entity.Accidents;
import com.axiomasoluciones.accidentinvestigation.models.entity.Estadistica;
import com.axiomasoluciones.accidentinvestigation.models.entity.EstadisticaMonthlySummary;
import com.axiomasoluciones.accidentinvestigation.services.IEstadisticaService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class EstadisticaServiceImplements implements IEstadisticaService {
    @Autowired
    IEstadisticaDAO statisticsDao;

    @Autowired
    IAccidentsDAO accidentsDAO;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    @Override
    @Transactional(readOnly = true)
    public List<Estadistica> findAll() {
        return statisticsDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Estadistica> findById(String id) {
        return statisticsDao.findById(id);
    }

    @Override
    @Transactional
    public Estadistica save(Estadistica statistics) {
        return statisticsDao.save(statistics);
    }

    @Override
    public String editStatistics(String id, Estadistica editedStatistics) {
        Estadistica existingStatistics = statisticsDao.findById(id)
                .orElseThrow(() -> new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id));

        existingStatistics.setHours(editedStatistics.getHours());
        existingStatistics.setPeople(editedStatistics.getPeople());

        statisticsDao.save(existingStatistics);
        return existingStatistics.getId();
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
    public List<Estadistica> findByUserId(String userId) {
        return statisticsDao.findByUserId(userId);
    }

    @Override
    public List<Estadistica> findDistinctOrganization() {
        return statisticsDao.findDistinctOrganization();
    }


    @Override
    public List<EstadisticaMonthlySummary> getMonthlyEstadisticaSummary(String nameOrganization) {
        return statisticsDao.getMonthlyEstadisticaSummary(nameOrganization);
    }


    private int getTotalHoursWorked(String organization, int year, int month) {
        // Obtener la estadística correspondiente a la organización, año y mes especificados
        Optional<Estadistica> estadisticaOptional = statisticsDao.findByOrganizationAndYearAndMonth(organization, year, month);

        // Verificar si se encontró la estadística
        if (estadisticaOptional.isPresent()) {
            Estadistica estadistica = estadisticaOptional.get();
            return estadistica.getHours(); // Devolver las horas trabajadas de la estadística
        } else {
            return 0; // Si no se encuentra la estadística, devolver 0 horas trabajadas
        }
    }


}
