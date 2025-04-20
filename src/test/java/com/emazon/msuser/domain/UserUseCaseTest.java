package com.emazon.msuser.domain;

import com.emazon.msuser.domain.api.use_case.UserUseCase;
import com.emazon.msuser.domain.exception.AgeValidationException;
import com.emazon.msuser.domain.exception.MailAlreadyExistsException;
import com.emazon.msuser.domain.exception.UserAlreadyExistsException;
import com.emazon.msuser.domain.model.Role;
import com.emazon.msuser.domain.model.User;
import com.emazon.msuser.domain.spi.UserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserUseCaseTest {
    @Mock
    private UserPersistencePort userPersistencePort;

    @InjectMocks
    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUserTest() {
        User user = new User(1L, "name", "lastname", "0000000000",  "+5555555555555", LocalDate.of(1,1,1), "email@email.com", "password", new Role(1L, "ADMIN", "admin"));

        when(userPersistencePort.getUserByEmail("email@email.com")).thenReturn(Optional.empty());
        when(userPersistencePort.getUserByDocument("0000000000")).thenReturn(Optional.empty());

        userUseCase.createAuxUser(user);
        verify(userPersistencePort).createAuxUser(any());
    }

    @Test
    void createUser_EmailAlreadyExists() {
        User user = new User(1L, "name", "lastname", "0000000000",  "+5555555555555", LocalDate.of(1,1,1), "email@email.com", "password", null);
        when(userPersistencePort.getUserByEmail("email@email.com")).thenReturn(Optional.of(user));
        when(userPersistencePort.getUserByDocument("0000000000")).thenReturn(Optional.empty());
        assertThrows(MailAlreadyExistsException.class,
                () -> userUseCase.createAuxUser(user));
        verify(userPersistencePort, times(0)).createAuxUser(any());
    }

    @Test
    void createUser_DocumentAlreadyExists() {
        User user = new User(1L, "name", "lastname", "0000000000",  "+5555555555555", LocalDate.of(1,1,1), "email@email.com", "password", null);
        when(userPersistencePort.getUserByEmail("email@email.com")).thenReturn(Optional.empty());
        when(userPersistencePort.getUserByDocument("0000000000")).thenReturn(Optional.of(user));
        assertThrows(UserAlreadyExistsException.class,
                () -> userUseCase.createAuxUser(user));
        verify(userPersistencePort, times(0)).createAuxUser(any());
    }

    @Test
    void createUser_UnderAg() {
        User user = new User(1L, "name", "lastname", "0000000000",  "+5555555555555", LocalDate.now(), "email@email.com", "password", null);
        when(userPersistencePort.getUserByEmail("email@email.com")).thenReturn(Optional.empty());
        when(userPersistencePort.getUserByDocument("0000000000")).thenReturn(Optional.empty());
        assertThrows(AgeValidationException.class,
                () -> userUseCase.createAuxUser(user));
        verify(userPersistencePort, times(0)).createAuxUser(any());
    }
}
