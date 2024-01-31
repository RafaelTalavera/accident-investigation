package com.axiomasoluciones.accidentinvestigation.services;

import com.axiomasoluciones.accidentinvestigation.dto.AuthenticationRequestDTO;
import com.axiomasoluciones.accidentinvestigation.dto.AuthenticationResponseDTO;
import com.axiomasoluciones.accidentinvestigation.models.dao.IUserDao;
import com.axiomasoluciones.accidentinvestigation.models.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private JwtService jwtService;

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
