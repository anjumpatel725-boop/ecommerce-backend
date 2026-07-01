package com.ecommerce.service;

import com.ecommerce.Security.JwtUtil;
import com.ecommerce.dto.LoginDTO;
import com.ecommerce.dto.RegisterDTO;
import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // ================= REGISTER =================
    public String register(RegisterDTO dto) {

    String password = dto.getPassword();

    String regex =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    if (!password.matches(regex)) {
        throw new RuntimeException(
            "Password must be 8+ chars with uppercase, lowercase, number & special char"
        );
    }

    if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
        throw new RuntimeException("Email already registered");
    }

    User user = new User();
    user.setName(dto.getName());
    user.setEmail(dto.getEmail());
    user.setPassword(passwordEncoder.encode(dto.getPassword()));
    user.setRole("ROLE_USER");

    userRepository.save(user);

    return "User registered successfully";
}

    // ================= LOGIN =================
    public Map<String, Object> login(LoginDTO dto) {

    Map<String, Object> response = new HashMap<>();

    try {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

                

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
    throw new RuntimeException("Invalid password");
}

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

        response.put("status", "SUCCESS");
        response.put("token", token);
        response.put("userId", user.getId());
        response.put("name", user.getName());
        response.put("role", user.getRole());

    } catch (Exception e) {
        response.put("status", "INVALID");
    }

    return response;
}
}