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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            lai.setMeaningfulness("Aceptable");
        } else if (resultado >= 5 && resultado <= 9 && resultado >= 5 && resultado <= 9) {
            lai.setMeaningfulness("Adecuado");
        } else if (resultado >= 10 && resultado <= 16 && resultado >= 10 && resultado <= 16) {
            lai.setMeaningfulness("Tolerable");
        } else if (resultado >= 17 && resultado <= 25 && resultado >= 17 && resultado <= 25) {
            lai.setMeaningfulness("Inaceptable");
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
        Lai existLai = laiDAO.findById(id)
                .orElseThrow(() -> new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id));

        existLai.setActivity(editeLai.getActivity());
        existLai.setDescription(editeLai.getDescription());
        existLai.setFrequency(editeLai.getFrequency());
        existLai.setDamage(editeLai.getDamage());
        existLai.setStateHolder(editeLai.getStateHolder());
        existLai.setLegislation(editeLai.getLegislation());
        existLai.setDescriptionOfControl(editeLai.getDescriptionOfControl());
        existLai.setDateOfRevision(editeLai.getDateOfRevision());
        existLai.setUserId(editeLai.getUserId());

        return laiDAO.save(existLai);
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

    @Override
    public List<Lai> findDistinctOrganization() {
        return laiDAO.findDistinctOrganization();
    }

    @Override
    public List<Lai> findDistinctAreaByOrganization(String organization) {
        return laiDAO.findDistinctAreaByOrganization(organization);
    }

    @Override
    public Map<String, Integer> countTypeOfControlByOrganizationAndArea(String organization, String area) {

        Map<String, Integer> countMap = new HashMap<>();
        List<Lai> lais = laiDAO.findByOrganizationAndArea(organization, area);

        for (Lai lai : lais ){
            String typeOfControl = lai.getTypeOfControl();
            countMap.put(typeOfControl, countMap.getOrDefault(typeOfControl, 0)+1);
        }

        return countMap;
    }

    @Override
    public Map<String, Map<String, Integer>> countMeaningfulnessByOrganizationAndArea(String organization, String area) {
        Map<String, Map<String, Integer>> countMap = new HashMap<>();
        List<Lai> lais = laiDAO.findByOrganizationAndArea(organization, area);

        System.out.println("Documentos encontrados para la organización " + organization + " y área " + area + ":");

        for (Lai lai: lais){
            String organizacionLai = lai.getOrganization();
            String areaLai = lai.getArea();
            String meaningfulness = lai.getMeaningfulness();
            String key = organizacionLai + " - " + areaLai ;

            // Imprimir valores para depuración
            System.out.println("Organización: " + organizacionLai);
            System.out.println("Área: " + areaLai);
            System.out.println("Meaningfulness: " + meaningfulness);
            System.out.println("Key: " + key);

            if (!countMap.containsKey(key)){
                countMap.put(key, new HashMap<>());
            }

            Map<String, Integer> innerMap = countMap.get(key);
            innerMap.put(meaningfulness, innerMap.getOrDefault(meaningfulness, 0) + 1);
        }

        return countMap;
    }

}
