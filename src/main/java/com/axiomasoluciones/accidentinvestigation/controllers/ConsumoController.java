package com.axiomasoluciones.accidentinvestigation.controllers;

import com.axiomasoluciones.accidentinvestigation.dto.response.ConsumoDTO;
import com.axiomasoluciones.accidentinvestigation.dto.response.RiskResponseDTO;
import com.axiomasoluciones.accidentinvestigation.models.entity.Risk;
import com.axiomasoluciones.accidentinvestigation.services.implemets.ConsumoServiceImplements;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.axiomasoluciones.accidentinvestigation.dto.request.ConsumoRequestDTO;
import com.axiomasoluciones.accidentinvestigation.dto.response.ConsumoResponseDTO;
import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.entity.Consumo;
import com.axiomasoluciones.accidentinvestigation.services.IConsumoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/consumo")
public class ConsumoController {

    @Autowired
    private IConsumoService service;

    @Autowired
    private ConsumoServiceImplements consumoService;


    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> obtenerTodosLosConsumos() {
        List<Consumo> consumos = service.findAll();

        if (!consumos.isEmpty()) {
            List<Map<String, Object>> responseList = consumos.stream()
                    .map(consumo -> {
                        Map<String, Object> response = new HashMap<>();
                        response.put("id", consumo.getId());
                        response.put("date", consumo.getDate() != null ? consumo.getDate().toString() : null);
                        response.put("organizationId", consumo.getOrganizationId());
                        response.put("nameOrganization", consumo.getNameOrganization());
                        response.put("fuente", consumo.getFuente());
                        response.put("tipoFuente", consumo.getTipoFuente());
                        response.put("combustible", consumo.getCombustible());
                        response.put("unidad", consumo.getUnidad());
                        response.put("month", consumo.getMonth());
                        response.put("year", consumo.getYear());
                        response.put("consumo", consumo.getConsumo() != null ? consumo.getConsumo() : null);
                        response.put("userId", consumo.getUserId());
                        return response;
                    })
                    .collect(Collectors.toList());

            return new ResponseEntity<>(responseList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/organization/{name}")
    public ResponseEntity<List<Map<String, Object>>> getConsumosByUserAndOrganization(
            HttpServletRequest request,
            @PathVariable("name") String nameOrganization) {
        try {
            String token = request.getHeader("Authorization");
            String userId = service.extractUserEmailFromToken(token);

            List<Consumo> consumos = service.findConsumoByUserIdAndNameOrganization(userId, nameOrganization);
            if (!consumos.isEmpty()) {
                List<Map<String, Object>> responseList = consumos.stream()
                        .map(consumo -> {
                            Map<String, Object> response = new HashMap<>();
                            response.put("id", consumo.getId());
                            response.put("date", consumo.getDate() != null ? consumo.getDate().toString() : null);
                            response.put("organizationId", consumo.getOrganizationId());
                            response.put("nameOrganization", consumo.getNameOrganization());
                            response.put("fuente", consumo.getFuente());
                            response.put("tipoFuente", consumo.getTipoFuente());
                            response.put("combustible", consumo.getCombustible());
                            response.put("unidad", consumo.getUnidad());
                            response.put("month", consumo.getMonth());
                            response.put("year", consumo.getYear());
                            response.put("consumo", consumo.getConsumo() != null ? consumo.getConsumo() : null);
                            response.put("userId", consumo.getUserId());
                            return response;
                        })
                        .collect(Collectors.toList());

                return new ResponseEntity<>(responseList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/list")
    public ResponseEntity<List<Map<String, Object>>> obtenerTodosLosConsumos(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            String userEmail = service.extractUserEmailFromToken(token);
            List<Consumo> consumos = service.findByUserID(userEmail);

            if (!consumos.isEmpty()) {
                List<Map<String, Object>> responseList = consumos.stream()
                        .map(consumo -> {
                            Map<String, Object> response = new HashMap<>();
                            response.put("id", consumo.getId());
                            response.put("date", consumo.getDate() != null ? consumo.getDate().toString() : null);
                            response.put("organizationId", consumo.getOrganizationId());
                            response.put("nameOrganization", consumo.getNameOrganization());
                            response.put("fuente", consumo.getFuente());
                            response.put("tipoFuente", consumo.getTipoFuente());
                            response.put("combustible", consumo.getCombustible());
                            response.put("unidad", consumo.getUnidad());
                            response.put("mes", consumo.getMonth());
                            response.put("year", String.valueOf(consumo.getYear()));
                            response.put("consumo", consumo.getConsumo() != null ? consumo.getConsumo() : null);
                            response.put("userId", consumo.getUserId());
                            return response;
                        })
                        .collect(Collectors.toList());

                return new ResponseEntity<>(responseList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consumo> findById(@PathVariable String id) {
        Optional<Consumo> consumo = service.findById(id);
        return consumo.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ConsumoResponseDTO> save(@RequestBody ConsumoRequestDTO data,
                                                   HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String userEmail = service.extractUserEmailFromToken(token);

        Consumo newInventario = new Consumo(data);
        newInventario.setUserId(userEmail);
        service.save(newInventario);
        ConsumoResponseDTO consumoResponseDTO = new ConsumoResponseDTO(newInventario);
        return new ResponseEntity<>(consumoResponseDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        service.deteleById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<ConsumoResponseDTO> editConsumo
            (@PathVariable String id, @RequestBody ConsumoRequestDTO
                    requestDTO, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String userEmail = service.extractUserEmailFromToken(token);

        try {
            Consumo editConsumo = new Consumo();
            editConsumo.setUserId(userEmail);
            editConsumo.setDate(requestDTO.date());
            editConsumo.setUnidad(requestDTO.unidad());
            editConsumo.setCombustible(requestDTO.combustible());
            editConsumo.setConsumo(requestDTO.consumo());
            editConsumo.setFuente(requestDTO.fuente());
            editConsumo.setTipoFuente(requestDTO.tipoFuente());

            Consumo updateConsumo = service.editConsumo(id, editConsumo);

            ConsumoResponseDTO responseDTO = new ConsumoResponseDTO(updateConsumo);
            return ResponseEntity.ok(responseDTO);

        } catch (RegistroNoEncontradoException e) {
            return ResponseEntity.notFound().build();

        }

    }

    @GetMapping("/extractUserEmail")
    public ResponseEntity<String> extractUserEmailFromToken(@RequestParam String token) {
        String userEmail = service.extractUserEmailFromToken(token);
        return ResponseEntity.ok().body(userEmail);
    }

    @GetMapping("/findByUserID")
    public ResponseEntity<List<Consumo>> findByUserID(@RequestParam String userId) {
        List<Consumo> consumos = service.findByUserID(userId);
        return ResponseEntity.ok().body(consumos);
    }

    @GetMapping("/consumo-total")
    public ResponseEntity<Map<String, Map<Integer, Map<String, Map<String, Double>>>>> obtenerConsumoTotalPorCombustibleAñoMes(
            HttpServletRequest request
    ) {
        String token = request.getHeader("Authorization");
        String userEmail = service.extractUserEmailFromToken(token);

        try {
            Map<String, Map<Integer, Map<String, Map<String, Double>>>> consumoTotal = service.obtenerConsumoTotalPorCombustibleAñoMes();
            return new ResponseEntity<>(consumoTotal, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/organizations")
    public ResponseEntity<List<String>> getDistincOrganizations(HttpServletRequest request) {

        String token = request.getHeader("Authorization");
        String userId = service.extractUserEmailFromToken(token);

        try {
            List<Consumo> consumos = service.findDistinctOrganizationByUserId(userId);
            List<String> nameOrganizations = consumos.stream()
                    .map(Consumo::getNameOrganization)
                    .distinct()
                    .collect(Collectors.toList());

            return new ResponseEntity<>(nameOrganizations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/combustibles")
    public ResponseEntity<List<String>> getDistinctCombustiblesByOrganization(
            @RequestParam String nameOrganization,
            HttpServletRequest request) {

        String token = request.getHeader("Authorization");
        String userEmail = service.extractUserEmailFromToken(token);

        try {
            List<String> combustibles = service.findDistinctCombustibleByNameOrganization(nameOrganization);
            Set<String> uniqueCombustibles = new HashSet<>(); // Utilizamos un conjunto para almacenar valores únicos

            // Iterar sobre la lista de combustibles
            for (String combustibleJson : combustibles) {
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> jsonMap = objectMapper.readValue(combustibleJson, Map.class);
                String combustible = (String) jsonMap.get("combustible");
                uniqueCombustibles.add(combustible); // Agregar el combustible al conjunto para garantizar la unicidad
            }

            // Convertir el conjunto de valores únicos de combustible de nuevo a una lista
            List<String> uniqueCombustiblesList = new ArrayList<>(uniqueCombustibles);

            return new ResponseEntity<>(uniqueCombustiblesList, HttpStatus.OK);
        } catch (Exception e) {
            // Manejar la excepción y devolver un código de estado 500 en caso de error interno del servidor
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/unidad")
    public ResponseEntity<Set<String>> getDistinctUnidadesByOrganizationAndCombustible(
            @RequestParam String nameOrganization,
            @RequestParam String combustible,
            HttpServletRequest request) {

        String token = request.getHeader("Authorization");
        String userEmail = service.extractUserEmailFromToken(token);

        try {
            List<String> unidades = service.findDistinctUnidadByOrganizationAndCombustible(nameOrganization, combustible);
            Set<String> unidadesSinFormato = new HashSet<>();
            for (String unidadJson : unidades) {
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, String> jsonMap = objectMapper.readValue(unidadJson, Map.class);
                String unidad = jsonMap.get("unidad");
                unidadesSinFormato.add(unidad);
            }

            return ResponseEntity.ok().body(unidadesSinFormato);
        } catch (Exception e) {
            // Manejar la excepción y devolver un código de estado 500 en caso de error interno del servidor
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{nameOrganization}/{combustible}/{unidad}")
    public ResponseEntity<List<ConsumoDTO>> getTotalConsumo(
            @PathVariable String nameOrganization,
            @PathVariable String combustible,
            @PathVariable String unidad
    ) {
        List<ConsumoDTO> consumoDTOList = consumoService.getTotalConsumoByOrganizationCombustibleAndUnidad(nameOrganization, combustible, unidad);
        return ResponseEntity.ok().body(consumoDTOList);
    }


}


