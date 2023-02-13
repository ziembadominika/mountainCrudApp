package com.crudApp.mountain.service;

import com.crudApp.mountain.domain.MountainDto;
import com.crudApp.mountain.domain.UserEntity;
import com.crudApp.mountain.domain.UserDto;
import com.crudApp.mountain.exception.UserNotFoundByGivenIdException;
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

    public UserDto findUserByUserNameContaining(String name){
        return userMapper.mapToUserDto(userRepository.findByUserName(name));
    }

    public void createUser(UserDto userDto) {
        UserEntity userEntity = userMapper.mapToUser(userDto);
        userRepository.save(userEntity);
    }

    public UserDto updateUser(UserDto userDto){
        UserEntity userEntity = userMapper.mapToUser(userDto);
        userRepository.save(userEntity);
        return userMapper.mapToUserDto(userEntity);
    }

    public void deleteUser(final Long id){
        userRepository.deleteById(id);
    }

    public List<MountainDto> getUserMountains(Long userId){
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(UserNotFoundByGivenIdException::new);
        return mountainMapper.mapToMountainDtoList(userEntity.getMountains());
    }
}
