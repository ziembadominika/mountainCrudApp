package com.crudApp.mountain.service;

import com.crudApp.mountain.domain.MountainDto;
import com.crudApp.mountain.domain.UserEntity;
import com.crudApp.mountain.domain.UserEntityDto;
import com.crudApp.mountain.exception.UserNotFoundByGivenIdException;
import com.crudApp.mountain.mapper.MountainMapper;
import com.crudApp.mountain.mapper.UserMapper;
import com.crudApp.mountain.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final MountainMapper mountainMapper;

    public List<UserEntityDto> getAllUsers(){
        return userMapper.mapToUserDtoList(userRepository.findAll());
    }

    public UserEntityDto getUser(Long id){
        return userMapper.mapToUserDto(userRepository.getReferenceById(id));
    }

    public UserEntityDto findUserByUserNameContaining(String name){
        return userMapper.mapToUserDto(userRepository.findByUserName(name));
    }

    public void createUser(UserEntityDto userEntityDto) {
        UserEntity userEntity = userMapper.mapToUser(userEntityDto);
        userRepository.save(userEntity);
    }

    public UserEntityDto updateUser(UserEntityDto userEntityDto){
        UserEntity userEntity = userMapper.mapToUser(userEntityDto);
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
