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

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

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
        userEntity.setPassword((passwordEncoder.encode(registerDto.getPassword())).toCharArray());
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
        if (user.isPresent() && Arrays.equals(user.get().getPassword(), oldPassword.toCharArray())) {
            user.get().setPassword(passwordEncoder.encode(newPassword).toCharArray());
            userRepository.save(user.get());
            return ResponseEntity.ok("Password changed successfully");
        } if (user.isPresent() && !Arrays.equals(user.get().getPassword(), oldPassword.toCharArray())) {
            return ResponseEntity.badRequest().body("old password is incorrect");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
