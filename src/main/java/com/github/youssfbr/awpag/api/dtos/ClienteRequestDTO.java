package com.github.youssfbr.awpag.api.dtos;

import com.github.youssfbr.awpag.domain.models.Cliente;
import lombok.Getter;

@Getter
public class ClienteRequestDTO {

    private String nome;
    private String email;
    private String telefone;

    public ClienteRequestDTO() { }

    public ClienteRequestDTO(String nome , String email , String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public ClienteRequestDTO(Cliente entity) {
        nome = entity.getNome();
        email = entity.getEmail();
        telefone = entity.getTelefone();
    }
}
