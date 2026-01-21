package com.app.springSecurity.service;

import com.app.springSecurity.Entity.UserEntity;
import com.app.springSecurity.dto.UserDto;
import com.app.springSecurity.mapper.UserMapper;
import com.app.springSecurity.repositry.UserRepositry;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepositry userRepositry;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepositry userRepositry, UserMapper userMapper) {
        this.userRepositry = userRepositry;
        this.userMapper = userMapper;
    }


    @Override
    public String saveUser(UserDto userDto) {
        if (userDto == null) {
            return "UserDto is null";
        } else {
            UserEntity userEntity = userMapper.toEntity(userDto);
            System.err.println(userDto);
            userRepositry.save(userEntity);
            System.err.println(userEntity);
            return "User saved successfully";
        }
    }

    @Override
    public UserDto getUserById(Integer id) {
        if (id == null) {
            return null;
        } else {
            Optional<UserEntity> optionalUserEntity = userRepositry.findById(id);
            if (optionalUserEntity.isPresent()) {
                UserEntity userEntity = optionalUserEntity.get();
                return userMapper.toDto(userEntity);

            }
        }
        return null;
    }

    @Override
    public String updateUser(Integer id, UserDto userDto) {
        Optional<UserEntity> existingUser = userRepositry.findById(id);
        if (existingUser.isPresent()) {
            UserEntity updatedUser = userMapper.toEntity(userDto);
            updatedUser.setId(id); // Ensure the ID remains the same
            userRepositry.save(updatedUser);
            return "User updated successfully.";
        }
        return "User not found.";
    }

    @Override
    public String deleteUser(Integer id) {
        if (userRepositry.existsById(id)){
            userRepositry.deleteById(id);
            return "User deleted successfully.";
        }
        return "User not found.";
    }
}
