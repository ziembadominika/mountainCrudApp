package com.crudApp.mountain.service;

import com.crudApp.mountain.domain.LoginDto;
import com.crudApp.mountain.domain.RegisterDto;
import com.crudApp.mountain.domain.UserEntity;
import com.crudApp.mountain.repository.RoleRepository;
import com.crudApp.mountain.repository.UserRepository;
import com.crudApp.mountain.security.JwtGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

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
    private UserEntity userEntityOne;

    @Before
    public void setUp() {
        userOne = new RegisterDto("UserOne", "Password", "John", "Smith", "jsmith@email.com");
        userTwo = new RegisterDto("UserOne", "password", "Taylor", "Jones", "taylor@email.com");
        userOneLoginDetails = new LoginDto("UserOne", "oldPassword");
        userEntityOne = new UserEntity.UserEntityBuilder().id(1L).userName("user97").firstName("Susan").lastName("Jones")
                .email("susan97@gmail.com").build();
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
    public void shouldNotRegister() {
        //Given
        when(userRepository.existsByUserName("UserOne")).thenReturn(true);
        //When
        ResponseEntity<String> userTwoMessage = authenticationService.register(userTwo);
        //Then
        assertEquals(userTwoMessage.getBody(), "Username is already taken");
        assertEquals(userTwoMessage.getStatusCode(), HttpStatus.BAD_REQUEST);
        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    public void login() {
        //Given&When
        ResponseEntity<String> response = authenticationService.login(userOneLoginDetails);
        //Then
        assertEquals(String.valueOf(response.getStatusCode()), "200 OK");
    }

    @Test
    public void shouldNotLogin() {
        //Given
        LoginDto incorrectLoginDto = new LoginDto("incorrectLogin", "incorrectPassword");
        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(incorrectLoginDto.getUserName(),
                incorrectLoginDto.getPassword()))).thenThrow(new AuthenticationServiceException("Incorrect credentials"));
        //When
        ResponseEntity<String> response = authenticationService.login(incorrectLoginDto);
        //Then
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Authentication error - incorrect login or password", response.getBody());
        verify(authenticationManager, times(1)).authenticate(any());
        verifyNoMoreInteractions(authenticationManager, jwtGenerator);
    }

    @Test
    public void shouldChangePassword(){
        //Given
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(userEntityOne));
        when(passwordEncoder.matches("oldPassword", userEntityOne.getPassword())).thenReturn(true);
        //When
        ResponseEntity<String> response = authenticationService.changePassword(1L, "oldPassword", "newPassword");
        //Then
        assertEquals(response.getBody(), "Password changed successfully");
        assertEquals(userEntityOne.getPassword(), "newPassword".toCharArray());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void shouldNotChangePassword(){
        //Given
        when(userRepository.findById(3L)).thenReturn(Optional.empty());
        //When
        ResponseEntity<String> response = authenticationService.changePassword(3L, "oldPassword", "newPassword");
        //Then
        assertEquals(response.getBody(), "User not found by given id");
    }
}