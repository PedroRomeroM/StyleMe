
package com.styleme.microUsers.DTOs;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RankingPosDTO {
    
    private Long score;
    private Integer id;
    private String username;
    private Long pos;
    private Boolean me;

    @Override
    public String toString() {
        return "RankingPosDTO{" + "score=" + score + ", id=" + id + ", username=" + username + ", pos=" + pos + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RankingPosDTO other = (RankingPosDTO) obj;
        return Objects.equals(this.id, other.id);
    }

    
    
    
}
