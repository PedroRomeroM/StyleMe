

package com.styleme.microOrquestrador.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class SagaDeleteUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sagaId;
    
    @CreationTimestamp
    private Date sagaTempo;

    private Boolean userReady;
    
    private Boolean challengeReady;
    
}
