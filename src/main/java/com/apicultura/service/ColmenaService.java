package com.apicultura.service;

import com.apicultura.exception.ColmenaNotFoundException;
import com.apicultura.exception.ColmenaYaExisteException;
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
	        throw new ColmenaYaExisteException(numero);
	    }
	    return colmenaRepo.save(new Colmena(numero));
	}
	
	public Colmena buscarPorNumero(Integer numero) {
	    return colmenaRepo.findById(numero)
	            .orElseThrow(() -> new ColmenaNotFoundException(numero));
	}

	@Transactional
	public Colmena registrarRevision(Integer numero, Revision revision) {
	    Colmena colmena = buscarPorNumero(numero);

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