package com.ecommerce.controller;

import com.ecommerce.dto.LoginDTO;
import com.ecommerce.dto.RegisterDTO;
import com.ecommerce.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "https://ecommerce-frontend-epx5gucuc-anjumpatel725-boops-projects.vercel.app")
public class AuthController {

    @Autowired
    private AuthService authService;

    // LOGIN (FIXED)
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginDTO dto) {
        return authService.login(dto);
    }

    // REGISTER (FIXED)
    @PostMapping("/register")
    public String register(@RequestBody RegisterDTO dto) {
        return authService.register(dto);
    }
}