
package com.styleme.microChallenge.repository;


import com.styleme.microChallenge.DTOs.ChallengeFullViewDTO;
import com.styleme.microChallenge.DTOs.ChallengeViewDTO;
import com.styleme.microChallenge.model.Challenge;
import jakarta.persistence.Tuple;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ChallengeRepository extends JpaRepository<Challenge, Integer>{
    
    List<Challenge> findByLevel(Integer level);
    
    @Query(nativeQuery = true, value =
        "SELECT c.id_challenge AS id, c.title as title, c.level AS level, c.description AS description, c.html AS html, " +
        "c.css_base AS cssBase, c.css_final AS cssFinal, (uc.user_id IS NOT NULL) AS done " +
        "FROM challenge c LEFT JOIN user_challenge uc ON c.id_challenge = uc.id_challenge AND uc.user_id = ?1 WHERE c.id_challenge = ?2"
    )
    Optional<Tuple> findChallengeByChallengeIdUserSigned(Integer userId, Integer challengeId);
    
    @Query(nativeQuery = true, value =
        "SELECT c.id_challenge id, c.title title, c.level level, c.description description, (uc.user_id IS NOT NULL) done " +
        "FROM challenge c LEFT JOIN user_challenge uc ON c.id_challenge = uc.id_challenge AND uc.user_id = ?1"
    )
    List<Tuple> findAllChallengesFeed(Integer userId);
    
    @Query(nativeQuery = true, value =
        "SELECT c.id_challenge id, c.title title, c.level level, c.description description, (uc.user_id IS NOT NULL) done " +
        "FROM challenge c LEFT JOIN user_challenge uc ON c.id_challenge = uc.id_challenge AND uc.user_id = ?1 where uc.user_id IS NOT NULL"
    )
    List<Tuple> findAllChallengesDoneByUser(Integer userId);
}
