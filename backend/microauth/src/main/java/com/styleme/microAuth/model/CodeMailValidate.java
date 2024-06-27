
package com.styleme.microAuth.model;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "codeMailValidate")
public class CodeMailValidate {
    
    @Id
    private String code;
    
    private int idLogin;
}
