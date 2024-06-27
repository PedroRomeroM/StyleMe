
package com.styleme.microChallenge.service;

import com.styleme.microChallenge.DTOs.ChallengeDTO;
import com.styleme.microChallenge.DTOs.ChallengeFullViewDTO;
import com.styleme.microChallenge.DTOs.ChallengeInsertDTO;
import com.styleme.microChallenge.DTOs.ChallengeViewDTO;
import com.styleme.microChallenge.DTOs.UserChallenge2DTO;
import com.styleme.microChallenge.DTOs.UserChallengeDTO;
import com.styleme.microChallenge.exception.ChallengeException;
import com.styleme.microChallenge.model.Challenge;
import com.styleme.microChallenge.model.UserChallengeComplete;
import com.styleme.microChallenge.repository.ChallengeRepository;
import com.styleme.microChallenge.repository.UserChallengeCompleteRepository;
import jakarta.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChallengeService {
    
    @Autowired
    private ChallengeRepository rep;
    
    @Autowired
    private UserChallengeCompleteRepository repUserCh;
    
    @Autowired
    private ModelMapper mapper;
    
    
    public ChallengeDTO insertChallenge(ChallengeInsertDTO dto){
        Challenge clg = mapper.map(dto, Challenge.class);
        clg.setId(null);
        clg = rep.save(clg);
        ChallengeDTO dto2 = mapper.map(clg, ChallengeDTO.class);
        return dto2;
    }
    
    public ChallengeDTO updateChallenge(ChallengeDTO dto) throws ChallengeException{
        Optional<Challenge> chOp = rep.findById(dto.getId());
        if(chOp.isPresent()){
            Challenge clg = mapper.map(dto, Challenge.class);
            clg = rep.save(clg);
            ChallengeDTO dto2 = mapper.map(clg, ChallengeDTO.class);
            return dto2;
        }
        
        throw new ChallengeException("O Id desse Desafio não Existe");
    }
    
    public void delete(Integer id){
        rep.deleteById(id);
    }
    
    public ChallengeFullViewDTO getChallengeById(Integer userId, Integer challengeId) throws ChallengeException{
         Optional<Tuple> dto = rep.findChallengeByChallengeIdUserSigned(userId, challengeId);
        if(dto.isPresent()){
            Tuple md = dto.get();
            ChallengeFullViewDTO dto2 = new ChallengeFullViewDTO(
                    (Integer) md.get("id"),
                    (String)md.get("title"),
                    (Integer)md.get("level"),
                    (String) md.get("description"),
                    (String) md.get("html"),
                    (String) md.get("cssBase"),
                    (String) md.get("cssFinal"),
                    (Boolean) md.get("done"));
            
            return dto2;
        }
        throw new ChallengeException("O Id desse Desafio não Existe");
    }
    
    public List<ChallengeViewDTO> getAllChallenges(Integer userId){
        List<Tuple> model = rep.findAllChallengesFeed(userId);
        List<ChallengeViewDTO> dto = new ArrayList<>();
        for(Tuple md : model){
            
            dto.add(new ChallengeViewDTO(
                    (Integer) md.get("id"),
                    (String)md.get("title"),
                    (Integer)md.get("level"),
                    (String) md.get("description"),
                    (Boolean) md.get("done"))
            );
        }
        return dto;
    }
    
    public List<ChallengeViewDTO> getDoneChallenges(Integer userId){
        List<Tuple> model = rep.findAllChallengesDoneByUser(userId);
        List<ChallengeViewDTO> dto = new ArrayList<>();
        for(Tuple md : model){
            
            dto.add(new ChallengeViewDTO(
                    (Integer) md.get("id"),
                    (String)md.get("title"),
                    (Integer)md.get("level"),
                    (String) md.get("description"),
                    (Boolean) md.get("done"))
            );
        }
        return dto;
    }
    
    public UserChallenge2DTO insertUserChallenge(UserChallengeDTO dto) throws ChallengeException{
        Optional<Challenge> ch = rep.findById(dto.getChallengeId());
        if(ch.isPresent()){
            UserChallengeComplete usCh = new UserChallengeComplete();
            usCh.setChallengeId(ch.get());
            usCh.setUserId(dto.getUserId());
            
            repUserCh.save(usCh);
            
            UserChallenge2DTO ret = new UserChallenge2DTO();
            ret.setChallengeId(dto.getChallengeId());
            ret.setUserId(dto.getUserId());
            ret.setScore(ch.get().getLevel());
            
            return ret;
        }
        throw new ChallengeException("Um Usuário Com Esse Id Não Existe");
    }
    
    public void deleteUserChallenge(Integer userId, Integer challengeId){
        repUserCh.deleteByUserIdAndChallengeId(userId, challengeId);
    }
    
    public void deleteUserChallengeByChId(Integer chId){
        repUserCh.deleteByChallengeId(chId);
        rep.deleteById(chId);
    }
    
    public void deleteUserChallengeByUserId(Integer userId){
        repUserCh.deleteByUserId(userId);
    }

}
