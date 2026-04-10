package com.apicultura.model;

import com.apicultura.model.enums.EstadoColmena;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "revisiones")
@Getter
@Setter
@NoArgsConstructor
public class Revision {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "colmena_numero")
	private Colmena colmena;

	private LocalDate fecha;
	private int cuadrosCria;
	private int reservasMiel;
	private boolean tieneReina;
	private int nivelVarroa;
	private boolean particion;

	@Enumerated(EnumType.STRING)
	private EstadoColmena estado;

	private String observaciones;
}