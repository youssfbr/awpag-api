package com.github.youssfbr.awpag.api.controllers;

import com.github.youssfbr.awpag.api.dtos.ClienteResponseDTO;
import com.github.youssfbr.awpag.domain.services.IClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
