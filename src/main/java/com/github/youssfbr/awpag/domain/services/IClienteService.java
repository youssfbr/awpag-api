package com.github.youssfbr.awpag.domain.services;

import com.github.youssfbr.awpag.api.dtos.ClienteResponseDTO;

import java.util.List;

public interface IClienteService {
    List<ClienteResponseDTO> listar();
    ClienteResponseDTO buscar(Long clienteId);
}
