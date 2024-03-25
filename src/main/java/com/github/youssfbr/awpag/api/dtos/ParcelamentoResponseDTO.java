package com.github.youssfbr.awpag.api.dtos;

import com.github.youssfbr.awpag.domain.models.Parcelamento;
import lombok.Getter;

@Getter
public class ParcelamentoResponseDTO extends ParcelamentoDTO {
    public ParcelamentoResponseDTO(Parcelamento entity) { super(entity); }
}
