package com.styleme.microUsers;

import com.styleme.microUsers.DTOs.RankingPosDTO;
import com.styleme.microUsers.DTOs.UserChallengeDTO;
import com.styleme.microUsers.DTOs.UserTotalDTO;
import com.styleme.microUsers.exception.UsersException;
import com.styleme.microUsers.repository.UsersChallengeRepository;
import com.styleme.microUsers.service.UsersService;
import jakarta.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class MicroUsersApplicationTests {

        @Autowired
        UsersChallengeRepository rep;

        @Autowired
        UsersService ser;

	@Test
        void test(){
            int g = rep.deleteByChallengeId(1);
            System.out.println("com.styleme.microUsers.MicroUsersApplicationTests.test()   " + g);
        }

        @Test
        void test1() throws UsersException{
            UserTotalDTO g = ser.getUser(1);
            System.out.println(g);
        }

        @Test
        void test3(){
            List<Tuple> model = rep.getRanking();
            System.out.println("com " + model.get(0));
            List<RankingPosDTO> dto = new ArrayList<>();
            for(Tuple md : model){

                dto.add(new RankingPosDTO(
                        (Long) md.get("score"),
                        (Integer)md.get("id"),
                        (String) md.get("username"),
                        (Long) md.get("pos"),
                        false)
                );
            }

            System.out.println("com.styleme. " + dto.get(1));

        }

        @Test
        void getRankingSingle(){
            Optional<Tuple> model = rep.getPosRankingById(3);
            RankingPosDTO rp = new RankingPosDTO();
            if(model.isPresent()){
                Tuple md = model.get();
                rp.setId((Integer) md.get("id"));
                rp.setScore((Long) md.get("score"));
                rp.setUsername((String) md.get("username"));
                rp.setPos((Long) md.get("pos"));
            }
            System.out.println("com -> " + rp);

        }

        @Test
        void getRankingFull(){
            List<RankingPosDTO> dto = ser.getRanking(1);
            System.out.println("com: " + dto);
        }


        @Test
        void insertCh() throws UsersException{
            UserChallengeDTO dto = new UserChallengeDTO();
            dto.setChallengeId(1);
            dto.setUserId(1);
            dto.setScore(5);

            ser.insertChallenge(dto);
        }

        @Test
        void deleteCh() throws UsersException{
            ser.deleteChallenge(1, 1);
        }
}
