package com.apicultura.model;

import com.apicultura.model.enums.EstadoColmena;
import com.apicultura.model.enums.TipoAlza;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Colmena {

	@Id
	private Integer numero;

	@Enumerated(EnumType.STRING)
	private TipoAlza tipoAlza;

	private int cuadrosCria;
	private int reservasMiel;

	@Enumerated(EnumType.STRING)
	private EstadoColmena estado;

	private boolean tieneReina;
	private int anioReina;
	private int nivelVarroa;
	private String marcaTratamiento;
	private LocalDate fechaTratamiento;
	private double kgMiel;

	@OneToMany(mappedBy = "colmena", cascade = CascadeType.ALL, 
	           orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Revision> revisiones = new ArrayList<>();

	public Colmena(Integer numero) {
		this.numero = numero;
	}

	public boolean necesitaTratamiento() {
		return nivelVarroa >= 3;
	}

	public void agregarRevision(Revision revision) {
		revision.setColmena(this);
		revisiones.add(revision);
	}
}
