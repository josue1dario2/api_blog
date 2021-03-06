package com.system.blog.controllers;

import com.system.blog.dtos.LoginDto;
import com.system.blog.dtos.RegisterDto;
import com.system.blog.entities.Role;
import com.system.blog.entities.User;
import com.system.blog.repositories.RoleRepository;
import com.system.blog.repositories.UserRepository;
import com.system.blog.security.JwtAuthResponseDto;
import com.system.blog.security.JwtTokenProvider;
import com.system.blog.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    @Autowired
    public AuthController (
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            JwtTokenProvider jwtTokenProvider
            ){
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponseDto> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // We get the jwtTokenProvider
        String token = jwtTokenProvider.generateAccessToken(authentication);

        return ResponseEntity.ok(new JwtAuthResponseDto(token));
    }
    @PostMapping("/singup")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDto dto){
        Boolean a = userRepository.existsByUsername(dto.getUsername());
        if(Boolean.TRUE.equals(a)){
            return new ResponseEntity<>("Username already exists",HttpStatus.BAD_REQUEST);
        }
        Boolean b = userRepository.existsByEmail(dto.getEmail());
        if(Boolean.TRUE.equals(b)){
            return new ResponseEntity<>("Email already exists",HttpStatus.BAD_REQUEST);
        }
        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        User user = Mapper.mapFromDto(dto,passwordEncoder,roles);

        userRepository.save(user);
        return new ResponseEntity<>("Successfully registered user",HttpStatus.OK);
    }
}
