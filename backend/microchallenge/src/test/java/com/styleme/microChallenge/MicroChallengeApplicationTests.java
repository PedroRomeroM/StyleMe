package com.styleme.microChallenge;

import com.styleme.microChallenge.DTOs.ChallengeFullViewDTO;
import com.styleme.microChallenge.DTOs.ChallengeViewDTO;
import com.styleme.microChallenge.DTOs.UserChallenge2DTO;
import com.styleme.microChallenge.DTOs.UserChallengeDTO;
import com.styleme.microChallenge.exception.ChallengeException;
import com.styleme.microChallenge.model.Challenge;
import com.styleme.microChallenge.model.UserChallengeComplete;
import com.styleme.microChallenge.model.UserChallengeCompleteId;
import com.styleme.microChallenge.repository.ChallengeRepository;
import com.styleme.microChallenge.repository.UserChallengeCompleteRepository;
import com.styleme.microChallenge.service.ChallengeService;
import jakarta.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(SpringRunner.class)
@SpringBootTest
class MicroChallengeApplicationTests {

    @Autowired
    ChallengeRepository rep;

    @Autowired
    UserChallengeCompleteRepository repUs;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    ChallengeService serv;

    @Test
    public void teste(){
        List<Tuple> model = rep.findAllChallengesFeed(1);
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

        System.out.println(dto.get(0).toString());
    }

    @Test
    public void teste5(){
        List<Tuple> model = rep.findAllChallengesDoneByUser(1);
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

        System.out.println(dto.get(1).toString());
    }


    @Test
    public void teste2(){
        Optional<Tuple> dto = rep.findChallengeByChallengeIdUserSigned(3, 2);
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

            System.out.println(dto2);
        }
        System.out.println(dto);
    }

    /*
    @Test
    public void test3(){
        Integer userId = 5;
        Integer challengeId = 1;

        Optional<Challenge> chOp = rep.findById(challengeId);
        if(chOp.isPresent()){
            UserChallengeCompleteId usChId = new UserChallengeCompleteId(chOp.get(), userId);

            Optional<UserChallengeComplete> usCh = repUs.findById(usChId);
            if(usCh.isEmpty()){
                UserChallengeComplete userChallenge = new UserChallengeComplete();
                userChallenge.setChallengeId(chOp.get());
                userChallenge.setUserId(userId);
                repUs.save(userChallenge);
            }
            System.out.println("Esse Usuário Já Realizou Esse Desafio!");
        }

        System.out.println("Esse Id de Desafio Não Existe!");

    }
*/

    @Test
    public void testInsert() throws ChallengeException{
        UserChallengeDTO dto = new UserChallengeDTO();
        dto.setChallengeId(2);
        dto.setUserId(6);

        UserChallenge2DTO dto2 = serv.insertUserChallenge(dto);
        System.out.println("com " + dto2);

    }

    @Test
    public void testDelet(){
        serv.deleteUserChallenge(6, 2);
    }
}
