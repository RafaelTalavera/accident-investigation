package com.axiomasoluciones.accidentinvestigation.config.security.filter;

import com.axiomasoluciones.accidentinvestigation.models.dao.IUserDao;
import com.axiomasoluciones.accidentinvestigation.models.entity.User;
import com.axiomasoluciones.accidentinvestigation.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserDao userDao;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //1. Obtener el header que contiene el jwt
        String authHeader = request.getHeader("Authorization"); // Bearer jwt

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        //2. Obtener jwt desde header
        String jwt = authHeader.split(" ")[1];

        System.out.println("JWT Token: " + jwt);


        //3. Obtener subject/username desde el jwt
        String username = jwtService.extractUsername(jwt);

        //4. Setear un objeto Authentication dentro del SecurityContext

        User user = userDao.findByUsername(username).get();
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                username, null, user.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authToken);

        //5. Ejecutar el resto de filtros

        filterChain.doFilter(request, response);
    }
}
