package com.axiomasoluciones.accidentinvestigation.services.implemets;

import com.axiomasoluciones.accidentinvestigation.dto.request.AuthenticationRequestDTO;
import com.axiomasoluciones.accidentinvestigation.dto.response.AuthenticationResponseDTO;
import com.axiomasoluciones.accidentinvestigation.models.dao.IUserDAO;
import com.axiomasoluciones.accidentinvestigation.models.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationServiceImplemets {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserDAO userDao;

    @Autowired
    private JwtServiceImplements jwtService;

    public AuthenticationResponseDTO login(AuthenticationRequestDTO authenticationRequest) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken
                (authenticationRequest.username(), authenticationRequest.password());

        authenticationManager.authenticate(authenticationToken);

        User user = userDao.findByUsername(authenticationRequest.username()).get();

        String jwt = jwtService.generateToken(user, generateExtraClaims(user));

        return new AuthenticationResponseDTO(jwt);

    }
    private Map<String, Object> generateExtraClaims(User user){
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("mail", user.getEmail());
        extraClaims.put("role", user.getRole().name());
        extraClaims.put("permissions", user.getAuthorities());
        return extraClaims;
    }



}
