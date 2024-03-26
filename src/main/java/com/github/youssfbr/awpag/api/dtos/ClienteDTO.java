package com.github.youssfbr.awpag.api.dtos;

import com.github.youssfbr.awpag.domain.models.Cliente;
import com.github.youssfbr.awpag.domain.services.validations.ValidationGroups;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {

    @NotNull(groups = ValidationGroups.ClienteDTOId.class)
    private Long id;

    @NotBlank
    @Size(max = 60)
    private String nome;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(max = 20)
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
