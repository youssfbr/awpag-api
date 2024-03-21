package com.github.youssfbr.awpag.domain.services;

import com.github.youssfbr.awpag.domain.models.Cliente;

import java.util.List;

public interface IClienteService {
    List<Cliente> listar();
    Cliente buscar(Long clienteId);
}
