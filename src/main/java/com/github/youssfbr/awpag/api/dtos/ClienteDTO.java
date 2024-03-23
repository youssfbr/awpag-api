package com.github.youssfbr.awpag.api.dtos;

import com.github.youssfbr.awpag.domain.models.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ClienteDTO {

    private Long id;

    @NotBlank(message = "Campo obrigatório")
    private String nome;

    @Email(message = "Favor entrar com e-mail válido")
    private String email;

    private String telefone;

    public ClienteDTO() { }

    public ClienteDTO(Long id , String nome , String email , String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public ClienteDTO(Cliente entity) {
        id = entity.getId();
        nome = entity.getNome();
        email = entity.getEmail();
        telefone = entity.getTelefone();
    }
}
