package com.apicultura.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.apicultura.model.Colmena;
import com.apicultura.model.Revision;
import com.apicultura.model.enums.EstadoColmena;
import com.apicultura.dto.ColmenaResponse;
import com.apicultura.dto.ColmenaMapper;
import com.apicultura.exception.ColmenaNotFoundException;
import com.apicultura.exception.ColmenaYaExisteException;
import com.apicultura.repository.ColmenaRepository;

@Service
public class ColmenaService {

    private final ColmenaRepository colmenaRepo;
    private final ColmenaMapper colmenaMapper;

    public ColmenaService(ColmenaRepository colmenaRepo, ColmenaMapper colmenaMapper) {
        this.colmenaRepo = colmenaRepo;
        this.colmenaMapper = colmenaMapper;
    }

    public List<ColmenaResponse> listarTodas() {
        return colmenaRepo.findAll().stream()
                .map(colmenaMapper::toResponse)
                .toList();
    }

    public ColmenaResponse buscarPorNumero(Integer numero) {
        Colmena colmena = colmenaRepo.findById(numero)
                .orElseThrow(() -> new ColmenaNotFoundException(numero));
        return colmenaMapper.toResponse(colmena);
    }

    public ColmenaResponse crear(Integer numero) {
        if (colmenaRepo.existsById(numero)) {
            throw new ColmenaYaExisteException(numero);
        }
        return colmenaMapper.toResponse(colmenaRepo.save(new Colmena(numero)));
    }

    public ColmenaResponse registrarRevision(Integer numero, Revision revision) {
        Colmena colmena = colmenaRepo.findById(numero)
                .orElseThrow(() -> new ColmenaNotFoundException(numero));

        EstadoColmena estado;
        if (!revision.isTieneReina())            estado = EstadoColmena.SIN_REINA;
        else if (revision.getCuadrosCria() >= 4) estado = EstadoColmena.BUENA;
        else                                     estado = EstadoColmena.DEBIL;

        revision.setEstado(estado);
        revision.setFecha(LocalDate.now());
        colmena.agregarRevision(revision);

        colmena.setCuadrosCria(revision.getCuadrosCria());
        colmena.setReservasMiel(revision.getReservasMiel());
        colmena.setEstado(estado);
        colmena.setTieneReina(revision.isTieneReina());
        colmena.setNivelVarroa(revision.getNivelVarroa());

        return colmenaMapper.toResponse(colmenaRepo.save(colmena));
    }
}