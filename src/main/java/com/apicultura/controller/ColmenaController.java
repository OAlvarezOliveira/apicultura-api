package com.apicultura.controller;

import com.apicultura.dto.ColmenaResponse;
import com.apicultura.model.Revision;
import com.apicultura.service.ColmenaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/colmenas")
@RequiredArgsConstructor
public class ColmenaController {

	private final ColmenaService colmenaService;

	@GetMapping
	public List<ColmenaResponse> listar() {
		return colmenaService.listarTodas();
	}

	@PostMapping("/{numero}")
	@ResponseStatus(HttpStatus.CREATED)
	public ColmenaResponse crear(@PathVariable Integer numero) {
	    return colmenaService.crear(numero);
	}

	@PostMapping("/{numero}/revisiones")
	public ColmenaResponse registrarRevision(@PathVariable Integer numero, @RequestBody Revision revision) {
		return colmenaService.registrarRevision(numero, revision);
	}
}