
package com.styleme.microChallenge.controller;

import com.styleme.microChallenge.DTOs.ChallengeDTO;
import com.styleme.microChallenge.DTOs.ChallengeFullViewDTO;
import com.styleme.microChallenge.DTOs.ChallengeInsertDTO;
import com.styleme.microChallenge.DTOs.ChallengeViewDTO;
import com.styleme.microChallenge.DTOs.UserChallenge2DTO;
import com.styleme.microChallenge.DTOs.UserChallengeDTO;
import com.styleme.microChallenge.service.ChallengeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ChallengeController {
    
    @Autowired
    private ChallengeService serv;
    
    
    @PostMapping("/ch")
    public ResponseEntity<?> insert(@RequestBody ChallengeInsertDTO dto){
        try {
            ChallengeDTO dto2 = serv.insertChallenge(dto);
            return new ResponseEntity<>(dto2, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/ch")
    public ResponseEntity<?> update(@RequestBody ChallengeDTO dto){
        try {
            dto = serv.updateChallenge(dto);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    /*
    Orquestrador
    @DeleteMapping("/ch/d/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer id){
        try {
            serv.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    */
    
    @GetMapping("/ch/{id}")
    public ResponseEntity<?> getFeed(@PathVariable(value = "id") Integer id){
        try {
            List<ChallengeViewDTO> dto = serv.getAllChallenges(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/ch/perfil/{id}")
    public ResponseEntity<?> getFeedPerfil(@PathVariable(value = "id") Integer id){
        try {
            List<ChallengeViewDTO> dto = serv.getDoneChallenges(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    
    @GetMapping("/ch/{id}/{ch}")
    public ResponseEntity<?> getChallenge(@PathVariable(value = "id") Integer userId,
            @PathVariable(value = "ch") Integer challengeId){
        try {
            ChallengeFullViewDTO dto = serv.getChallengeById(userId, challengeId);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    /*
    //Testar
    //Substituido por Orquestrador *somente para testes*
    @PostMapping("/user/ch")
    public ResponseEntity<?> insertChallengeDonebyUser(@RequestBody UserChallengeDTO dto){
        try {
            UserChallenge2DTO dto2 = serv.insertUserChallenge(dto);
            return new ResponseEntity<>(dto2, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
     
    //implementar saga
    @DeleteMapping("/user/ch/{idUser}/{idChall}")
    public ResponseEntity<?> deleteChallengeDoneByUser(@PathVariable(value = "idUser") Integer idUser,
            @PathVariable(value = "idChall") Integer idChall){
        try {
            serv.deleteUserChallenge(idChall, idUser);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    */
    
}
