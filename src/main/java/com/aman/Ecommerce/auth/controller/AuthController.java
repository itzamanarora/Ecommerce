package com.aman.Ecommerce.auth.controller;

import com.aman.Ecommerce.auth.dto.request.SignUpRequest;
import com.aman.Ecommerce.auth.service.AuthService;
import com.aman.Ecommerce.user.dto.response.UserResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication API", description = "Endpoints for user authentication and authorization")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        return new ResponseEntity<>(authService.saveUser(signUpRequest), HttpStatus.CREATED);
    }
}
