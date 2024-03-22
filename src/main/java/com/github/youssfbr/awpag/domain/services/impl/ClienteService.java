package com.github.youssfbr.awpag.domain.services.impl;

import com.github.youssfbr.awpag.api.dtos.ClienteRequestDTO;
import com.github.youssfbr.awpag.api.dtos.ClienteResponseDTO;
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
         return getClienteById(clienteId);
    }

    @Override
    @Transactional
    public ClienteResponseDTO inserir(ClienteRequestDTO dto) {

        final Cliente cliente = new Cliente();

        copyDtoToEntity(dto , cliente);

        final Cliente clienteSalvo = clienteRepository.save(cliente);

        return new ClienteResponseDTO(clienteSalvo);
    }

    private ClienteResponseDTO getClienteById(Long clienteId) {
        return clienteRepository
                .findById(clienteId)
                .map(ClienteResponseDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException(CLIENTE_NAO_EXISTE_COM_ID + clienteId));
    }

    private void copyDtoToEntity(ClienteRequestDTO dto , Cliente entity) {
        BeanUtils.copyProperties(dto , entity);
    }
}
