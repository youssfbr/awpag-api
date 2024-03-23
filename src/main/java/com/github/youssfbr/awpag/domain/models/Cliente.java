package com.github.youssfbr.awpag.domain.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_clientes")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60 , nullable = false)
    private String nome;

    @Column(unique = true , nullable = false)
    private String email;

    @Column(length = 20 , name = "fone" , nullable = false)
    private String telefone;
}
