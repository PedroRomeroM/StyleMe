
package com.styleme.microUsers.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserFullDTO {
    
    private Integer idUser;
    private String username;
    private String img;
    private String imgtype;
}
