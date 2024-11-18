package org.myreview.Domain.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter @Getter
public class RegisterRequest {

    private String username;
    private String email;
    private String password;
    private String confirmPassword;
}