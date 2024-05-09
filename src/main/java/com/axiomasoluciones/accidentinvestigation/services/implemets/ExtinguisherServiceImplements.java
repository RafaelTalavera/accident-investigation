package com.axiomasoluciones.accidentinvestigation.services.implemets;

import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.dao.IExtinguisherDAO;
import com.axiomasoluciones.accidentinvestigation.models.entity.Extinguisher;
import com.axiomasoluciones.accidentinvestigation.services.IExtinguisherService;
import com.axiomasoluciones.accidentinvestigation.utils.NormalizeText;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExtinguisherServiceImplements implements IExtinguisherService {

    @Autowired
    IExtinguisherDAO extinguisherDao;

    @Autowired
    private NormalizeText normalizeText;


    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    @Override
    @Transactional(readOnly = true)
    public List<Extinguisher> findAll() {
        return (List<Extinguisher>) extinguisherDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Extinguisher> findById(String id) {
        return extinguisherDao.findById(id);
    }

    @Override
    public Extinguisher save(Extinguisher extinguisher) {
        return extinguisherDao.save(extinguisher);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        Extinguisher existingExtinguisher = extinguisherDao.findById(id)
                .orElseThrow(() -> new RegistroNoEncontradoException
                        ("No se encontró ningún registro con el ID: " + id));
        extinguisherDao.deleteById(id);

    }

    @Override
    @Transactional
    public Extinguisher editExtinguisher(String id, Extinguisher extinguisher) {

        Extinguisher existExtinguisher = extinguisherDao.findById(id).orElseThrow(() ->
                new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id));

        return extinguisherDao.save(existExtinguisher);

    }

    @Override
    @Transactional
    public Extinguisher chageEnabled(String id, Extinguisher extinguisher) {
        Extinguisher existExtinguisher = extinguisherDao.findById(id).orElseThrow(() ->
                new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id));

        return extinguisherDao.save(existExtinguisher);
    }

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
    public List<Extinguisher> findByUserId(String userId) {
        return extinguisherDao.findExtinguisherByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Extinguisher> findExtinguisherByNameOrganization(String nameOrganization) {
        return extinguisherDao.findExtinguisherByNameOrganization(nameOrganization);
    }

    @Override
    @Transactional(readOnly = true)
    public int countByNameOrganizationAndSector(String nameOrganization, String sector) {

        List<Extinguisher> extinguishers = extinguisherDao.findExtinguisherByNameOrganizationAndSector(nameOrganization, sector);
        return extinguishers.size();
    }

    @Override
    @Transactional(readOnly = true)
    public int countVigentesByNameOrganizationAndSector(String nameOrganization, String sector) {

        List<Extinguisher> extinguishers = extinguisherDao.findExtinguisherByNameOrganizationAndSector(nameOrganization, sector);
        int count = 0;
        for (Extinguisher extinguisher : extinguishers) {
            if (extinguisher.estaVigente()) {
                count++;
            }
        }
        return count;
    }

    @Override
    @Transactional(readOnly = true)
    public int countVencidosByNameOrganizationAndSector(String nameOrganization, String sector) {
        List<Extinguisher> extinguishers = extinguisherDao.findExtinguisherByNameOrganizationAndSector(nameOrganization, sector);
        int count = 0;
        for (Extinguisher extinguisher : extinguishers) {
            if (!extinguisher.estaVigente()) {
                count++;
            }
        }
        return count;
    }

    @Override
    @Transactional(readOnly = true)
    public int countByTipoAndEnabled(String tipo, boolean enabled) {
        List<Extinguisher> extinguishers = extinguisherDao.findExtinguisherByTipoAndEnabled(tipo, enabled);
        return extinguishers.size();
    }

    @Override
    @Transactional(readOnly = true)
    public int countEnabledByNameOrganizationAndSector(String nameOrganization, String sector) {
        List<Extinguisher> extinguishers = extinguisherDao.findExtinguisherByNameOrganizationAndSector(nameOrganization, sector);
        int countEnabled = 0;
        for (Extinguisher extinguisher : extinguishers) {
            if (extinguisher.isEnabled()) {
                countEnabled++;
            }
        }
        return countEnabled;
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> findCompaniesByUserId(String userId) {
        List<Extinguisher> extinguishers = extinguisherDao.findCompaniesByUserId(userId);
        return extinguishers.stream()
                .map(Extinguisher::getNameOrganization) // Obtener solo el nombre de la empresa
                .distinct() // Eliminar duplicados
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Extinguisher> findExtinguisherByUserIdAndNameOrganization(String userId, String nameOrganization) {
        return extinguisherDao.findExtinguisherByUserIdAndNameOrganization(userId, nameOrganization);
    }

}