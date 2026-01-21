package com.app.springSecurity.mapper;

import com.app.springSecurity.Entity.UserEntity;
import com.app.springSecurity.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(UserEntity user);


    UserEntity toEntity(UserDto dto);
}

