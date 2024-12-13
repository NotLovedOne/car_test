package org.example.car_test.controller;

import org.example.car_test.dto.LoginRequest;
import org.example.car_test.dto.RegistrationRequest;
import org.example.car_test.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        logger.info("login request.", loginRequest);

        Map<String, String> tokens = authService.login(loginRequest.getUsername(),loginRequest.getPassword());
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        logger.info("register request.", registrationRequest);

        authService.registerUser(registrationRequest);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> refreshTokenRequest) {
        logger.info("refresh token request.", refreshTokenRequest);

        String refreshToken = refreshTokenRequest.get("refreshToken");
        Map<String, String> tokens = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(tokens);
    }

}



