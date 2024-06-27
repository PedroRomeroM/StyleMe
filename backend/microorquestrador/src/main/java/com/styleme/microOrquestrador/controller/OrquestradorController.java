
package com.styleme.microOrquestrador.controller;


import com.styleme.microOrquestrador.DTO.CadastroFullDTO;
import com.styleme.microOrquestrador.DTO.CadastroFullMsgDTO;
import com.styleme.microOrquestrador.DTO.UserChallengeDTO;
import com.styleme.microOrquestrador.sagaChDone.InitSagaChDone;
import com.styleme.microOrquestrador.sagaCadastro.InitSagaCadastro;
import com.styleme.microOrquestrador.sagaDeleteCh.InitSagaDeleteCh;
import com.styleme.microOrquestrador.sagaDeleteUser.InitSagaDeleteUser;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class OrquestradorController {
    
    @Autowired
    private InitSagaCadastro servCadas;
    
    @Autowired
    private InitSagaChDone servChDone;
    
    @Autowired
    private InitSagaDeleteCh servChDelete;
    
    @Autowired
    private InitSagaDeleteUser servUserDelete;
    
    @PostMapping("/orq/cadastro")
    public ResponseEntity<?> insertUser(@RequestBody CadastroFullMsgDTO dto){
        try {
            servCadas.cadastro(dto);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/orq/chdone")
    public ResponseEntity<?> insertChDone(@RequestBody UserChallengeDTO dto){
        try {
            servChDone.insertChallengeUser(dto);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/orq/chdelete/{id}")
    public ResponseEntity<?> deleteCh(@PathVariable(value = "id") Integer id){
        try {
            servChDelete.deleteChallenge(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/orq/userdelete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Integer id){
        try {
            servUserDelete.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
}
