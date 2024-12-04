package com.emazon.msuser.adapters.driving.http.controller;

import com.emazon.msuser.adapters.driving.http.dto.request.AuthRequest;
import com.emazon.msuser.adapters.driving.http.dto.response.AuthResponse;
import com.emazon.msuser.infraestructure.configuration.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthRestController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Login to obtain the token",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Session token",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AuthResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Wrong credentials",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest request) {
        return authenticationService.authenticate(request);
    }
}
