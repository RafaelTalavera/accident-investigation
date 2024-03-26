package com.axiomasoluciones.accidentinvestigation.services;

import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.dao.IRiskDao;
import com.axiomasoluciones.accidentinvestigation.models.entity.Risk;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RiskServiceImplements implements IRiskService {

    @Autowired
    private IRiskDao riskDao;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    @Override
    @Transactional(readOnly = true)
    public List<Risk> findAll() {
        return (List<Risk>) riskDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Risk> findById(String id) {
        return riskDao.findById(id);
    }

    @Override
    @Transactional
    public Risk save(Risk risk) {
        int gravedad = risk.getGravedad();
        int probabilidad = risk.getProbabilidad();

        int resultado = gravedad * probabilidad;

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

        // Guardar el objeto Risk en la base de datos
        return riskDao.save(risk);
    }


    @Override
    @Transactional
    public void deleteById(String id) {

        Risk existingRisk = riskDao.findById(id)
                .orElseThrow(() -> new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id));
        riskDao.deleteById(id);

    }

    @Override
    @Transactional
    public Risk editRisk(String id, Risk editedRisk) {
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
    public List<Risk> findByUserId(String userId) {
        return riskDao.findByUserId(userId);
    }

    @Override
    public Map<String, Integer> countClasificaMCByAreaAndPuesto(String area, String puesto) {
        Map<String, Integer> countMap = new HashMap<>();
        List<Risk> risks = riskDao.findRiskByAreaAndPuesto(area, puesto);

        for (Risk risk : risks) {
            String clasificaMC = risk.getClasificaMC();
            countMap.put(clasificaMC, countMap.getOrDefault(clasificaMC, 0) + 1);
        }

        return countMap;
    }

    @Override
    public Map<String, Map<String, Integer>> countEvaluacionByAreaAndPuesto(String area, String puesto) {
        Map<String, Map<String, Integer>> countMap = new HashMap<>();
        List<Risk> risks = riskDao.findRiskByAreaAndPuesto(area, puesto);

        for (Risk risk : risks) {
            String areaRisk = risk.getArea(); // Obtener el área de la instancia de Risk
            String puestoRisk = risk.getPuesto(); // Obtener el puesto de la instancia de Risk
            String evaluacion = risk.getEvaluacion();
            String key = areaRisk + " - " + puestoRisk; // Crear una clave única para cada área y puesto

            // Verificar si el área ya está en el map exterior
            if (!countMap.containsKey(key)) {
                countMap.put(key, new HashMap<>());
            }

            Map<String, Integer> innerMap = countMap.get(key);

            // Contar el número de veces que aparece cada evaluación en el área y puesto específicos
            innerMap.put(evaluacion, innerMap.getOrDefault(evaluacion, 0) + 1);

            // Incrementar el contador total
            innerMap.put("count", innerMap.getOrDefault("count", 0) + 1);
        }

        return countMap;
    }




}


