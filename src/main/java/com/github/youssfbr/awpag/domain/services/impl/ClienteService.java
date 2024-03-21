package com.github.youssfbr.awpag.domain.services.impl;

import com.github.youssfbr.awpag.domain.models.Cliente;
import com.github.youssfbr.awpag.domain.repositories.IClienteRepository;
import com.github.youssfbr.awpag.domain.services.IClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService implements IClienteService {

    private final IClienteRepository clienteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }
}
