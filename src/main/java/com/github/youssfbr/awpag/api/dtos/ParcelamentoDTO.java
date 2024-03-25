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
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ParcelamentoDTO {

    private Long id;

    @Valid
    @NotNull(message = "é obrigatório")
    @ConvertGroup(from = Default.class , to = ValidationGroups.ClienteDTOId.class)
    private ClienteDTO cliente;

    @NotBlank(message = "é obrigatório")
    @Size(max = 60 , message = "tem que ter no máximo 60 caracteres")
    private String descricao;

    @NotNull(message = "é obrigatório")
    @Positive(message = "deve ser positivo")
    private BigDecimal valorTotal;

    @NotNull(message = "é obrigatório")
    @Positive(message = "deve ser positivo")
    @Max(value = 12 , message = "deve ser até 12")
    private Integer quantidadeParcelas;

    private LocalDateTime dataCriacao;

    public ParcelamentoDTO(Long id , Cliente cliente , String descricao , BigDecimal valorTotal , Integer quantidadeParcelas , LocalDateTime dataCriacao) {
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
