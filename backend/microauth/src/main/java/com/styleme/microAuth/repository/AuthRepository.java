
package com.styleme.microAuth.repository;

import com.styleme.microAuth.model.Auth;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthRepository extends JpaRepository<Auth, Integer>{
    
    Optional<Auth> findByEmail(String email);
    
    int deleteAuthByEmail(String email);
}
