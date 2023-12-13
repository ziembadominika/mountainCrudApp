package com.crudApp.mountain.service;

import com.crudApp.mountain.domain.*;
import com.crudApp.mountain.repository.RoleRepository;
import com.crudApp.mountain.repository.UserRepository;
import com.crudApp.mountain.security.JwtGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<String> register(RegisterDto registerDto) {
        if (userRepository.existsByUserName(registerDto.getUserName())) {
            return new ResponseEntity<>("Username is already taken", HttpStatus.BAD_REQUEST);
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(registerDto.getUserName());
        userEntity.setPassword((passwordEncoder.encode(registerDto.getPassword())));
        userEntity.setFirstName(registerDto.getFirstName());
        userEntity.setLastName(registerDto.getLastName());
        userEntity.setEmail(registerDto.getEmail());

        Role roles = roleRepository.findByName("User");
        userEntity.setRoles(Collections.singletonList(roles));
        userRepository.save(userEntity);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    public ResponseEntity<String> login(LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate((new UsernamePasswordAuthenticationToken
                    (loginDto.getUserName(), loginDto.getPassword())));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication);
            return new ResponseEntity<>(new AuthenticationResponseDto(token).toString(), HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Authentication error - incorrect login or password", HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity<String> changePassword(long id, String oldPassword, String newPassword) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isPresent() && passwordEncoder.matches(oldPassword, user.get().getPassword())) {
            user.get().setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user.get());
            return ResponseEntity.ok("Password changed successfully");
        } if (user.isPresent() && !passwordEncoder.matches(oldPassword, user.get().getPassword())) {
            return ResponseEntity.badRequest().body("Old password is incorrect");
        } else {
            return ResponseEntity.badRequest().body("User not found by given id");
        }
    }

    public ResponseEntity<String> forgotPassword(String userName){
        Optional<UserEntity> user = userRepository.findByUserName(userName);
        String newPassword = generateNewPassword();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    public String generateNewPassword(){
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String specialCharacters = "!@#$%^&*()-_=+[]{}|;:',.<>?";

        String allCharacters = upperCaseLetters + lowerCaseLetters + numbers + specialCharacters;

        Random random = new Random();
        StringBuilder newPassword = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            newPassword.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }

        return newPassword.toString();
    }
}
