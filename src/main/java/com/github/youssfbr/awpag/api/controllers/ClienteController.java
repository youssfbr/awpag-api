package com.github.youssfbr.awpag.api.controllers;

import com.github.youssfbr.awpag.api.dtos.ClienteRequestDTO;
import com.github.youssfbr.awpag.api.dtos.ClienteResponseDTO;
import com.github.youssfbr.awpag.domain.services.IClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {

    private final IClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listar() {
        return ResponseEntity.ok(clienteService.listar());
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteResponseDTO> buscar(@PathVariable Long clienteId) {
        return ResponseEntity.ok(clienteService.buscar(clienteId));
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> buscar(@Valid @RequestBody ClienteRequestDTO dto) {

        final ClienteResponseDTO clienteInseridoDTO = clienteService.inserir(dto);

        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(clienteInseridoDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(clienteInseridoDTO);
    }

}
