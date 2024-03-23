package com.github.youssfbr.awpag.api.controllers;

import com.github.youssfbr.awpag.api.dtos.ClienteInsertDTO;
import com.github.youssfbr.awpag.api.dtos.ClienteResponseDTO;
import com.github.youssfbr.awpag.api.dtos.ClienteUpdateDTO;
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
    public ResponseEntity<ClienteResponseDTO> buscar(@Valid @RequestBody ClienteInsertDTO dto) {

        final ClienteResponseDTO clienteInseridoDTO = clienteService.inserir(dto);

        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(clienteInseridoDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(clienteInseridoDTO);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<ClienteResponseDTO> update(@PathVariable Long clienteId , @Valid @RequestBody ClienteUpdateDTO dto) {
        return ResponseEntity.ok(clienteService.atualizar(clienteId , dto));
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> deletar(@PathVariable Long clienteId) {
        clienteService.deletar(clienteId);
        return ResponseEntity.noContent().build();
    }

}
