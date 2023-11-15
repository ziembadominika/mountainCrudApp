package com.crudApp.mountain.service;

import com.crudApp.mountain.domain.AuthenticationResponseDto;
import com.crudApp.mountain.domain.LoginDto;
import com.crudApp.mountain.domain.RegisterDto;
import com.crudApp.mountain.repository.RoleRepository;
import com.crudApp.mountain.repository.UserRepository;
import com.crudApp.mountain.security.JwtGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AuthenticationServiceTest {

    @InjectMocks
    private AuthenticationService authenticationService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtGenerator jwtGenerator;
    @Mock
    private AuthenticationManager authenticationManager;
    private RegisterDto userOne;
    private RegisterDto userTwo;
    private LoginDto userOneLoginDetails;

    @Before
    public void setUp(){
        userOne = new RegisterDto("UserOne", "Password", "John", "Smith", "jsmith@email.com");
        userTwo = new RegisterDto("UserOne", "password", "Taylor", "Jones", "taylor@email.com");
        userOneLoginDetails = new LoginDto("UserOne", "Password".toCharArray());
    }

    @Test
    public void shouldRegister() {
        //Given
        when(userRepository.existsByUserName("UserOne")).thenReturn(false);
        //When
        ResponseEntity<String> userOneMessage = authenticationService.register(userOne);
        //Then
        assertEquals(userOneMessage.getBody(), "User registered successfully");
    }

    @Test
    public void shouldNotRegister(){
        //Given
        when(userRepository.existsByUserName("UserOne")).thenReturn(true);
        //When
        ResponseEntity<String> userTwoMessage = authenticationService.register(userTwo);
        //Then
        assertEquals(userTwoMessage.getBody(), "Username is already taken");
    }
    @Test
    public void login() {
        //Given&When
        ResponseEntity<AuthenticationResponseDto> response = authenticationService.login(userOneLoginDetails);
        //Then
        assertEquals(String.valueOf(response.getStatusCode()), "200 OK");
    }
}