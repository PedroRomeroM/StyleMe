
package com.styleme.microChallenge.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserChallenge2DTO {
    
    private Integer userId;
    private Integer challengeId;
    private Integer score;
}
