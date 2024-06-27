
package com.styleme.microAuth.utils;


import com.styleme.microAuth.exception.EncryptionException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encrypt {
    
    public static int SALT_SIZE = 32;
    
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
    
    private static String criptoBcrypt(String senha){
        String result = encoder.encode(senha);
        return result;
    }
        
    public static String gerarSalt(int size){
        int or;
        if(size < 0){
            size = size * -1;
        }
        
        or = size;
        
        if(size % 2 == 1){
            size++;
        }
        
        StringBuilder salt = new StringBuilder();
        SecureRandom random = new SecureRandom();
        byte[] saltByte = new byte[size/2];
        random.nextBytes(saltByte);
        
        for (byte b : saltByte) {
            salt.append(String.format("%02x", b)); //Converte para caracter legivel
        }
        
        
        return salt.toString().substring(0, or);
    }
    
    public static String encryptPassword(String senha, String salt) throws EncryptionException{
        senha = criptoBcrypt(senha + salt);
        return senha;
    }
    
    public static boolean verifyPasswordIsRight(String senha, String cod){
        return encoder.matches(senha, cod);
    }
    
}
