package com.axiomasoluciones.accidentinvestigation.services;

import com.axiomasoluciones.accidentinvestigation.models.entity.Consumo;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IConsumoService {

    public List<Consumo> findAll();

    public Optional<Consumo> findById(String id);

    public Consumo save(Consumo inventarioGEI);

    public void deteleById(String id);

    public Consumo editConsumo(String id, Consumo editConsumo);

    String extractUserEmailFromToken(String token);

    List<Consumo> findByUserID(String userId);

    List<Consumo> findDistinctOrganizationByUserId(String userId);

    Map<String, Map<Integer, Map<String, Map<String, Double>>>> obtenerConsumoTotalPorCombustibleAñoMes();

    List<String> findDistinctCombustibleByNameOrganization(String nameOrganization);

    List<String> findDistinctUnidadByOrganizationAndCombustible(@Param("nameOrganization") String nameOrganization, @Param("combustible") String combustible);

    List<Consumo> findConsumoByOrganizationCombustibleAndUnidad(
            String nameOrganization,
            String combustible,
            String unidad
    );

    List<Consumo> findConsumoByUserIdAndNameOrganization(String userId, String nameOrganization);

}
