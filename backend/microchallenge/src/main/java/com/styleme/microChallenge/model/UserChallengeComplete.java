
package com.styleme.microChallenge.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_challenge")
@IdClass(UserChallengeCompleteId.class)
public class UserChallengeComplete {
    
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_challenge", nullable = false)
    private Challenge challengeId;
    
    @Id
    private Integer userId;
    
}
