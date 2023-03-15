package com.crudApp.mountain.controller;

import com.crudApp.mountain.domain.UserEntityDto;
import com.crudApp.mountain.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private UserService userService;

    private static UserEntityDto userOne;


    @BeforeAll
    public static void setUp() {
        userOne = new UserEntityDto();
        userOne.setId(1L);
        userOne.setUserName("UserOne");
        userOne.setFirstName("User");
        userOne.setLastName("One");
        userOne.setEmail("userOne@email.com");
    }

    @Test
    public void testGetRepositoryByRepositoryName() throws Exception {

        when(userService.getUser(1L)).thenReturn(userOne);
        mockMvc.perform(get("/mountainApp/getUser?id=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Id", is(1L)))
                .andExpect(jsonPath("$.userName", is("UserOne")))
                .andExpect(jsonPath("$.firstName", is("User")))
                .andExpect(jsonPath("$.lastName", is("One")))
                .andExpect(jsonPath("$.email", is("userOne@email.com")));
    }
}