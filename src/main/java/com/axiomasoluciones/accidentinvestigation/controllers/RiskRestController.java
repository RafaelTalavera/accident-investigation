package com.axiomasoluciones.accidentinvestigation.controllers;


import com.axiomasoluciones.accidentinvestigation.dto.RiskRequestDTO;
import com.axiomasoluciones.accidentinvestigation.dto.RiskResponseDTO;
import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.dao.IRiskDao;
import com.axiomasoluciones.accidentinvestigation.models.entity.Risk;
import com.axiomasoluciones.accidentinvestigation.services.IRiskService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/risk")
public class RiskRestController {

    @Autowired
    private IRiskService riskService;


    @GetMapping
    public ResponseEntity<List<RiskResponseDTO>> getAll() {
        List<Risk> risks = riskService.findAll();
        if (!((List<?>) risks).isEmpty()) {
            List<RiskResponseDTO> riskReponseDTOS = risks.stream()
                    .map(RiskResponseDTO::new).collect(Collectors.toList());
            return new ResponseEntity<>(riskReponseDTOS, HttpStatus.OK);
        } else {
            throw new RegistroNoEncontradoException("No se encontró ningún registro en la base de datos.");
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<RiskResponseDTO>> getAll(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            String userEmail = riskService.extractUserEmailFromToken(token);
            List<Risk> risks = riskService.findByUserId(userEmail);
            if (!risks.isEmpty()) {
                List<RiskResponseDTO> riskResponseDTOS = risks.stream()
                        .map(RiskResponseDTO::new).collect(Collectors.toList());
                return new ResponseEntity<>(riskResponseDTOS, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("permitAll")
    @PostMapping
    public ResponseEntity<RiskResponseDTO> createRisk(
            @RequestBody RiskRequestDTO data , HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String userEmail = riskService.extractUserEmailFromToken(token);
        Risk newRisk = new Risk(data);
        newRisk.setUserId(userEmail);
        riskService.save(newRisk);
        RiskResponseDTO riskResponseDTO = new RiskResponseDTO(newRisk);
        return new ResponseEntity<>(riskResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<RiskResponseDTO> editRisk(@PathVariable String id,
                                                    @RequestBody RiskRequestDTO requestDTO,
                                                    HttpServletRequest request ){
        String token = request.getHeader("Authorization");
        String userEmail = riskService.extractUserEmailFromToken(token);

        try {
            Risk editRisk = new Risk();
            editRisk.setUserId(userEmail);
            editRisk.setPuesto(requestDTO.puesto());
            editRisk.setOrganization(requestDTO.organization());
            editRisk.setArea(requestDTO.area());
            editRisk.setTarea(requestDTO.tarea());
            editRisk.setFuente(requestDTO.fuente());
            editRisk.setIncidentesPotenciales(requestDTO.incidentesPotenciales());
            editRisk.setConsecuencia(requestDTO.consecuencia());
            editRisk.setTipo(requestDTO.tipo());
            editRisk.setProbabilidad(requestDTO.probabilidad());
            editRisk.setGravedad(requestDTO.gravedad());
            editRisk.setEvaluacion(requestDTO.evaluacion());
            editRisk.setClasificaMC(requestDTO.clasificaMC());
         //   editRisk.setNewControl(requestDTO.newControl());
            editRisk.setDate(requestDTO.date());
            editRisk.setDateOfRevision(requestDTO.dateOfRevision());

            Risk updateRisk = riskService.editRisk(id, editRisk);
            RiskResponseDTO responseDTO = new RiskResponseDTO(updateRisk);

            return ResponseEntity.ok(responseDTO);
        } catch (RegistroNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/countClasificaMC")
    public ResponseEntity<Map<String, Integer>> countClasificaMCByOrganizationAndAreaAndPuesto(
            @RequestParam String organization,
            @RequestParam String area,
            @RequestParam String puesto) {
        Map<String, Integer> countMap = riskService.countClasificaMCByOrganizationAndAreaAndPuesto(organization, area, puesto);
        return new ResponseEntity<>(countMap, HttpStatus.OK);
    }

    @GetMapping("/countEvaluacion")
    public ResponseEntity<Map<String, Map<String, Integer>>> countOrganizationAndEvaluacionMCByAreaAndPuesto(
            @RequestParam String organization,
            @RequestParam String area,
            @RequestParam String puesto) {
        Map<String, Map<String, Integer>> countMap = riskService.countEvaluacionByOrganizationAndAreaAndPuesto(organization,area, puesto);
        return new ResponseEntity<>(countMap, HttpStatus.OK);
    }

    @GetMapping("/organizations")
    public ResponseEntity<List<String>> getDistinctOrganizations() {
        List<String> organizations = riskService.findDistinctOrganization()
                .stream()
                .map(Risk::getOrganization)
                .distinct()
                .collect(Collectors.toList());
        return new ResponseEntity<>(organizations, HttpStatus.OK);
    }

    @GetMapping("/areas/{organization}")
    public ResponseEntity<List<String>> getDistinctAreasByOrganization(@PathVariable String organization) {
        List<Risk> risks = riskService.findDistinctAreaByOrganization(organization);
        List<String> distinctAreas = risks.stream()
                .map(Risk::getArea)
                .distinct()
                .collect(Collectors.toList());
        return new ResponseEntity<>(distinctAreas, HttpStatus.OK);
    }

    @GetMapping("/puestos/{organization}/{area}")
    public ResponseEntity<List<String>> getDistinctPuestosByOrganizationAndArea(
            @PathVariable String organization,
            @PathVariable String area) {
        List<Risk> risks = riskService.findDistinctPuestoByOrganizationAndArea(organization, area);
        List<String> distinctPuestos = risks.stream()
                .map(Risk::getPuesto)
                .distinct()
                .collect(Collectors.toList());
        return new ResponseEntity<>(distinctPuestos, HttpStatus.OK);
    }

}
