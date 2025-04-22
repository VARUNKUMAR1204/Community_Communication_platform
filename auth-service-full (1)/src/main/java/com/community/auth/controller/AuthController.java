package com.community.auth.controller;

import com.community.auth.dto.AuthRequest;
import com.community.auth.dto.AuthResponse;
import com.community.auth.dto.LoginRequest; 
import com.community.auth.entity.User; 
import com.community.auth.repository.UserRepository;
import com.community.auth.service.JwtService; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.web.bind.annotation.*;

@RestController 
@RequestMapping("/auth") 
public class AuthController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/register")
	public String register(@RequestBody AuthRequest request) {

	    if (userRepo.existsByEmail(request.getEmail())) {
	        return "❌ User already exists with this email.";
	    }

	    // Validate role
	    String role = request.getRole();
	    if (role == null || (!role.equals("ROLE_USER") && !role.equals("ROLE_ADMIN"))) {
	        return "❌ Invalid role. Must be either ROLE_USER or ROLE_ADMIN.";
	    }

	    // Validate community access code
	    if (request.getCommunityAccessCode() == null || request.getCommunityAccessCode().isEmpty()) {
	        return "❌ Community Access Code is required for " + role;
	    }

	    // Register user/admin
	    User user = new User(
	            request.getName(),
	            request.getEmail(),
	            request.getPhoneNumber(),
	            passwordEncoder.encode(request.getPassword()),
	            role,
	            request.getCommunityAccessCode()
	    );

	    userRepo.save(user);
	    return "✅ " + role.replace("ROLE_", "") + " registered successfully.";
	}

	@PostMapping("/login")
	public AuthResponse login(@RequestBody LoginRequest loginRequest) {
	    User user = userRepo.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new RuntimeException("❌ User not found"));

	    if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
	        throw new RuntimeException("❌ Invalid credentials");
	    }

	    String token = jwtService.generateToken(user.getEmail(), user.getRole());
	    return new AuthResponse(token, user.getRole());
	}
}