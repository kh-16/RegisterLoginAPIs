package com.LoginLogoutAPI.userauthapi.controller;

import com.LoginLogoutAPI.userauthapi.dto.LoginRequest;
import com.LoginLogoutAPI.userauthapi.dto.RegisterRequest;
import com.LoginLogoutAPI.userauthapi.dto.SendCodeRequest;
import com.LoginLogoutAPI.userauthapi.entity.User;
import com.LoginLogoutAPI.userauthapi.service.AuthService;
import com.LoginLogoutAPI.userauthapi.repository.UserRepository;
import com.LoginLogoutAPI.userauthapi.service.GoogleOAuthService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final GoogleOAuthService googleOAuthService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private JavaMailSender mailSender;
    private final Map<String, String> verificationCodes = new HashMap<>();

    public AuthController(AuthService authService,
                          GoogleOAuthService googleOAuthService,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.googleOAuthService = googleOAuthService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());

        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        User user = userOpt.get();

        // Match both username and password
        if (!user.getUsername().equals(request.getUsername())) {
            return ResponseEntity.badRequest().body("Invalid username");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid password");
        }

        return ResponseEntity.ok("Login successful!");
    }

    @PostMapping("/google-signin")
    public ResponseEntity<String> googleSignIn(@RequestBody Map<String, String> request) {
        String token = request.get("idToken");
        GoogleIdToken.Payload payload = googleOAuthService.verifyToken(token);

        if (payload == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }

        String email = payload.getEmail();
        // Optionally, register or authenticate the user here using your UserRepository

        return ResponseEntity.ok("Google sign-in successful for: " + email);
    }
}
