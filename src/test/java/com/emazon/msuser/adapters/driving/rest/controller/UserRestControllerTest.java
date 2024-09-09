package com.emazon.msuser.adapters.driving.rest.controller;

import com.emazon.msuser.adapters.driving.http.controller.UserRestController;
import com.emazon.msuser.adapters.driving.http.dto.request.UserRequest;
import com.emazon.msuser.adapters.driving.http.mapper.UserRequestMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.emazon.msuser.domain.api.IUserServicePort;
import com.emazon.msuser.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class UserRestControllerTest {
    @InjectMocks
    private UserRestController userController;

    @Mock
    private IUserServicePort userServicePort;

    @Mock UserRequestMapper userRequestMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveArticleTest() {
        UserRequest userRequest = new UserRequest("name", "lastname", "0000000000",  "+5555555555555", LocalDate.of(1,1,1), "email@email.com", "password");
        User user = new User(1L, "name", "lastname", "0000000000",  "+5555555555555", LocalDate.of(1,1,1), "email@email.com", "password", null);

        when(userRequestMapper.toUserModel(any(UserRequest.class))).thenReturn(user);
        doNothing().when(userServicePort).createAuxUser(any(User.class));
        ResponseEntity<?> response = userController.createAuxUser(userRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(userServicePort, times(1)).createAuxUser(any(User.class));
    }
}
