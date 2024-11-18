package org.myreview.Infra.Repositories;

import jakarta.transaction.Transactional;
import org.myreview.Domain.Entities.Token;
import org.myreview.Domain.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(String token);
    Optional<Token> findByUser(User user);
    Boolean existsByToken(String token);
    Boolean existsByUser(User user);

    @Transactional
    @Modifying
    @Query("UPDATE Token t " +
            "SET t.confirmedAt = ?2 WHERE t.token = ?1")
    int updateConfirmedAt(String token, java.time.LocalDateTime confirmedAt);
}
