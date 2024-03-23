package com.github.youssfbr.awpag.domain.services.impl;

import com.github.youssfbr.awpag.api.dtos.ClienteInsertDTO;
import com.github.youssfbr.awpag.api.dtos.ClienteResponseDTO;
import com.github.youssfbr.awpag.api.dtos.ClienteUpdateDTO;
import com.github.youssfbr.awpag.domain.models.Cliente;
import com.github.youssfbr.awpag.domain.repositories.IClienteRepository;
import com.github.youssfbr.awpag.domain.services.IClienteService;
import com.github.youssfbr.awpag.domain.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService implements IClienteService {

    private final IClienteRepository clienteRepository;
    public static final String CLIENTE_NAO_EXISTE_COM_ID = "Cliente não existe com Id ";

    @Override
    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> listar() {
        return clienteRepository.findAll()
                .stream()
                .map(ClienteResponseDTO::new)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteResponseDTO buscar(Long clienteId) {
        return new ClienteResponseDTO(getClienteById(clienteId));
    }

    @Override
    @Transactional
    public ClienteResponseDTO inserir(ClienteInsertDTO dto) {

        final Cliente clienteASerSalvo = new Cliente();

        copyDtoToEntity(dto , clienteASerSalvo);

        final Cliente clienteSalvo = clienteRepository.save(clienteASerSalvo);

        return new ClienteResponseDTO(clienteSalvo);
    }

    @Override
    public ClienteResponseDTO atualizar(Long clienteId , ClienteUpdateDTO dto) {

        final Cliente clienteASerAtualizado = getClienteById(clienteId);

        copyDtoToEntity(dto , clienteASerAtualizado);
        clienteASerAtualizado.setId(clienteId);

        final Cliente clienteAtualizado = clienteRepository.save(clienteASerAtualizado);
        return new ClienteResponseDTO(clienteAtualizado);
    }

    private Cliente getClienteById(Long clienteId) {
        return clienteRepository
                .findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException(CLIENTE_NAO_EXISTE_COM_ID + clienteId));
    }

    private void copyDtoToEntity(Object dto , Cliente entity) {
        BeanUtils.copyProperties(dto , entity);
    }
}
