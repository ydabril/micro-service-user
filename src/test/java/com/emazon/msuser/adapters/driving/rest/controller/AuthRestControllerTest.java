package com.emazon.msuser.adapters.driving.rest.controller;

import com.emazon.msuser.adapters.driving.http.controller.AuthRestController;
import com.emazon.msuser.adapters.driving.http.dto.request.AuthRequest;
import com.emazon.msuser.adapters.driving.http.dto.response.AuthResponse;
import com.emazon.msuser.infraestructure.configuration.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class AuthRestControllerTest {
    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthRestController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void authenticateUser_SuccessfulAuthentication_ReturnsJwtToken() {
        // Arrange
        AuthRequest authRequest = new AuthRequest("admin123@ejemplo.com", "password123");
        AuthResponse authResponse = new AuthResponse("mocked-jwt-token");

        when(authenticationService.authenticate(any(AuthRequest.class))).thenReturn(ResponseEntity.ok(authResponse));

        // Act
        ResponseEntity<AuthResponse> response = authController.authenticate(authRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("mocked-jwt-token", response.getBody().getToken());
        verify(authenticationService, times(1)).authenticate(any(AuthRequest.class));
    }

    @Test
    void authenticateUser_InvalidCredentials_ReturnsUnauthorized() {
        // Arrange
        AuthRequest authRequest = new AuthRequest("invalid@ejemplo.com", "wrongpassword");

        when(authenticationService.authenticate(any(AuthRequest.class)))
                .thenReturn(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse("Credenciales inválidas")));

        // Act
        ResponseEntity<AuthResponse> response = authController.authenticate(authRequest);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Credenciales inválidas", response.getBody().getToken());
        verify(authenticationService, times(1)).authenticate(any(AuthRequest.class));
    }

}
