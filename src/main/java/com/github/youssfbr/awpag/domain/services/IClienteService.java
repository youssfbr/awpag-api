package com.github.youssfbr.awpag.domain.services;

import com.github.youssfbr.awpag.api.dtos.ClienteInsertDTO;
import com.github.youssfbr.awpag.api.dtos.ClienteResponseDTO;
import com.github.youssfbr.awpag.api.dtos.ClienteUpdateDTO;

import java.util.List;

public interface IClienteService {
    List<ClienteResponseDTO> listar();
    ClienteResponseDTO buscar(Long clienteId);
    ClienteResponseDTO inserir(ClienteInsertDTO dto);
    ClienteResponseDTO atualizar(Long clienteId , ClienteUpdateDTO dto);
    void deletar(Long clienteId);
}
