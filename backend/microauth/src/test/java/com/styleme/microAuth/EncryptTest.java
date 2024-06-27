
package com.styleme.microAuth;

import com.styleme.microAuth.exception.EncryptionException;
import com.styleme.microAuth.utils.Encrypt;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptTest {
    
    public EncryptTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void hello() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String result = encoder.encode("myPassword");
        boolean a = encoder.matches("myPassword", result);
        System.out.println(a);
    }
    
    @org.junit.jupiter.api.Test
    public void testGerarSalt(){
        System.out.println("gerarSalt");
        int size = -5;
        String expResult = "";
        String result = com.styleme.microAuth.utils.Encrypt.gerarSalt(size);
        System.out.println(result);
    }
    
    @Test
    public void testEnc() throws EncryptionException{
        String salt = "etrerepo";//Encrypt.gerarSalt(8);
        String senha = "teste";
        
        String result = Encrypt.encryptPassword(senha, salt);
        System.out.println(result);
        
        System.out.println(Encrypt.verifyPasswordIsRight(senha+salt, result));
    }
    
    @Test
    public void testEncr() throws EncryptionException{
        String salt = "etrerepo";//Encrypt.gerarSalt(8);
        String senha = "teste";
        
        String result = Encrypt.encryptPassword(senha, salt);
        System.out.println(result);
        
        System.out.println(Encrypt.verifyPasswordIsRight(senha+salt, result));
    }
    
    @Test
    public void es() throws IOException{

            
            ClassPathResource svgResource = new ClassPathResource("static/logo.svg");
            String svgContent = new String(Files.readAllBytes(svgResource.getFile().toPath()), StandardCharsets.UTF_8);
            
            System.out.println(svgContent);
            //temp.getContext().setVariable("logo", svgContent);
    }
}
