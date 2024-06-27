/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.styleme.microChallenge.DTOs;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeFullViewDTO {
    
    private Integer id;
    private String title;
    private Integer level;
    private String description;
    private String html;
    private String cssBase;
    private String cssFinal;
    private Boolean done;
}
