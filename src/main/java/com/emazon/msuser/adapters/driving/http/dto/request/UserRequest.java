package com.emazon.msuser.adapters.driving.http.dto.request;


import com.emazon.msuser.adapters.driving.http.utils.ExceptionMessage;
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
public class UserRequest {
    @NotBlank(message = ExceptionMessage.FIRST_NAME_NOT_BLANK)
    private String firstName;

    @NotBlank(message = ExceptionMessage.LAST_NAME_NOT_BLANK)
    private String lastName;

    @NotBlank(message = ExceptionMessage.DNI_NOT_BLANK)
    @Pattern(regexp = ExceptionMessage.DOCUMENT_NUMBER_REGEX, message = ExceptionMessage.DNI_PATTERN)
    private String documentNumber;

    @NotBlank(message = ExceptionMessage.PHONE_NUMBER_NOT_BLANK)
    @Pattern(regexp = ExceptionMessage.PHONE_NUMBER_REGEX, message = ExceptionMessage.PHONE_NUMBER_PATTERN)
    private String phoneNumber;

    @NotNull(message = ExceptionMessage.BIRTHDATE_NOT_NULL)
    @Past(message = ExceptionMessage.BIRTHDATE_PAST)
    private LocalDate birthdate;

    @NotBlank(message = ExceptionMessage.EMAIL_NOT_BLANK)
    @Email(message = ExceptionMessage.EMAIL_PATTERN)
    private String email;

    @NotBlank(message = ExceptionMessage.PASSWORD_NOT_BLANK)
    private String password;
}
