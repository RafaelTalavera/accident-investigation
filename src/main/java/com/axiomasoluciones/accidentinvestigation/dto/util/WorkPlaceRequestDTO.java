package com.axiomasoluciones.accidentinvestigation.dto.util;

import java.time.LocalDate;
import java.util.List;

public record WorkPlaceRequestDTO(
        String name,
        String sector,
        String adverseWeatherDetalis,
        Boolean adverseWeather,
        String lightingDetails,
        Boolean lighting,
        LocalDate inspectionDate,
        Boolean inspection

) {
}
