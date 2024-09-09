package com.emazon.msuser.domain.api.use_case;

import com.emazon.msuser.domain.api.IUserServicePort;
import com.emazon.msuser.domain.exception.AgeValidationException;
import com.emazon.msuser.domain.exception.MailAlreadyExistsException;
import com.emazon.msuser.domain.exception.UserAlreadyExistsException;
import com.emazon.msuser.domain.model.Role;
import com.emazon.msuser.domain.model.User;
import com.emazon.msuser.domain.spi.UserPersistencePort;
import com.emazon.msuser.domain.util.Constants;

import java.time.LocalDate;
import java.time.Period;

public class UserUseCase implements IUserServicePort {
    private final UserPersistencePort userPersistencePort;

    public UserUseCase(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }
    @Override
    public void createAuxUser(User user) {
        Role role = new Role();
        role.setId(Constants.AUX_ROLE);
        user.setRole(role);

        if (userPersistencePort.getUserByDocument(user.getDocumentNumber()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        if (userPersistencePort.getUserByEmail(user.getEmail()).isPresent()) {
            throw new MailAlreadyExistsException();
        }

        validateAge(user.getBirthdate());

        user.setPassword(userPersistencePort.getPasswordEncrypt(
                user.getPassword()
        ));
        userPersistencePort.createAuxUser(user);
    }

    private void validateAge(LocalDate birthdate) {
        if (birthdate == null || Period.between(birthdate, LocalDate.now()).getYears() < Constants.MINIMUM_AGE ) {
            throw new AgeValidationException();
        }
    }
}
