
package com.styleme.microUsers.repository;


import com.styleme.microUsers.model.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface UsersRepository extends JpaRepository<Users, Integer>{
    
    @Query(nativeQuery = true,
     value = "SELECT id_user, null as img, null as img_type, username FROM users WHERE username = :username")
    Optional<Users> findByUsername(String username);
    
    @Modifying
    @Query(nativeQuery = true,
    value = "UPDATE users SET username = :username WHERE id_user = :idUser")
    int updateUsername(Integer idUser, String username);
}
