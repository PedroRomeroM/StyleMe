package com.styleme.microUsers.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    
    @Id
    @Column(name="id_user")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idUser;
    
    @Column(nullable = false, length = 100, unique = true)
    private String username;
    
    @Column(length = 15)
    private String imgType;
    
    @Lob
    private byte[] img;
    
}
