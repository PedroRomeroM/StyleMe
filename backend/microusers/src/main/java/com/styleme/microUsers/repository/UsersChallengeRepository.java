
package com.styleme.microUsers.repository;


import com.styleme.microUsers.DTOs.RankingPosDTO;
import com.styleme.microUsers.model.UserId;
import com.styleme.microUsers.model.UserChallenge;
import com.styleme.microUsers.model.Users;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UsersChallengeRepository extends JpaRepository<UserChallenge, UserId>{
    
    @Query(nativeQuery = true, value = "select user_id, challenge_id, score from user_challenge WHERE user_id = :userId")
    Optional<UserChallenge> findByUserId(Integer userId);
    
    @Query(nativeQuery = true, value = "SELECT SUM(score) FROM user_challenge WHERE user_id = :userId")
    Integer getTotalScoreById(@Param("userId") Integer userId);
    
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM user_challenge WHERE user_id = :userId AND challenge_id = :challenge_id")
    int deleteByUserIdAndChallengeId(Integer userId, @Param("challenge_id") Integer challengeId);
    
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM user_challenge WHERE challenge_id = :challenge_id")
    int deleteByChallengeId(Integer challenge_id);
    
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM user_challenge WHERE user_id = :user_id ")
    int deleteByUserId(@Param("user_id") Integer userId);
   
    @Query(nativeQuery = true, value = "select score, id, username, pos from (select score, id, username, row_number () over (order by score desc) as pos " +
        "from (select (CASE WHEN (SUM(uc.score) IS NULL) THEN 0 ELSE SUM(uc.score) END) AS score, " +
        "u.id_user id, u.username username FROM user_challenge uc RIGHT JOIN users u " +
        "ON u.id_user = uc.user_id GROUP BY uc.user_id, u.id_user ORDER BY score desc)) limit 5")
    List<Tuple> getRanking(); 
    
    
    @Query(nativeQuery = true, value = "select score, id, username, pos from (select score, id, username, row_number () over (order by score desc) as pos " +
        "from (select (CASE WHEN (SUM(uc.score) IS NULL) THEN 0 ELSE SUM(uc.score) END) AS score, " +
        "u.id_user id, u.username username FROM user_challenge uc RIGHT JOIN users u " +
        "ON u.id_user = uc.user_id GROUP BY uc.user_id, u.id_user ORDER BY score desc)) WHERE id = :idUser")
    Optional<Tuple> getPosRankingById(Integer idUser);
    
}

