
package com.styleme.microAuth.controller;

import com.styleme.microAuth.DTOs.AuthDTO;
import com.styleme.microAuth.DTOs.AuthLoginDTO;
import com.styleme.microAuth.DTOs.AuthTotalDTO;
import com.styleme.microAuth.DTOs.AuthUpdateDTO;
import com.styleme.microAuth.exception.AuthLoginException;
import com.styleme.microAuth.exception.AuthUpdateException;
import com.styleme.microAuth.exception.EncryptionException;
import com.styleme.microAuth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    
    @Autowired
    private AuthService serv;
    
    //http://localhost:8080/api/auth/
    @PostMapping("/auth/login")
    public ResponseEntity<?> fazLogin(@RequestBody AuthLoginDTO dto){
        try {
            AuthDTO dto2 = serv.getLogin(dto.getEmail(), dto.getSenha());
            return new ResponseEntity<>(dto2, HttpStatus.OK);
        } catch (AuthLoginException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (EncryptionException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //http://localhost:8080/api/auth
    @PutMapping("/auth")
    public ResponseEntity<?> update(@RequestBody AuthUpdateDTO dto){
        try {
            AuthTotalDTO dto2 = serv.updateAuth(dto);
            return new ResponseEntity<>(dto2, HttpStatus.OK);
        } catch (AuthUpdateException ex) {
             return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (EncryptionException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /*
    //Substituido por Orquestrador *somente para testes*
    @PostMapping("/auth")
    public ResponseEntity<?> insert(@RequestBody AuthDTO dto){
        try {
            dto = serv.insertAuth(dto);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (AuthInsertException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (EncryptionException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //Testar
    //*somente para testes* se for excluir um auth tem que excluir um user
    @DeleteMapping("/auth/{id}")
    public ResponseEntity<?> deleteAuth(@PathVariable(value = "id") Integer id){
        try {
            serv.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */
    
    //http://localhost:8080/api/validate?data=teste
    @GetMapping("/validate")
    public ResponseEntity<?> validateEmail(@RequestParam("cod") String data){
        boolean res = serv.validateEmail(data);
        
        if(res){
            return new ResponseEntity<>("Email Validado!", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Erro na validação do Email!", HttpStatus.OK);
        }
    }
    
    @GetMapping("/auth/recover/{email}")
    public ResponseEntity<?> recoverPassword(@PathVariable(value = "email") String email){
        try {
            serv.recoverPassword(email);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (AuthUpdateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (EncryptionException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("/auth/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(value = "id") Integer id){
        try {
            AuthDTO dto2 = serv.getAuthById(id);
            return new ResponseEntity<>(dto2, HttpStatus.OK);
        } catch (AuthLoginException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/auth/emailexists/{email}")
    public ResponseEntity<?> emailExists(@PathVariable(value = "email") String email){
        boolean b = serv.emailExists(email);
        if(b){
            return new ResponseEntity<>("OK",HttpStatus.OK);
        }
         return new ResponseEntity<>("NOT-OK",HttpStatus.OK);
    }
    
    
}
