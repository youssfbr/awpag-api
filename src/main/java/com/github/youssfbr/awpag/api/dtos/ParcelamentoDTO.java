package com.github.youssfbr.awpag.api.dtos;

import com.github.youssfbr.awpag.domain.models.Cliente;
import com.github.youssfbr.awpag.domain.models.Parcelamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Campo obrigatório")
    private Cliente cliente;

    @NotBlank(message = "Campo obrigatório")
    private String descricao;

    @NotNull(message = "Campo obrigatório")
    private BigDecimal valorTotal;

    @NotNull(message = "Campo obrigatório")
    private Integer quantidadeParcelas;

    private LocalDateTime dataCriacao;

    public ParcelamentoDTO(Long id , Cliente cliente , String descricao , BigDecimal valorTotal , Integer quantidadeParcelas , LocalDateTime dataCriacao) {
        this.id = id;
        this.cliente = cliente;
        this.descricao = descricao;
        this.valorTotal = valorTotal;
        this.quantidadeParcelas = quantidadeParcelas;
        this.dataCriacao = dataCriacao;
    }

    public ParcelamentoDTO(Parcelamento entity) {
        id = entity.getId();
        cliente = entity.getCliente();
        descricao = entity.getDescricao();
        valorTotal = entity.getValorTotal();
        quantidadeParcelas = entity.getQuantidadeParcelas();
        dataCriacao = entity.getDataCriacao();
    }
}
