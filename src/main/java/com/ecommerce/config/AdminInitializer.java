package com.ecommerce.config;

import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.findByEmail("admin@gmail.com").isEmpty()) {
            User admin = new User();

            admin.setName("Admin");
            admin.setEmail("admin@gmail.com");

            // YAHAN
            admin.setPassword(passwordEncoder.encode("admin123"));

            admin.setRole("ROLE_ADMIN");

            userRepository.save(admin);

            System.out.println("Admin created!");
        }
    }
}