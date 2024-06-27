
package com.styleme.microChallenge.repository;


import com.styleme.microChallenge.model.UserChallengeCompleteId;
import com.styleme.microChallenge.model.UserChallengeComplete;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserChallengeCompleteRepository extends JpaRepository<UserChallengeComplete, UserChallengeCompleteId>{

    Optional<UserChallengeComplete> findByUserId(Integer userid);
    
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM user_challenge WHERE user_id = :userId AND id_challenge = :challengeId")
    int deleteByUserIdAndChallengeId(@Param("userId") Integer userId, @Param("challengeId") Integer challengeId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM user_challenge WHERE id_challenge = :challengeId")
    int deleteByChallengeId(@Param("challengeId") Integer challengeId);
    
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM user_challenge WHERE user_id = :userId")
    int deleteByUserId(@Param("userId") Integer userId);

}
