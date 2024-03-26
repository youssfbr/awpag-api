package com.github.youssfbr.awpag.domain.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_parcelamentos")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Parcelamento {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @Column(length = 60 , nullable = false)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @Column(nullable = false)
    private Integer quantidadeParcelas;

    @Column(nullable = false)
    private OffsetDateTime dataCriacao;

//    @Column(nullable = false)
//    private LocalDateTime dataCriacao;
}
