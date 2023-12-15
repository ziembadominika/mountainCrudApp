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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
    @Mock
    private JavaMailSender javaMailSender;
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
                .email("susan97@gmail.com").password("oldPassword").build();
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
    public void shouldChangePassword() {
        //Given
        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntityOne));
        when(passwordEncoder.matches("oldPassword", userEntityOne.getPassword())).thenReturn(true);
        //When
        ResponseEntity<String> response = authenticationService.changePassword(1L, "oldPassword", "newPassword");
        //Then
        assertEquals(response.getBody(), "Password changed successfully");
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void shouldNotChangePasswordIncorrectUserId() {
        //Given
        when(userRepository.findById(3L)).thenReturn(Optional.empty());
        //When
        ResponseEntity<String> response = authenticationService.changePassword(3L, "oldPassword", "newPassword");
        //Then
        assertEquals(response.getBody(), "User not found by given id");
    }

    @Test
    public void shouldNotChangePasswordIncorrectOldPassword() {
        //Given
        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntityOne));
        when(passwordEncoder.matches("Password", userEntityOne.getPassword())).thenReturn(false);
        //When
        ResponseEntity<String> response = authenticationService.changePassword(1L, "Password", "newPassword");
        //Then
        assertEquals(response.getBody(), "Old password is incorrect");
        assertEquals(response.getStatusCodeValue(), 400);
        verify(userRepository, never()).save(userEntityOne);

    }

    @Test
    public void shouldGenerateNewPassword() {
        //Given&When
        String generatedPassword = authenticationService.generateNewPassword();
        //Then
        assertEquals(10, generatedPassword.length());
        assertTrue(generatedPassword.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$"));
    }

    @Test
    public void shouldGenerateDifferentPasswords() {
        //Given&When
        String firstGeneratedPassword = authenticationService.generateNewPassword();
        String secondGeneratedPassword = authenticationService.generateNewPassword();
        //Then
        assertNotEquals(firstGeneratedPassword, secondGeneratedPassword);
    }

    @Test
    public void shouldGenerateUniquePassword() {
        //Given
        Set<String> generatedPasswords = new HashSet<>();
        int numberOfPasswordsToGenerate = 1000;
        //When&Then
        while(numberOfPasswordsToGenerate > 0) {
            String newPassword = authenticationService.generateNewPassword();
            assertFalse(generatedPasswords.contains(newPassword));
            generatedPasswords.add(newPassword);
            numberOfPasswordsToGenerate--;
        }
    }
}