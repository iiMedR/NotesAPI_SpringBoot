package org.example.notesapi.controller;

import jakarta.validation.Valid;
import org.example.notesapi.dto.RegisterRequest;
import org.example.notesapi.dto.UserResponse;
import org.example.notesapi.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
        UserResponse user = authService.RegisterUser(request);
        return ResponseEntity.ok(user);
    }


}
