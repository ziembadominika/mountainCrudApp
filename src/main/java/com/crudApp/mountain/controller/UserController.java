package com.crudApp.mountain.controller;

import com.crudApp.mountain.domain.MountainDto;
import com.crudApp.mountain.domain.UserEntityDto;
import com.crudApp.mountain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mountainApp")
public class UserController {


    private final UserService userService;

    @GetMapping(value = "/getAllUsers")
    public List<UserEntityDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/getUser")
    public UserEntityDto getUser(@RequestParam Long id) {
        return userService.getUser(id);
    }

    @GetMapping(value = "/getUsersByUserNameContaining")
    public List<UserEntityDto> getUsersByUserNameContaining(@RequestParam String username) {
        return userService.findUserByUserNameContaining(username);
    }

    @PostMapping(value = "/createUser")
    public void createUser(@RequestBody UserEntityDto userEntityDto) {
        userService.createUser(userEntityDto);
    }

    @PutMapping(value = "/updateUser")
    public UserEntityDto updateUser(@RequestBody UserEntityDto userEntityDto) {
        return userService.updateUser(userEntityDto);
    }

    @DeleteMapping(value = "/deleteUser")
    public void deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
    }

    @GetMapping(value = "/getUserMountains")
    public List<MountainDto> getUserMountains(@RequestParam Long userId) {
        return userService.getUserMountains(userId);
    }
}
