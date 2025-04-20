package com.emazon.msuser.adapters.driving.http.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequest {
    @NotBlank(message = "Email must not be empty")
    @Email(message = "Must be a well-formed email address")
    private String email;
    @NotBlank(message = "Password must not be empty")
    private String password;
}
