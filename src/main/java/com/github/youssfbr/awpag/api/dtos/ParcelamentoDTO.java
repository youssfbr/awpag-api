package com.github.youssfbr.awpag.api.dtos;

import com.github.youssfbr.awpag.domain.models.Cliente;
import com.github.youssfbr.awpag.domain.models.Parcelamento;
import com.github.youssfbr.awpag.domain.services.validations.ValidationGroups;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ParcelamentoDTO {

    private Long id;

    @Valid
    @NotNull
    @ConvertGroup(from = Default.class , to = ValidationGroups.ClienteDTOId.class)
    private ClienteDTO cliente;

    @NotBlank
    @Size(max = 60)
    private String descricao;

    @NotNull
    @Positive
    private BigDecimal valorTotal;

    @NotNull
    @Positive
    @Max(12)
    private Integer quantidadeParcelas;

    private OffsetDateTime dataCriacao;
    //private LocalDateTime dataCriacao;

    public ParcelamentoDTO(Long id , Cliente cliente , String descricao , BigDecimal valorTotal , Integer quantidadeParcelas , OffsetDateTime dataCriacao) {
        this.id = id;
        this.cliente = new ClienteDTO(cliente);
        this.descricao = descricao;
        this.valorTotal = valorTotal;
        this.quantidadeParcelas = quantidadeParcelas;
        this.dataCriacao = dataCriacao;
    }

    public ParcelamentoDTO(Parcelamento entity) {
        id = entity.getId();
        cliente = new ClienteDTO(entity.getCliente());
        descricao = entity.getDescricao();
        valorTotal = entity.getValorTotal();
        quantidadeParcelas = entity.getQuantidadeParcelas();
        dataCriacao = entity.getDataCriacao();
    }
}
