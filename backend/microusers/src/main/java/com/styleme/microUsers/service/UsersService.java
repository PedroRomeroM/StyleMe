
package com.styleme.microUsers.service;

import com.styleme.microUsers.ApplicationStartup;
import com.styleme.microUsers.DTOs.RankingPosDTO;
import com.styleme.microUsers.DTOs.UserChallengeDTO;
import com.styleme.microUsers.DTOs.UserFullDTO;
import com.styleme.microUsers.DTOs.UserTotalDTO;
import com.styleme.microUsers.DTOs.UsersDTO;
import com.styleme.microUsers.MicroUsersApplication;
import com.styleme.microUsers.exception.UsersException;
import com.styleme.microUsers.model.UserChallenge;
import com.styleme.microUsers.model.Users;
import com.styleme.microUsers.repository.UsersChallengeRepository;
import com.styleme.microUsers.repository.UsersRepository;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class UsersService {
    
    @Autowired
    private UsersRepository repUser;
    
    @Autowired
    private UsersChallengeRepository repCh;
    
    @Autowired
    private ModelMapper mapper;
    
    private static final List<String> fileType = new ArrayList<>(List.of("image/png", "image/jpg", "image/jpeg"));

    @Transactional
    public UserTotalDTO insertUser(String username, byte[] imgcontent, String imgtype) throws UsersException, IOException{
        try{
            Users user = new Users();
            user.setIdUser(null);
            user.setUsername(username);

            if(imgcontent != null){
                user = insertImage(user, imgcontent, imgtype);
            }
            repUser.save(user);
            
            UserTotalDTO dto1 = mapper.map(user, UserTotalDTO.class);
            return dto1;
        }catch(DataIntegrityViolationException e){
            SQLException ex = ((ConstraintViolationException) e.getCause()).getSQLException();
            String campo = ex.getMessage();
            campo = campo.substring(campo.indexOf("(") + 1, campo.indexOf(")"));
            throw new UsersException("Esse " + campo + " já existe!");
        }
    }
    
    //Por algum motivo que eu não entendo, se não houver imagem no update ao invés de só atualizar o username ele exclui a img
    @Transactional
    public UserTotalDTO update(UserFullDTO dto) throws UsersException, IOException{
        Optional<Users> usernameOp = repUser.findByUsername(dto.getUsername());
        if(usernameOp.isPresent()){
            if (!dto.getIdUser().equals(usernameOp.get().getIdUser())) {
                throw new UsersException("O usuário : '" + dto.getUsername() + "' já existe");
            }
        }
        if(dto.getUsername() != null){
            if(dto.getUsername().isBlank()){
                throw new UsersException("O username não pode ficar vazio!");
            }
        }
        
        Optional<Users> userOp = repUser.findById(dto.getIdUser());
        if(userOp.isPresent()){
            Users user = userOp.get();
            user.setUsername(dto.getUsername());
            
            if(dto.getImg() != null){
                user = insertImage(user, Base64.getDecoder().decode(dto.getImg()), dto.getImgtype());
                user = repUser.save(user);
            }else{
                repUser.updateUsername(dto.getIdUser(), dto.getUsername());
                user.setUsername(dto.getUsername());
            }
            
            UserTotalDTO dto1 = mapper.map(user, UserTotalDTO.class);
            return dto1;
        }else{
            throw new UsersException("Um Usuário Com Esse ID Não Existe");
        }
    }
    
    
    public Users insertImage(Users user, byte[] imgcontent, String imgtype) throws IOException, UsersException{
        if(imgcontent == null || imgtype == null){
            return user;
        }
                
        if(fileType.contains(imgtype)){
                user.setImg(imgcontent);
                user.setImgType(imgtype);
                return user;
        }else{
            throw new UsersException("O arquivo Enviado Não Está Num Formato Permitido");
        }
    }
    
    
    public UserTotalDTO getUser(Integer id) throws UsersException{
        Optional<Users> userOp = repUser.findById(id);
        if(id == 0){
            return MicroUsersApplication.adminDto;
        }
        if(userOp.isPresent()){
            Users user = userOp.get();
            Integer totalScore = repCh.getTotalScoreById(user.getIdUser());
            if(totalScore == null){
                totalScore = 0;
            }
            UserTotalDTO dto = mapper.map(user, UserTotalDTO.class);
            dto.setTotalScore(totalScore);
            return dto;
        }
        throw new UsersException("Um Usuário Com Esse Id Não Existe");
    }
    
    public Boolean usernameExist(String username){
        Optional<Users> usernameOp = repUser.findByUsername(username);
        if(usernameOp.isPresent()){
            return true;
        }
        return false;
    }
    
    public UserChallengeDTO insertChallenge(UserChallengeDTO dto) throws UsersException{
        Optional<Users> us = repUser.findById(dto.getUserId());
        if(us.isPresent()){
            UserChallenge usCh = new UserChallenge();
            usCh.setUserId(us.get());
            usCh.setScore(dto.getScore());
            usCh.setChallengeId(dto.getChallengeId());
            repCh.save(usCh);
            return mapper.map(usCh, UserChallengeDTO.class);
        }
        throw new UsersException("Um Usuário Com Esse Id Não Existe");
    }
    
    public void deleteChallenge(Integer idUser, Integer idChallenge){
        repCh.deleteByUserIdAndChallengeId(idUser, idChallenge);
    }
    
     public void deleteChallengeByChId(Integer idChallenge){
        repCh.deleteByChallengeId(idChallenge);
    }
    
    public void deleteUser(Integer id){
        repCh.deleteByUserId(id);
        repUser.deleteById(id);
    }
    
    public List<RankingPosDTO> getRanking(Integer id){
            List<Tuple> model = repCh.getRanking();
            List<RankingPosDTO> dto = new ArrayList<>();
            for(Tuple md : model){
                Long score  = Long.valueOf(0);
                if(md.get("score") != null){
                    score = (Long) md.get("score");
                }
                dto.add(new RankingPosDTO(
                        score,
                        (Integer)md.get("id"),
                        (String) md.get("username"),
                        (Long) md.get("pos"),
                        false)
                );
            }
            
            Optional<Tuple> model2 = repCh.getPosRankingById(id);
            
            if(model2.isPresent()){
                RankingPosDTO rp = new RankingPosDTO();
                Tuple md = model2.get();
                rp.setId((Integer) md.get("id"));
                rp.setScore((Long) md.get("score"));
                rp.setUsername((String) md.get("username"));
                rp.setPos((Long) md.get("pos"));
                rp.setMe(true);
                
                if(!dto.contains(rp)){
                    if(dto.size() > 4){
                        dto.removeLast();
                    }
                    dto.add(rp);
                }else{
                    dto.set(dto.indexOf(rp), rp);
                }
            }
            
            return dto;
    }

    
}
