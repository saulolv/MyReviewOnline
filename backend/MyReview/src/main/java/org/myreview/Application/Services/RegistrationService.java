package org.myreview.Application.Services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.myreview.Domain.DTOs.RegisterRequest;
import org.myreview.Domain.Entities.Token;
import org.myreview.Domain.Entities.User;
import org.myreview.Domain.Enums.Role;
import org.myreview.Infra.Mail.EmailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final TokenService tokenService;
    private final UserService userService;
    // private final EmailService emailService;
    private final EmailSender emailSender;

    public String signUpUser(RegisterRequest registerRequest) {
        User user = new User(
                registerRequest.getUsername(),
                registerRequest.getEmail(),
                registerRequest.getPassword(),
                Role.USER
        );

        String token = userService.signUpUser(user);
        String link = "http://localhost:8080/api/v1/registration/confirm?token=" + token;

        // emailService.send(registerRequest.getEmail(), buildEmail(registerRequest.getUsername(), link));
        emailSender.send(registerRequest.getEmail(), buildEmail(registerRequest.getUsername(), link));


        return token;
    }

    @Transactional
    public String confirmToken(String token) {
        String loginLink = "http://localhost:8080/api/v1/registration/login";
        Token confirmationToken = tokenService.getToken(token)
                .orElseThrow(() -> new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        tokenService.setConfirmedAt(token);
        userService.enableUser(confirmationToken.getUser().getUsername());

        return buildConfirmedPage(loginLink, "Email confirmed");
    }

    private String buildEmail(String name, String link) {
        return
                "<p> Hi " + name +
                        ",</p>" +
                        "<p> Thank you for registering. Please click on the below link to activate your account: </p>" +
                        "<blockquote><p> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 10 minutes.";
    }
    private String buildConfirmedPage(String link, String message){
        return "<p> " + message + ". Please click on the below link to Login: </p>" +
                "<blockquote><p> <a href=\"" + link + "\">Login</a> </p>";
    }
}
