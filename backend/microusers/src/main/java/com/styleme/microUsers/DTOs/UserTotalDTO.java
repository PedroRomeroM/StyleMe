
package com.styleme.microUsers.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserTotalDTO {
    
    private Integer idUser;
    private String username;
    private Integer totalScore;
    private String imgType;
    private byte[] img;

    @Override
    public String toString() {
        return "UserTotalDTO{" + "idUser=" + idUser + ", username=" + username + ", totalScore=" + totalScore + ", imgType=" + imgType + ", img=" + img + '}';
    }
    
    
}
