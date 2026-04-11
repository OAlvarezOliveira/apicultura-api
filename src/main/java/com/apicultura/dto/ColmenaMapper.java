package com.apicultura.dto;

import com.apicultura.model.Colmena;
import org.springframework.stereotype.Component;

@Component
public class ColmenaMapper {

    public ColmenaResponse toResponse(Colmena colmena) {
        ColmenaResponse dto = new ColmenaResponse();
        dto.setNumero(colmena.getNumero());
        dto.setEstado(colmena.getEstado());
        dto.setTipoAlza(colmena.getTipoAlza());
        dto.setCuadrosCria(colmena.getCuadrosCria());
        dto.setReservasMiel(colmena.getReservasMiel());
        dto.setTieneReina(colmena.isTieneReina());
        dto.setNivelVarroa(colmena.getNivelVarroa());
        dto.setNecesitaTratamiento(colmena.necesitaTratamiento());
        dto.setTotalRevisiones(colmena.getRevisiones().size());
        dto.setFechaUltimaRevision(
            colmena.getRevisiones().isEmpty() ? null
            : colmena.getRevisiones().get(0).getFecha()
        );
        return dto;
    }
}