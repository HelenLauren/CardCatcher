package br.pucpr.aulawebservice.service;

import br.pucpr.aulawebservice.dto.*;
import br.pucpr.aulawebservice.model.Role;
import br.pucpr.aulawebservice.model.User;
import br.pucpr.aulawebservice.repository.UserRepository;
import br.pucpr.aulawebservice.security.JwtUtil;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthenticationManager authManager, JwtUtil jwtUtil, 
                       UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse login(AuthRequest request) {
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
        var user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("User not found"));
        var token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());
        return new AuthResponse(token, user.getRole().name());
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByUsername(request.username()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        var role = Role.valueOf(request.role().toUpperCase());
        var user = new User(null, request.username(),
                passwordEncoder.encode(request.password()), role);
        userRepository.save(user);
        var token = jwtUtil.generateToken(user.getUsername(), role.name());
        return new AuthResponse(token, role.name());
    }
}
