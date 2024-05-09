package com.axiomasoluciones.accidentinvestigation.services.implemets;

import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.dao.IRiskDAO;
import com.axiomasoluciones.accidentinvestigation.models.entity.Risk;
import com.axiomasoluciones.accidentinvestigation.services.IRiskService;
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
    private IRiskDAO riskDao;

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

        if (resultado >= 1 && resultado <= 4 && resultado >= 1 && resultado <= 4) {
            risk.setEvaluacion("Aceptable");
        } else if (resultado >= 5 && resultado <= 9 && resultado >= 5 && resultado <= 9) {
            risk.setEvaluacion("Adecuado");
        } else if (resultado >= 10 && resultado <= 16 && resultado >= 10 && resultado <= 16) {
            risk.setEvaluacion("Tolerable");
        } else if (resultado >= 17 && resultado <= 25 && resultado >= 17 && resultado <= 25) {
            risk.setEvaluacion("Inaceptable");
        }
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
        Risk existRisk = riskDao.findById(id)
                .orElseThrow(() -> new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id));
        existRisk.setPuesto(editedRisk.getPuesto());
        existRisk.setNameOrganization(editedRisk.getNameOrganization());
        existRisk.setArea(editedRisk.getArea());
        existRisk.setTarea(editedRisk.getTarea());
        existRisk.setIncidentesPotenciales(editedRisk.getIncidentesPotenciales());
        existRisk.setConsecuencia(editedRisk.getConsecuencia());
        existRisk.setTipo(editedRisk.getTipo());
        existRisk.setProbabilidad(editedRisk.getProbabilidad());
        existRisk.setGravedad(editedRisk.getGravedad());
        existRisk.setEvaluacion(editedRisk.getEvaluacion());
        existRisk.setClasificaMC(editedRisk.getClasificaMC());
        existRisk.setMedidaControl(editedRisk.getMedidaControl());
        existRisk.setDate(editedRisk.getDate());
        existRisk.setDateOfRevision(editedRisk.getDateOfRevision());
        existRisk.setUserId(editedRisk.getUserId());

        return riskDao.save(existRisk);
    }

    @Override
    @Transactional
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
    public List<Risk> findByUserId(String userId) {
        return riskDao.findByUserId(userId);
    }

    @Override
    public List<Risk> findDistinctOrganizationByUserId(String userId) {
        return riskDao.findDistinctOrganizationByUserId(userId);
    }

    @Override
    public List<Risk> findDistinctAreaByNameOrganization(String nameOrganization) {
        return riskDao.findDistinctAreaByNameOrganization(nameOrganization);
    }

    @Override
    public List<Risk> findDistinctPuestoByNameOrganizationAndArea(String nameOrganization, String area) {
        return riskDao.findDistinctPuestoByNameOrganizationAndArea(nameOrganization, area);
    }


    @Override
    public Map<String, Integer> countClasificaMCByNameOrganizationAndAreaAndPuesto(String nameOrganization,String area, String puesto) {
        Map<String, Integer> countMap = new HashMap<>();
        List<Risk> risks = riskDao.findRiskByNameOrganizationAndAreaAndPuesto(nameOrganization,area,puesto);

        for (Risk risk : risks) {
            String clasificaMC = risk.getClasificaMC();
            countMap.put(clasificaMC, countMap.getOrDefault(clasificaMC, 0) + 1);
        }

        return countMap;
    }

    @Override
    public Map<String, Map<String, Integer>> countEvaluacionByNameOrganizationAndAreaAndPuesto(String nameOrganization, String area, String puesto) {
        Map<String, Map<String, Integer>> countMap = new HashMap<>();
        List<Risk> risks = riskDao.findRiskByNameOrganizationAndAreaAndPuesto(nameOrganization, area, puesto);

        for (Risk risk : risks) {
            String organizationRisk = risk.getNameOrganization();
            String areaRisk = risk.getArea();
            String puestoRisk = risk.getPuesto();
            String evaluacion = risk.getEvaluacion();
            String key = organizationRisk + " - " + areaRisk + " - " + puestoRisk;

            if (!countMap.containsKey(key)) {
                countMap.put(key, new HashMap<>());
            }

            Map<String, Integer> innerMap = countMap.get(key);
            innerMap.put(evaluacion, innerMap.getOrDefault(evaluacion, 0) + 1);
            innerMap.put("count", innerMap.getOrDefault("count", 0) + 1);
        }
        return countMap;
    }

    @Override
    @Transactional(readOnly = true)
   public List<Risk> findRiskByUserIdAndNameOrganization(String userId, String nameOrganization) {
        return riskDao.findRiskByUserIdAndNameOrganization(userId, nameOrganization);
    }
}


