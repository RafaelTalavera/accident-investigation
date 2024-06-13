package com.axiomasoluciones.accidentinvestigation.services.implemets;

import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.dao.IWorkerDAO;
import com.axiomasoluciones.accidentinvestigation.models.entity.Risk;
import com.axiomasoluciones.accidentinvestigation.models.entity.Worker;
import com.axiomasoluciones.accidentinvestigation.services.IWorkerService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerServiceImplements implements IWorkerService {

    @Autowired
    private IWorkerDAO workerDAO;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    @Override
    public List<Worker> findAll() {
        return (List<Worker>) workerDAO.findAll();
    }

    @Override
    public Optional<Worker> findById(String id) {
        return workerDAO.findById(id);
    }

    @Override
    public Worker save(Worker worker) {
        return workerDAO.save(worker);
    }

    @Override
    public void deleteById(String id) {
        workerDAO.deleteById(id);
    }

    @Override
    public Worker editWorker(String id, Worker editedWorker) {
        Worker existWork = workerDAO.findById(id)
                .orElseThrow(() ->new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id));
        return workerDAO.save(existWork);
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
    public List<Worker> findByUserId(String userId) {
        return workerDAO.findByUserId(userId);
    }

    @Override
    public List<Worker> findDistinctOrganizationByUserId(String userId) {
        return workerDAO.findDistinctOrganizationByUserId(userId);
    }

    @Override
    public List<Worker  > findWorkByUserIdAndNameOrganization(String userId, String nameOrganization) {
        return workerDAO.findWorkByUserIdAndNameOrganization(userId,nameOrganization);
    }

    @Override
    public Optional<Worker> findByFingerPrint(byte[] fingerPrint) {
        return workerDAO.findByFingerPrint(fingerPrint);
    }

}
