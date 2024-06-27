/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.styleme.microChallenge.DTOs;

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
