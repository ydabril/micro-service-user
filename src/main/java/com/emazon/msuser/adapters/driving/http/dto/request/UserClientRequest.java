package com.emazon.msuser.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserClientRequest {
    @NotBlank(message = "First name must not be empty")
    private String firstName;

    @NotBlank(message = "Last name must not be empty")
    private String lastName;

    @NotBlank(message = "DNI must not be empty")
    @Pattern(regexp = "^\\d{9,13}$", message = "Document number can only contain numbers")
    private String documentNumber;

    @NotBlank(message = "Phone number must not be empty")
    @Pattern(regexp = "^\\+?\\d{9,12}$", message = "Wrong phone format")
    private String phoneNumber;

    @NotNull(message = "Birthdate must not be empty")
    @Past(message = "Must be a past date")
    private LocalDate birthdate;

    @NotBlank(message = "Email must not be empty")
    @Email(message = "Must be a well-formed email address")
    private String email;

    @NotBlank(message = "Password must not be empty")
    private String password;
}
