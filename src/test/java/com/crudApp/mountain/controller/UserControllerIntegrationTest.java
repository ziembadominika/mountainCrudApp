package com.crudApp.mountain.controller;

import com.crudApp.mountain.domain.LoginDto;
import com.crudApp.mountain.domain.UserEntityDto;
import com.crudApp.mountain.security.JwtGenerator;
import com.crudApp.mountain.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private JwtGenerator jwtGenerator;
    private static UserEntityDto userOne;
    private static LoginDto loginDto;
    private String token = "Bearer token";

    @BeforeAll
    public static void setUp() {
        loginDto = new LoginDto("user", "password");
        userOne = new UserEntityDto();
        userOne.setId(1L);
        userOne.setUserName("UserOne");
        userOne.setFirstName("User");
        userOne.setLastName("One");
        userOne.setEmail("userOne@email.com");
    }


    @Test
    public void testGetRepositoryByRepositoryName() throws Exception {
//        when(jwtGenerator.generateToken().thenReturn(new ResponseEntity<>(new AuthenticationResponseDto(token), HttpStatus.OK));
        when(userService.getUser(1L)).thenReturn(userOne);
        mockMvc.perform(get("/mountainApp/getUser?id=1")
                .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Id", is(1L)))
                .andExpect(jsonPath("$.userName", is("UserOne")))
                .andExpect(jsonPath("$.firstName", is("User")))
                .andExpect(jsonPath("$.lastName", is("One")))
                .andExpect(jsonPath("$.email", is("userOne@email.com")));
    }
}