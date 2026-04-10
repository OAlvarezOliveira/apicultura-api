package com.apicultura.service;

import com.apicultura.model.Colmena;
import com.apicultura.model.Revision;
import com.apicultura.model.enums.EstadoColmena;
import com.apicultura.repository.ColmenaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ColmenaService {

	private final ColmenaRepository colmenaRepo;

	public List<Colmena> listarTodas() {
		return colmenaRepo.findAll();
	}

	public Colmena crear(Integer numero) {
		if (colmenaRepo.existsById(numero)) {
			throw new RuntimeException("La colmena #" + numero + " ya existe");
		}
		return colmenaRepo.save(new Colmena(numero));
	}

	@Transactional
	public Colmena registrarRevision(Integer numero, Revision revision) {
		Colmena colmena = colmenaRepo.findById(numero)
				.orElseThrow(() -> new RuntimeException("Colmena #" + numero + " no encontrada"));

		EstadoColmena estado;
		if (!revision.isTieneReina())
			estado = EstadoColmena.SIN_REINA;
		else if (revision.getCuadrosCria() >= 4)
			estado = EstadoColmena.BUENA;
		else
			estado = EstadoColmena.DEBIL;

		revision.setEstado(estado);
		revision.setFecha(LocalDate.now());
		colmena.agregarRevision(revision);

		colmena.setCuadrosCria(revision.getCuadrosCria());
		colmena.setReservasMiel(revision.getReservasMiel());
		colmena.setEstado(estado);
		colmena.setTieneReina(revision.isTieneReina());
		colmena.setNivelVarroa(revision.getNivelVarroa());

		return colmenaRepo.save(colmena);
	}
}