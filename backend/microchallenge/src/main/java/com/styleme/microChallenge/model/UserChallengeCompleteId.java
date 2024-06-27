
package com.styleme.microChallenge.model;

import com.styleme.microChallenge.model.Challenge;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserChallengeCompleteId implements Serializable{
    
    private Challenge challengeId;
    private Integer userId;    
}
