package com.github.youssfbr.awpag.api.controllers;

import com.github.youssfbr.awpag.api.dtos.ParcelamentoRequestCreateDTO;
import com.github.youssfbr.awpag.api.dtos.ParcelamentoResponseDTO;
import com.github.youssfbr.awpag.domain.services.IParcelamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parcelamentos")
public class ParcelamentoController {

    private final IParcelamentoService parcelamentoService;

    @GetMapping
    public ResponseEntity<List<ParcelamentoResponseDTO>> listar() {
        return ResponseEntity.ok(parcelamentoService.listar());
    }

    @GetMapping("/{parcelamentoId}")
    public ResponseEntity<ParcelamentoResponseDTO> buscar(@PathVariable Long parcelamentoId) {
        return ResponseEntity.ok(parcelamentoService.buscar(parcelamentoId));
    }

    @PostMapping
    public ResponseEntity<ParcelamentoResponseDTO> criar(@Valid @RequestBody ParcelamentoRequestCreateDTO dto) {

        final ParcelamentoResponseDTO parcelamentoInseridoDTO = parcelamentoService.inserir(dto);

        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(parcelamentoInseridoDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(parcelamentoInseridoDTO);
    }

}
