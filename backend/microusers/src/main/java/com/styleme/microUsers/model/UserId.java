
package com.styleme.microUsers.model;

import com.styleme.microUsers.model.Users;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserId implements Serializable{ 
    private Users userId;
    private Integer challengeId;
}
