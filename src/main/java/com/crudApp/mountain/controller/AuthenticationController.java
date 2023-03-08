package com.crudApp.mountain.controller;

import com.crudApp.mountain.domain.*;
import com.crudApp.mountain.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mountainApp/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthenticationResponseDto> login(@RequestBody LoginDto loginDto) {
        return authenticationService.login(loginDto);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
       return authenticationService.register(registerDto);
    }
}
