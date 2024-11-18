package org.myreview.Application.Services;

import lombok.AllArgsConstructor;
import org.myreview.Domain.Entities.Token;
import org.myreview.Domain.Entities.User;
import org.myreview.Infra.Repositories.TokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    public void saveConfirmationToken(Token token) {
        tokenRepository.save(token);
    }

    public Optional<Token> getToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return tokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }

    public String createToken() {
        return UUID.randomUUID().toString();
    }
}
