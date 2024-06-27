package com.styleme.microAuth;

import com.styleme.microAuth.DTOs.AuthDTO;
import com.styleme.microAuth.exception.AuthInsertException;
import com.styleme.microAuth.exception.EncryptionException;
import com.styleme.microAuth.model.Auth;
import com.styleme.microAuth.repository.AuthRepository;
import com.styleme.microAuth.service.AuthService;
import com.styleme.microAuth.utils.Encrypt;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class MicroAuthApplicationTests {

    @Autowired
    AuthService serv;

    @Test
    public void testGerarSalt(){
        System.out.println("gerarSalt");
        int size = -5;
        String expResult = "";
        String result = Encrypt.gerarSalt(size);
        System.out.println(result);
    }

    @Test
    public void tes() throws EncryptionException{
        System.out.println("Slatar");
        int size = -5;
        String expResult = "";
        String salt = "";
        String result = Encrypt.encryptPassword(expResult, salt);
        System.out.println(result);
    }

    @Test
    public void insert() throws AuthInsertException, EncryptionException{
        AuthDTO dto = new AuthDTO();
        dto.setEmail("da");
        dto.setSenha("123");
        dto.setTipoUser("ADM");
        serv.insertAuth(dto);
    }

    @Test
    public void testDelete(){
        serv.delete(1);
    }

    @Autowired
    AuthRepository rep;

    @Test
    public void getTest(){
        Optional<Auth> at = rep.findByEmail("da");
        System.out.println("com " + at.isPresent());
    }
}
