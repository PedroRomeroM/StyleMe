
package com.styleme.microUsers.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserChallengeDTO {
    
    private Integer userId;
    private Integer challengeId;
    private Integer score;
}
