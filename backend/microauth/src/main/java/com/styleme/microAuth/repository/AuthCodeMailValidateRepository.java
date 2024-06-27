
package com.styleme.microAuth.repository;

import com.styleme.microAuth.model.CodeMailValidate;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthCodeMailValidateRepository extends JpaRepository<CodeMailValidate, String>{
    
}
