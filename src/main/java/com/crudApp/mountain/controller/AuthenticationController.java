package com.crudApp.mountain.controller;

import com.crudApp.mountain.domain.*;
import com.crudApp.mountain.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mountainApp/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        return authenticationService.login(loginDto);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        return authenticationService.register(registerDto);
    }

    @PutMapping(value = "/changePassword")
    public ResponseEntity<String> changePassword(@RequestParam Long userId, String oldPassword, String newPassword){
        return authenticationService.changePassword(userId, oldPassword, newPassword);
    }
}
