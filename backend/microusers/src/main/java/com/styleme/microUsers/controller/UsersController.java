
package com.styleme.microUsers.controller;


import com.styleme.microUsers.DTOs.RankingPosDTO;
import com.styleme.microUsers.DTOs.UserChallengeDTO;
import com.styleme.microUsers.DTOs.UserFullDTO;
import com.styleme.microUsers.DTOs.UserTotalDTO;
import com.styleme.microUsers.service.UsersService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UsersController {
    
    @Autowired
    private UsersService serv;
      
    @PutMapping("/user")
    public ResponseEntity<?> update(@RequestBody UserFullDTO dto){
        try {
            UserTotalDTO dto2 = serv.update(dto);
            return new ResponseEntity<>(dto2, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    
    @GetMapping(value = "/user/ranking/{id}")
    public ResponseEntity<?> getRanking(@PathVariable(value = "id") Integer id){
        try{
            List<RankingPosDTO> dto = serv.getRanking(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping(value = "/user/usernameExists/{username}")
    public ResponseEntity<?> verifyIfUsernameExist(@PathVariable(value = "username") String username){
        Boolean b = serv.usernameExist(username);
        if(b){
            return new ResponseEntity<>("OK",HttpStatus.OK);
        }
        return new ResponseEntity<>("NOT-OK",HttpStatus.OK);
    }
    
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable(value = "id") Integer id){
        try {
            UserTotalDTO dto = serv.getUser(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping(value = "/user/{id}/image")
    public ResponseEntity<?> getImage(@PathVariable Integer id) {
        
        try{
            UserTotalDTO dto = serv.getUser(id);
            byte[] imageBytes = dto.getImg();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    /*
    //Testar abaixo
    //Substituido por Orquestrador *somente para testes*
    @PostMapping("/user/ch")
    public ResponseEntity<?> insertChallenge(@RequestBody UserChallengeDTO dto){
        try {
            UserChallengeDTO dto2 = serv.insertChallenge(dto);
            return new ResponseEntity<>(dto2, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
     
    @DeleteMapping("/user/ch/{idUser}/{idChall}")
    public ResponseEntity<?> deleteChallenge(@PathVariable(value = "idUser") Integer idUser,
            @PathVariable(value = "idChall") Integer idChall){
        try {
            serv.deleteChallenge(idUser, idChall);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
     //Substituido por Orquestrador *somente para testes*
    @PostMapping("/user")
    public ResponseEntity<?> insert(@ModelAttribute UserFullDTO dto){
        try {
            UserTotalDTO dto2 = serv.insertUser(dto.getUsername(), 
                    dto.getImg().getBytes(), dto.getImg().getContentType());
            
            return new ResponseEntity<>(dto2, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    
    //*somente para testes* se for excluir um auth tem que excluir um user
    @DeleteMapping("/user/d/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer id){
        try {
            serv.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
*/
     
}
