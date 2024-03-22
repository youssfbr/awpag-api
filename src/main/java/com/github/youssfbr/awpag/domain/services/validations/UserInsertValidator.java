package com.github.youssfbr.awpag.domain.services.validations;

import com.github.youssfbr.awpag.api.controllers.exceptions.FieldMessage;
import com.github.youssfbr.awpag.api.dtos.ClienteRequestDTO;
import com.github.youssfbr.awpag.domain.models.Cliente;
import com.github.youssfbr.awpag.domain.repositories.IClienteRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UserInsertValidator implements ConstraintValidator<UserInsertValid, ClienteRequestDTO> {

    private final IClienteRepository repository;
    @Override
    public boolean isValid(ClienteRequestDTO dto , ConstraintValidatorContext context) {

        final List<FieldMessage> list = new ArrayList<>();

        final Cliente cliente = repository.findByEmail(dto.getEmail());

        if (cliente != null) {
            list.add(new FieldMessage("email", "e-mail já existe"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();

    }

    @Override
    public void initialize(UserInsertValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
