
package com.styleme.microAuth.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "login")
public class Auth {
    
    @Id
    private Integer id;
    
    @Column(unique = true)
    private String email;
    
    @Column(nullable = true)
    private String senha;
    private String salt;
    private String tipoUser;
}
