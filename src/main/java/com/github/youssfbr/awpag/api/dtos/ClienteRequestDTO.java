package com.github.youssfbr.awpag.api.dtos;

import com.github.youssfbr.awpag.domain.models.Cliente;
import com.github.youssfbr.awpag.domain.services.validations.UserInsertValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
@UserInsertValid
public class ClienteRequestDTO {

    @NotBlank(message = "Campo obrigatório")
    private String nome;

    @Email(message = "Favor entrar com e-mail válido")
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
