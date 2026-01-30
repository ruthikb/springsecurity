package com.app.springSecurity.service;

import com.app.springSecurity.dto.UserDto;
import org.jspecify.annotations.Nullable;

public interface UserService {
    String saveUser(UserDto userDto);

    UserDto getUserById(Integer id);

    String updateUser(Integer id, UserDto userDto);

     String deleteUser(Integer id);

}
