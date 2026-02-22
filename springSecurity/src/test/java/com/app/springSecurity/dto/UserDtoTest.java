package com.app.springSecurity.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidUserDto() {
        UserDto userDto = new UserDto();
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setPhoneNumber("1234567890");
        userDto.setGender("M");
        userDto.setEmail("john@gmail.com");
        userDto.setLocalDate(LocalDate.now());

        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);

        assertTrue(violations.isEmpty(), "Expected no validation errors");
    }

    @Test
    void testFirstNameNotBlank() {
        UserDto userDto = new UserDto();
        userDto.setFirstName("");
        userDto.setLastName("Doe");
        userDto.setPhoneNumber("1234567890");
        userDto.setGender("M");
        userDto.setEmail("test@example.com");
        userDto.setLocalDate(LocalDate.now());

        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);

        assertFalse(violations.isEmpty());

        assertTrue(
                violations.stream()
                        .anyMatch(v -> v.getMessage().equals("First name must not be blank"))
        );
    }

    @Test
    void testInvalidEmailFormat() {
        UserDto userDto = new UserDto();
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setPhoneNumber("1234567890");
        userDto.setGender("M");
        userDto.setEmail("invalid-email");
        userDto.setLocalDate(LocalDate.now());

        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);

        assertFalse(violations.isEmpty());

        assertTrue(
                violations.stream()
                        .anyMatch(v -> v.getMessage().equals("Invalid email format"))
        );
    }
}
