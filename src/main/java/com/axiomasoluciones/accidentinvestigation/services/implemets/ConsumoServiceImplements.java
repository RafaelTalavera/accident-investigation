package com.axiomasoluciones.accidentinvestigation.services.implemets;

import com.axiomasoluciones.accidentinvestigation.dto.response.ConsumoDTO;
import com.axiomasoluciones.accidentinvestigation.exeption.RegistroNoEncontradoException;
import com.axiomasoluciones.accidentinvestigation.models.dao.IConsumoDAO;
import com.axiomasoluciones.accidentinvestigation.models.entity.Consumo;

import com.axiomasoluciones.accidentinvestigation.services.IConsumoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsumoServiceImplements implements IConsumoService {

    @Autowired
    IConsumoDAO consumoDAO;

    @Autowired
    ObjectMapper objectMapper;


    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;


    @Override
    @Transactional(readOnly = true)
    public List<Consumo> findAll() {
        return (List<Consumo>) consumoDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Consumo> findById(String id) {
        return consumoDAO.findById(id);
    }

    @Override
    @Transactional
    public Consumo save(Consumo consumo) {
        return consumoDAO.save(consumo);
    }

    @Override
    @Transactional
    public void deteleById(String id) {
        Consumo existigInventarioGEI = consumoDAO.findById(id).orElseThrow(
                () -> new RegistroNoEncontradoException
                        ("No se encontró ningún registro con el ID: " + id));
        consumoDAO.deleteById(id);

    }

    @Override
    @Transactional
    public Consumo editConsumo(String id, Consumo editConsumo) {
        Consumo existConsumo = consumoDAO.findById(id).orElseThrow(() ->
                new RegistroNoEncontradoException("No se encontró ningún registro con el ID: " + id));
        existConsumo.setUserId(editConsumo.getUserId());
        existConsumo.setDate(editConsumo.getDate());
        existConsumo.setUnidad(editConsumo.getUnidad());
        existConsumo.setCombustible(editConsumo.getCombustible());
        existConsumo.setConsumo(editConsumo.getConsumo());
        existConsumo.setFuente(editConsumo.getFuente());
        existConsumo.setTipoFuente(editConsumo.getTipoFuente());
        return consumoDAO.save(existConsumo);
    }

    @Override
    @Transactional(readOnly = true)
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
    public List<Consumo> findByUserID(String userId) {
        return consumoDAO.findByUserId(userId);
    }

    @Override
    public  List<Consumo> findDistinctOrganizationByUserId(String userId) {
        return consumoDAO.findDistinctOrganizationByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Map<Integer, Map<String, Map<String, Double>>>> obtenerConsumoTotalPorCombustibleAñoMes() {
        List<Consumo> consumos = consumoDAO.findAll();

        // Inicializar el mapa para almacenar los resultados
        Map<String, Map<Integer, Map<String, Map<String, Double>>>> consumoTotalPorOrganizacionAñoMes = new HashMap<>();

        // Agrupar los consumos por organización
        Map<String, List<Consumo>> consumosPorOrganizacion = consumos.stream()
                .collect(Collectors.groupingBy(Consumo::getNameOrganization));

        // Iterar sobre cada organización
        consumosPorOrganizacion.forEach((organizacion, listaConsumos) -> {
            // Inicializar el mapa para el año actual de la organización actual
            Map<Integer, Map<String, Map<String, Double>>> consumoTotalPorAño = new HashMap<>();

            // Agrupar los consumos por año
            Map<Integer, List<Consumo>> consumosPorAñoMap = listaConsumos.stream()
                    .collect(Collectors.groupingBy(Consumo::getYear));

            // Iterar sobre cada año
            consumosPorAñoMap.forEach((año, listaConsumosAño) -> {
                // Inicializar el mapa para el mes actual del año actual de la organización actual
                Map<String, Map<String, Double>> consumoTotalPorMes = new HashMap<>();

                // Agrupar los consumos por mes
                Map<String, List<Consumo>> consumosPorMesMap = listaConsumosAño.stream()
                        .collect(Collectors.groupingBy(Consumo::getMonth));

                // Iterar sobre cada mes
                consumosPorMesMap.forEach((mes, listaConsumosMes) -> {
                    // Inicializar el mapa para el combustible actual del mes actual del año actual de la organización actual
                    Map<String, Double> consumoTotalPorCombustibleUnidad = new HashMap<>();

                    // Sumar los consumos por combustible y unidad
                    listaConsumosMes.forEach(consumo -> {
                        String combustible = consumo.getCombustible();
                        String unidad = consumo.getUnidad();
                        Double consumoActual = consumo.getConsumo();

                        // Obtener o inicializar la suma de consumo para el par (combustible, unidad)
                        Double sumaConsumo = consumoTotalPorCombustibleUnidad.getOrDefault(combustible + "-" + unidad, 0.0);

                        // Actualizar la suma de consumo para el par (combustible, unidad)
                        consumoTotalPorCombustibleUnidad.put(combustible + "-" + unidad, sumaConsumo + consumoActual);
                    });

                    // Agregar el mapa de consumo total por (combustible, unidad) al mapa de consumo total por mes
                    consumoTotalPorMes.put(mes, consumoTotalPorCombustibleUnidad);
                });

                // Agregar el mapa de consumo total por mes al mapa de consumo total por año
                consumoTotalPorAño.put(año, consumoTotalPorMes);
            });

            // Agregar el mapa de consumo total por año al mapa de consumo total por organización
            consumoTotalPorOrganizacionAñoMes.put(organizacion, consumoTotalPorAño);
        });

        return consumoTotalPorOrganizacionAñoMes;
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> findDistinctCombustibleByNameOrganization(String nameOrganization) {
        return consumoDAO.findDistinctCombustibleByNameOrganization(nameOrganization);
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> findDistinctUnidadByOrganizationAndCombustible(String nameOrganization, String combustible) {
        return consumoDAO.findDistinctUnidadByOrganizationAndCombustible(nameOrganization, combustible);
    }

    @Override
    public List<Consumo> findConsumoByOrganizationCombustibleAndUnidad(String nameOrganization, String combustible, String unidad) {
        return consumoDAO.findConsumoByOrganizationCombustibleAndUnidad(nameOrganization, combustible, unidad);
    }

    @Override
    public List<Consumo> findConsumoByUserIdAndNameOrganization(String userId, String nameOrganization) {
        return consumoDAO.findConsumoByUserIdAndNameOrganization(userId, nameOrganization);
    }

    public List<ConsumoDTO> getTotalConsumoByOrganizationCombustibleAndUnidad(
            String nameOrganization,
            String combustible,
            String unidad
    ) {
        List<Consumo> consumos = consumoDAO.findConsumoByOrganizationCombustibleAndUnidad(nameOrganization, combustible, unidad);
        Map<YearMonth, Double> consumoPorMes = new HashMap<>();

        for (Consumo consumo : consumos) {
            YearMonth yearMonth = YearMonth.from(consumo.getDate());
            consumoPorMes.put(yearMonth, consumoPorMes.getOrDefault(yearMonth, 0.0) + consumo.getConsumo());
        }

        return consumoPorMes.entrySet().stream()
                .map(entry -> {
                    YearMonth yearMonth = entry.getKey();
                    LocalDateTime dateTime = yearMonth.atDay(1).atStartOfDay();
                    return new ConsumoDTO(
                            nameOrganization,
                            combustible,
                            unidad,
                            dateTime,
                            entry.getValue());
                })
                .collect(Collectors.toList());
    }

}



