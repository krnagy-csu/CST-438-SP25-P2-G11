package com.example.demo.Controllers;

import com.example.demo.Config.JwtUtils;
import com.example.demo.Service.UserService;
import com.example.demo.Tables.User;
import com.example.demo.Tables.Role;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthController(
            UserService userService,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtUtils jwtUtils) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        try {
            // Authenticate user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            // User is authenticated, generate JWT
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtUtils.generateToken(userDetails);

            // Create response with token
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("username", userDetails.getUsername());

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Authentication failed: " + e.getMessage());
        }
    }

    @PostMapping("/register") //for first time users to create an account
    public ResponseEntity<String> register(@RequestParam String username, @RequestParam String password) {
        if (userService.getUserByUsername(username) != null) {
            return ResponseEntity.badRequest().body("User already exists!");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(Role.ROLE_USER);

        boolean success = userService.addUser(user);

        if (success) {
            return ResponseEntity.ok("User registered successfully!");
        } else {
            return ResponseEntity.internalServerError().body("Failed to register user!");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        org.springframework.security.core.context.SecurityContextHolder.clearContext();

        logger.info("User logged out");

        return ResponseEntity.ok("Logged out successfully. Please discard your token.");
    }

    @GetMapping("/debug/auth")
    public ResponseEntity<String> debugAuthentication(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        Authentication auth = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();

        StringBuilder debug = new StringBuilder();
        debug.append("Authorization Header: ").append(authHeader != null ? authHeader.substring(0, 20) + "..." : "null");

        debug.append("\n\nAuthentication exists: ").append(auth != null);

        if (auth != null) {
            debug.append("\nName: ").append(auth.getName());
            debug.append("\nAuthenticated: ").append(auth.isAuthenticated());
            debug.append("\nAuthorities: ").append(auth.getAuthorities());
            debug.append("\nPrincipal Type: ").append(auth.getPrincipal().getClass().getName());
        }

        return ResponseEntity.ok(debug.toString());
    }
}