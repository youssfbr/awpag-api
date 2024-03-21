package com.github.youssfbr.awpag.api.dtos;

import com.github.youssfbr.awpag.domain.models.Cliente;
import lombok.Getter;

@Getter
public class ClienteResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String telefone;

    public ClienteResponseDTO() { }

    public ClienteResponseDTO(Long id , String nome , String email , String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public ClienteResponseDTO(Cliente entity) {
        id = entity.getId();
        nome = entity.getNome();
        email = entity.getEmail();
        telefone = entity.getTelefone();
    }
}
