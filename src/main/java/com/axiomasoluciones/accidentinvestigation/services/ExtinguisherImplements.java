package com.axiomasoluciones.accidentinvestigation.services;

import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.dao.IExtinguisherDao;
import com.axiomasoluciones.accidentinvestigation.models.entity.Event;
import com.axiomasoluciones.accidentinvestigation.models.entity.Extinguisher;
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
public class ExtinguisherImplements implements IExtinguisherService {

    @Autowired
    IExtinguisherDao extinguisherDao;

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
        String empresaNormalized = normalizeText.normalize(extinguisher.getEmpresa());
        String sectorNormalized = normalizeText.normalize(extinguisher.getSector());
        extinguisher.setEmpresa(empresaNormalized);
        extinguisher.setSector(sectorNormalized);
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
    public List<Extinguisher> findExtinguisherByEmpresa(String empresa) {
        String empresaNormalized = normalizeText.normalize(empresa);
        return extinguisherDao.findExtinguisherByEmpresa(empresaNormalized);
    }

    @Override
    @Transactional(readOnly = true)
    public int countByEmpresaAndSector(String empresa, String sector) {
        String empresaNormalized = normalizeText.normalize(empresa);
        String sectorNormalized = normalizeText.normalize(sector);
        List<Extinguisher> extinguishers = extinguisherDao.findExtinguisherByEmpresaAndSector(empresaNormalized, sectorNormalized);
        return extinguishers.size();
    }

    @Override
    @Transactional(readOnly = true)
    public int countVigentesByEmpresaAndSector(String empresa, String sector) {
        String empresaNormalized = normalizeText.normalize(empresa);
        String sectorNormalized = normalizeText.normalize(sector);
        List<Extinguisher> extinguishers = extinguisherDao.findExtinguisherByEmpresaAndSector(empresaNormalized, sectorNormalized);
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
    public int countVencidosByEmpresaAndSector(String empresa, String sector) {
        String empresaNormalized = normalizeText.normalize(empresa);
        String sectorNormalized = normalizeText.normalize(sector);
        List<Extinguisher> extinguishers = extinguisherDao.findExtinguisherByEmpresaAndSector(empresaNormalized, sectorNormalized);
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
    public int countEnabledByEmpresaAndSector(String empresa, String sector) {
        List<Extinguisher> extinguishers = extinguisherDao.findExtinguisherByEmpresaAndSector(empresa, sector);
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
    public List<String> findAllCompanies() {
        List<Extinguisher> extinguishers = (List<Extinguisher>) extinguisherDao.findAll();
        return extinguishers.stream()
                .map(Extinguisher::getEmpresa) // Obtener solo el nombre de la empresa
                .distinct() // Eliminar duplicados
                .collect(Collectors.toList());
    }




}
