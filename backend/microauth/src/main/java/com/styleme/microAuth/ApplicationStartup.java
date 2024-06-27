
package com.styleme.microAuth;

import com.styleme.microAuth.exception.EncryptionException;
import com.styleme.microAuth.model.Auth;
import com.styleme.microAuth.repository.AuthRepository;
import com.styleme.microAuth.utils.Encrypt;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent>{
    
    @Autowired
    private AuthRepository rep;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            Auth auth = new Auth();
            auth.setId(0);
            auth.setEmail("admin");
            auth.setTipoUser("ADM");
            
            String senha = "admin";
            String salt = Encrypt.gerarSalt(Encrypt.SALT_SIZE);
            senha = Encrypt.encryptPassword(senha, salt);
            
            auth.setSenha(senha);
            auth.setSalt(salt);
            
            rep.save(auth);
        } catch (EncryptionException ex) {
            Logger.getLogger(ApplicationStartup.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
