package com.axiomasoluciones.accidentinvestigation.services.implemets;

import com.axiomasoluciones.accidentinvestigation.models.dao.IComentsDAO;
import com.axiomasoluciones.accidentinvestigation.models.entity.Coments;
import com.axiomasoluciones.accidentinvestigation.services.IComentsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentsImplementsService implements IComentsService {

    @Autowired
    IComentsDAO comentsDAO;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;


    @Override
    public Coments save(Coments coments) {

        return comentsDAO.save(coments);
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
    public List<Coments> findByUserId(String userId) {
        return comentsDAO.findByUserId(userId);
    }
}
