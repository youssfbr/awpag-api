package com.github.youssfbr.awpag.api.dtos;

import com.github.youssfbr.awpag.domain.services.validations.ClienteInsertValid;
import lombok.Getter;

@Getter
@ClienteInsertValid
public class ClienteInsertDTO extends ClienteDTO {
    public ClienteInsertDTO() { super(); }

}
