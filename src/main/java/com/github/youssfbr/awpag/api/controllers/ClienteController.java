package com.github.youssfbr.awpag.api.controllers;

import com.github.youssfbr.awpag.domain.model.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        final Cliente cliente1 = new Cliente(1L, "Alisson", "youssfbr@gmail.com", "99 9876461231");
        final Cliente cliente2 = new Cliente(2L, "Lilica", "lilica@gmail.com", "99 9876461232");

        final List<Cliente> list = Arrays.asList(cliente1 , cliente2);

        return ResponseEntity.ok(list);
    }

}
