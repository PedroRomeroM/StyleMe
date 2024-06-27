
package com.styleme.microChallenge.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeViewDTO {
    private Integer id;
    private String title;
    private Integer level;
    private String description;
    private Boolean done;

    @Override
    public String toString() {
        return "ChallengeViewDTO{" + "id=" + id + ", title=" + title + ", level=" + level + ", description=" + description + ", done=" + done + '}';
    }
    
    
}
