package com.apicultura.dto;

import com.apicultura.model.enums.EstadoColmena;
import com.apicultura.model.enums.TipoAlza;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter @Setter
public class ColmenaResponse {

    private Integer numero;
    private EstadoColmena estado;
    private TipoAlza tipoAlza;
    private int cuadrosCria;
    private int reservasMiel;
    private boolean tieneReina;
    private int nivelVarroa;
    private boolean necesitaTratamiento;
    private int totalRevisiones;
    private LocalDate fechaUltimaRevision;
}