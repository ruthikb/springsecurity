package com.app.springSecurity.service;

import com.app.springSecurity.Entity.UserEntity;
import com.app.springSecurity.dto.UserDto;
import com.app.springSecurity.mapper.UserMapper;
import com.app.springSecurity.repositry.UserRepositry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepositry userRepositry;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private UserDto userDto;
    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        userDto = new UserDto();
        userDto.setFirstName("Kruthik");
        userDto.setLastName("S");
        userDto.setGender('M');
        userDto.setPhoneNumber("1234567890");
        userDto.setEmail("kruthik@example.com");
        userDto.setLocalDate(LocalDate.of(1995, 5, 20));

        userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setFirstName("Kruthik");
        userEntity.setLastName("S");
        userEntity.setGender('M');
        userEntity.setPhoneNumber("1234567890");
        userEntity.setEmail("kruthik@example.com");
        userEntity.setLocalDate(LocalDate.of(1995, 5, 20));
    }

    // ---------------- SAVE USER ----------------

    @Test
    void saveUser_success() {
        when(userMapper.toEntity(userDto)).thenReturn(userEntity);
        when(userRepositry.save(userEntity)).thenReturn(userEntity);

        String result = userService.saveUser(userDto);

        assertEquals("User saved successfully", result);
        verify(userMapper).toEntity(userDto);
        verify(userRepositry).save(userEntity);
    }

    @Test
    void saveUser_nullDto() {
        String result = userService.saveUser(null);

        assertEquals("UserDto is null", result);
        verifyNoInteractions(userMapper, userRepositry);
    }

    // ---------------- GET USER ----------------

    @Test
    void getUserById_found() {
        when(userRepositry.findById(1)).thenReturn(Optional.of(userEntity));
        when(userMapper.toDto(userEntity)).thenReturn(userDto);

        UserDto result = userService.getUserById(1);

        assertNotNull(result);
        assertEquals("Kruthik", result.getFirstName());
        verify(userRepositry).findById(1);
        verify(userMapper).toDto(userEntity);
    }

    @Test
    void getUserById_notFound() {
        when(userRepositry.findById(1)).thenReturn(Optional.empty());

        UserDto result = userService.getUserById(1);

        assertNull(result);
        verify(userRepositry).findById(1);
        verifyNoInteractions(userMapper);
    }

    @Test
    void getUserById_nullId() {
        UserDto result = userService.getUserById(null);

        assertNull(result);
        verifyNoInteractions(userRepositry, userMapper);
    }

    // ---------------- UPDATE USER ----------------

    @Test
    void updateUser_success() {
        when(userRepositry.findById(1)).thenReturn(Optional.of(userEntity));
        when(userMapper.toEntity(userDto)).thenReturn(userEntity);

        String result = userService.updateUser(1, userDto);

        assertEquals("User updated successfully.", result);
        verify(userRepositry).findById(1);
        verify(userRepositry).save(userEntity);
    }

    @Test
    void updateUser_notFound() {
        when(userRepositry.findById(1)).thenReturn(Optional.empty());

        String result = userService.updateUser(1, userDto);

        assertEquals("User not found.", result);
        verify(userRepositry).findById(1);
        verify(userRepositry, never()).save(any());
    }

    // ---------------- DELETE USER ----------------

    @Test
    void deleteUser_success() {
        when(userRepositry.existsById(1)).thenReturn(true);

        String result = userService.deleteUser(1);

        assertEquals("User deleted successfully.", result);
        verify(userRepositry).deleteById(1);
    }

    @Test
    void deleteUser_notFound() {
        when(userRepositry.existsById(1)).thenReturn(false);

        String result = userService.deleteUser(1);

        assertEquals("User not found.", result);
        verify(userRepositry, never()).deleteById(anyInt());
    }
}
