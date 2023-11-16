package com.crudApp.mountain.service;

import com.crudApp.mountain.domain.MountainDto;
import com.crudApp.mountain.domain.UserEntity;
import com.crudApp.mountain.domain.UserEntityDto;
import com.crudApp.mountain.exception.UserNotFoundByGivenIdException;
import com.crudApp.mountain.exception.UserNotFoundByGivenUserName;
import com.crudApp.mountain.mapper.MountainMapper;
import com.crudApp.mountain.mapper.UserMapper;
import com.crudApp.mountain.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final MountainMapper mountainMapper;
    public static Pageable firstPage = PageRequest.of(2, 5, Sort.by("userName"));

    public List<UserEntityDto> getAllUsers() {
        return userMapper.mapToUserDtoList(userRepository.findAll());
    }

    public UserEntityDto getUser(Long id) {
        return userMapper.mapToUserDto(userRepository.findById(id).orElseThrow(UserNotFoundByGivenIdException::new));
    }

    public List<UserEntityDto> findUserByUserNameContaining(String name) {
        List<UserEntity> users = userRepository.findByUserNameContaining(name, firstPage)
                .orElseThrow(UserNotFoundByGivenUserName::new);

        return userMapper.mapToUserDtoList(users);
    }

    public void createUser(UserEntityDto userEntityDto) {
        UserEntity userEntity = userMapper.mapToUser(userEntityDto);
        userRepository.save(userEntity);
    }

    public UserEntityDto updateUser(UserEntityDto userEntityDto) {
        UserEntity userEntity = userMapper.mapToUser(userEntityDto);
        userRepository.save(userEntity);
        return userMapper.mapToUserDto(userEntity);
    }

    public void deleteUser(final Long id) {
        userRepository.deleteById(id);
    }

    public List<MountainDto> getUserMountains(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(UserNotFoundByGivenIdException::new);
        return mountainMapper.mapToMountainDtoList(userEntity.getMountains());
    }
}
