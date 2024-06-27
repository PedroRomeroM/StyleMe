package com.styleme.microAuth.DTOs;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AuthDTO {
    private Integer id;
    private String email;
    private String senha;
    private String tipoUser;
}
