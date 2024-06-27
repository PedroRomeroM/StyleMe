package com.styleme.microOrquestrador.model;

import jakarta.persistence.*;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "saga_cadastro")
public class SagaCadastro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sagaId;
    
    @CreationTimestamp
    private Date sagaTempo;

    private Integer id;
    
}
