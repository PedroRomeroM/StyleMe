
package com.styleme.microUsers.DTOs;

import java.util.Base64;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CadastroFullMsgDTO {
    
    private Integer idUser;
    private String email;
    private String senha;
    private String username;
    private byte[] imgcontent;
    private String imgtype;

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /*
    public void setImgcontent(byte[] imgcontent) {
        System.out.println("com.styleme.microUsers.DTOs.CadastroFullMsgDTO.setImgcontent(): bytes" );
        this.imgcontent = imgcontent;
    }
    */
    public void setImgcontent(String imgcontent) {
        this.imgcontent = Base64.getDecoder().decode(imgcontent);
    }

    public void setImgtype(String imgtype) {
        this.imgtype = imgtype;
    }
    
    
}
