
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
public class ChallengeInsertDTO {
    
    private String title;
    private Integer level;
    private String description;
    private String html;
    private String cssBase;
    private String cssFinal;
}
