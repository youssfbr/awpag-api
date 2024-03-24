package com.github.youssfbr.awpag.domain.services;

import com.github.youssfbr.awpag.api.dtos.ParcelamentoRequestCreateDTO;
import com.github.youssfbr.awpag.api.dtos.ParcelamentoResponseDTO;

import java.util.List;

public interface IParcelamentoService {
    List<ParcelamentoResponseDTO> listar();
    ParcelamentoResponseDTO buscar(Long parcelamentoId);
    ParcelamentoResponseDTO inserir(ParcelamentoRequestCreateDTO dto);

}
