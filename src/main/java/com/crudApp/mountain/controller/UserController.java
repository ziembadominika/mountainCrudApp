package com.crudApp.mountain.controller;

import com.crudApp.mountain.domain.User;
import com.crudApp.mountain.domain.UserDto;
import com.crudApp.mountain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mountainApp")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/getAllUsers")
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping(value = "/getUser")
    public UserDto getUser(@RequestParam Long id){
        return userService.getUser(id);
    }

    @GetMapping(value = "/getUsersByNameContaining")
    public List<UserDto> getUsersByNameContaining(@RequestParam String name){
        return userService.findUseByNameContaining(name);
    }

    @PostMapping(value = "/createUser")
    public void createUser(@RequestBody UserDto userDto){
        userService.createUser(userDto);
    }

    @PutMapping(value = "/updateUser")
    public UserDto updateUser(@RequestBody UserDto userDto){
        return userService.updateUser(userDto);
    }

    @DeleteMapping(value = "/deleteUser")
    public void deleteUser(@RequestParam Long id){
        userService.deleteUser(id);
    }
}
