package org.example.car_test.service;

import org.example.car_test.dto.RegistrationRequest;
import org.example.car_test.model.User;
import org.example.car_test.repository.UserRepository;
import org.example.car_test.utils.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtility jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Map<String, String> login(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String accessToken = jwtUtil.generateToken(user.getUsername(), true);
        String refreshToken = jwtUtil.generateToken(user.getUsername(), false);

        user.setRefreshToken(refreshToken);
        userRepository.save(user);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        return tokens;
    }

    public void registerUser(RegistrationRequest registrationRequest) {
        User newUser = new User();
        newUser.setUsername(registrationRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        newUser.setRole(registrationRequest.getRole());
        userRepository.save(newUser);
    }

    public Map<String, String> refreshToken(String refreshToken) {
        if (!jwtUtil.validateToken(refreshToken)) {
            throw new RuntimeException("Invalid or expired refresh token");
        }

        User user = userRepository.findByRefreshToken(refreshToken);
        if (user == null) {
            throw new RuntimeException("Refresh token not associated with any user");
        }

        String accessToken = jwtUtil.generateToken(user.getUsername(), true);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);

        return tokens;
    }

}

