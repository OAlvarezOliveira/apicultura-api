package com.apicultura.controller;

import com.apicultura.model.Colmena;
import com.apicultura.model.Revision;
import com.apicultura.service.ColmenaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/colmenas")
@RequiredArgsConstructor
public class ColmenaController {

	private final ColmenaService colmenaService;

	@GetMapping
	public List<Colmena> listar() {
		return colmenaService.listarTodas();
	}

	@PostMapping("/{numero}")
	public Colmena crear(@PathVariable Integer numero) {
		return colmenaService.crear(numero);
	}

	@PostMapping("/{numero}/revisiones")
	public Colmena registrarRevision(@PathVariable Integer numero, @RequestBody Revision revision) {
		return colmenaService.registrarRevision(numero, revision);
	}
}