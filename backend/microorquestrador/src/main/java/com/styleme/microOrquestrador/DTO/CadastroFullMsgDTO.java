
package com.styleme.microOrquestrador.DTO;

import java.util.Base64;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CadastroFullMsgDTO {
    
    private String email;
    private String senha;
    private String username;
    private String imgcontent;
    private String imgtype;
}
