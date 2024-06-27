
package com.styleme.microOrquestrador.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CadastroFullDTO {
    
    private String email;
    private String senha;
    private String username;

    @JsonIgnore
    private MultipartFile img;
}
