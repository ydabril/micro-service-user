package com.emazon.msuser.adapters.driving.http.controller;

import com.emazon.msuser.adapters.driving.http.dto.request.UserRequest;
import com.emazon.msuser.adapters.driving.http.mapper.UserRequestMapper;
import com.emazon.msuser.domain.api.IUserServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Validated
@RequiredArgsConstructor
public class UserRestController {
    private final UserRequestMapper userRequestMapper;
    private final IUserServicePort userServicePort;


    @Operation(summary = "Crear un nuevo usuario", description = "Crea un nuevo usuario en el sistema con los detalles proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Empleado creado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserRequest.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida, error de validación",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Acceso no autorizado",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Acceso prohibido",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/create-aux-user")
    public ResponseEntity<Void> createAuxUser(@Valid @RequestBody UserRequest request) {
        userServicePort.createAuxUser(userRequestMapper.toUserModel(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Crear un nuevo usuario cliente", description = "Crea un nuevo usuario cliente en el sistema con los detalles proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserRequest.class))),
            @ApiResponse(responseCode = "400", description = "Cliente inválida, error de validación",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Acceso no autorizado",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "403", description = "Acceso prohibido",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/register-client")
    public ResponseEntity<Void> createClientUser(@Valid @RequestBody UserRequest request) {
        userServicePort.createClientUser(userRequestMapper.toUserModel(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
