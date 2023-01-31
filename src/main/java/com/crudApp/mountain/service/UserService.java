package com.crudApp.mountain.service;

import com.crudApp.mountain.domain.MountainDto;
import com.crudApp.mountain.domain.User;
import com.crudApp.mountain.domain.UserDto;
import com.crudApp.mountain.mapper.MountainMapper;
import com.crudApp.mountain.mapper.UserMapper;
import com.crudApp.mountain.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Data
public class UserService {

    private UserRepository userRepository;

    private UserMapper userMapper;

    private User user;

    private MountainMapper mountainMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDto> getAllUsers(){
        return userMapper.mapToUserDtoList(userRepository.findAll());
    }

    public UserDto getUser(final Long id){
        return userMapper.mapToUserDto(userRepository.getReferenceById(id));
    }

    public List<UserDto> findUseByNameContaining(String name){
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

    public Set<MountainDto> getUserMountains(Long userId){
        User user = userRepository.getReferenceById(userId);
        return mountainMapper.mapToMountainDtoSet(user.getMountains());
    }
}
