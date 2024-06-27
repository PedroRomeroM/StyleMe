
package com.styleme.microOrquestrador.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserChallengeDTO {
    private Integer challengeId;
    private Integer userId;

    @Override
    public String toString() {
        return "UserChallengeDTO{" + "challengeId=" + challengeId + ", userId=" + userId + '}';
    }
    
    
}
