package com.crudApp.mountain.service;

import com.crudApp.mountain.domain.MountainDto;
import com.crudApp.mountain.domain.User;
import com.crudApp.mountain.domain.UserDto;
import com.crudApp.mountain.exception.UserNotFoundException;
import com.crudApp.mountain.mapper.MountainMapper;
import com.crudApp.mountain.mapper.UserMapper;
import com.crudApp.mountain.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private MountainMapper mountainMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, MountainMapper mountainMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.mountainMapper = mountainMapper;
    }

    public List<UserDto> getAllUsers(){
        return userMapper.mapToUserDtoList(userRepository.findAll());
    }

    public UserDto getUser(final Long id){
        return userMapper.mapToUserDto(userRepository.getReferenceById(id));
    }

    public List<UserDto> findUserByUserNameContaining(String name){
        return userMapper.mapToUserDtoList(userRepository.findByUserNameContaining(name));
    }

    public void createUser(UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        userRepository.save(user);
    }

    public UserDto updateUser(UserDto userDto){
        User user = userMapper.mapToUser(userDto);
        userRepository.save(user);
        return userMapper.mapToUserDto(user);
    }

    public void deleteUser(final Long id){
        userRepository.deleteById(id);
    }

    public List<MountainDto> getUserMountains(Long userId){
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return mountainMapper.mapToMountainDtoList(user.getMountains());
    }
}
