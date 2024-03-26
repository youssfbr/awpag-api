package com.github.youssfbr.awpag.domain.services.impl;

import com.github.youssfbr.awpag.api.dtos.ParcelamentoRequestCreateDTO;
import com.github.youssfbr.awpag.api.dtos.ParcelamentoResponseDTO;
import com.github.youssfbr.awpag.domain.models.Cliente;
import com.github.youssfbr.awpag.domain.models.Parcelamento;
import com.github.youssfbr.awpag.domain.repositories.IParcelamentoRepository;
import com.github.youssfbr.awpag.domain.services.IParcelamentoService;
import com.github.youssfbr.awpag.domain.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParcelamentoService implements IParcelamentoService {

    private final IParcelamentoRepository parcelamentoRepository;

    private final ClienteService clienteService;

    // private final IClienteRepository clienteRepository;

    // private static final String CLIENTE_NAO_EXISTE_COM_ID = "Cliente não existe com Id ";

    private static final String PARCELAMENTO_NAO_EXISTE_COM_ID = "Parcelamento não existe com Id ";
    private static final String PARCELAMENTO_INVALIDO_ID = "Parcelamento a ser criado não deve possuir um código";
    private static final String PARCELAMENTO_INVALIDO_DATA = "Parcelamento a ser criado não deve possuir data";

    @Override
    @Transactional(readOnly = true)
    public List<ParcelamentoResponseDTO> listar() {
        return parcelamentoRepository.findAll()
                .stream()
                .map(ParcelamentoResponseDTO::new)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ParcelamentoResponseDTO buscar(Long parcelamentoId) {
        final Parcelamento parcelamentoById = getParcelamentoById(parcelamentoId);
        return new ParcelamentoResponseDTO(parcelamentoById);
    }

    @Override
    @Transactional
    public ParcelamentoResponseDTO inserir(ParcelamentoRequestCreateDTO dto) {

        if (dto.getId() != null) throw new IllegalArgumentException(PARCELAMENTO_INVALIDO_ID);
        if (dto.getDataCriacao() != null) throw new IllegalArgumentException(PARCELAMENTO_INVALIDO_DATA);

        final Parcelamento parcelamentoASerSalvo = new Parcelamento();

        BeanUtils.copyProperties(dto , parcelamentoASerSalvo);

        final Long clientId = dto.getCliente().getId();
        final Cliente cliente = clienteService.buscarCliente(clientId);

        parcelamentoASerSalvo.setCliente(cliente);
        parcelamentoASerSalvo.setDataCriacao(OffsetDateTime.now());

        final Parcelamento parcelamentoSalvo = parcelamentoRepository.save(parcelamentoASerSalvo);

        return new ParcelamentoResponseDTO(parcelamentoSalvo);
    }

    private Parcelamento getParcelamentoById(Long parcelamentoId) {
        return parcelamentoRepository
                .findById(parcelamentoId)
                .orElseThrow(() -> new ResourceNotFoundException(PARCELAMENTO_NAO_EXISTE_COM_ID + parcelamentoId));
    }

//    private Cliente getCliente(Long clientId) {
//        return (clientId == null) ? null : clienteRepository.findById(clientId)
//                .orElseThrow(() -> new IllegalArgumentException(CLIENTE_NAO_EXISTE_COM_ID + clientId));
//    }

}
