package com.github.youssfbr.awpag.domain.services.validations;

import com.github.youssfbr.awpag.api.controllers.exceptions.FieldMessage;
import com.github.youssfbr.awpag.api.dtos.ClienteUpdateDTO;
import com.github.youssfbr.awpag.domain.models.Cliente;
import com.github.youssfbr.awpag.domain.repositories.IClienteRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdateValid, ClienteUpdateDTO> {

    private final IClienteRepository clienteRepository;
    private final HttpServletRequest request;

    @Override
    public boolean isValid(ClienteUpdateDTO dto , ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked")
        final Map<String, String> uriVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        final long clienteId = Long.parseLong(uriVars.get("clienteId"));

        final List<FieldMessage> list = new ArrayList<>();

        final Cliente cliente = clienteRepository.findByEmail(dto.getEmail());

        if (cliente != null && clienteId != cliente.getId()) {
            list.add(new FieldMessage("email", "Já existe um cliente cadastrado com esse e-mail."));
        }

//        final Optional<Cliente> cliente = clienteRepository.findByEmail(dto.getEmail());
//
//        final boolean emailEmUso = clienteRepository.findByEmail(dto.getEmail())
//                .filter(c -> !c.equals(cliente))
//                .isPresent();
//
//        if (emailEmUso && clienteId != cliente.get().getId()) {
//            list.add(new FieldMessage("email" , "Já existe um cliente cadastro com esse e-mail"));
//        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();

    }

    @Override
    public void initialize(ClienteUpdateValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
