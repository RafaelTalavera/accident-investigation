package com.axiomasoluciones.accidentinvestigation.services.implemets;

import com.axiomasoluciones.accidentinvestigation.models.dao.IOrganizationDAO;
import com.axiomasoluciones.accidentinvestigation.models.entity.Organization;
import com.axiomasoluciones.accidentinvestigation.services.IOrganizationService;
import com.axiomasoluciones.accidentinvestigation.utils.NormalizeText;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationServiceImplemets implements IOrganizationService {

    @Autowired
    IOrganizationDAO organizationDAO;

    @Autowired
    private NormalizeText normalizeText;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;


    @Override
    @Transactional(readOnly = true)
    public List<Organization> findAll() {
        return (List<Organization>) organizationDAO.findAll();
    }

    @Override
    public Optional<Organization> findById(String id) {
        return organizationDAO.findById(id);
    }

    @Override
    public Organization save(Organization organization) {
        String nameNormalized = normalizeText.normalize(organization.getName());
        organization.setName(nameNormalized);

        try {
            return organizationDAO.save(organization);
        } catch (com.mongodb.DuplicateKeyException e) {
            throw new DuplicateKeyException("Ya existe una organización con el mismo nombre", e);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la organización", e);
        }
    }


    @Override
    public void deleteby(String id) {
        organizationDAO.deleteById(id);
    }

    @Override
    public Organization editOrganization(String id, Organization editeOrg) {
        return null;
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
    public List<Organization> findByUserId(String userId) {
        return organizationDAO.findByUserId(userId);
    }

    @Override
    public List<Organization> findDistinctOrganization() {
        return organizationDAO.findDistinctOrganization();
    }
}
