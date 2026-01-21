package com.app.springSecurity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class UserDto implements Serializable {

    @NotBlank(message = "First name must not be blank")
    private String firstName;
    @NotBlank(message = "Last name must not be blank")
    private String lastName;
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone number")
    private String phoneNumber;
    @NotBlank(message = " invaid Gender")
    private char gender;
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Invalid email format")
    private String email;

    private LocalDate localDate;
//    public  UserDto(Integer id, String firstName, String lastName, String phoneNumber, char gender, String email, LocalDate localDate){
//        this.id=id;
//        this.firstName=firstName;
//        this.lastName=lastName;
//        this.phoneNumber=phoneNumber;
//        this.gender=gender;
//        this.email=email;
//        this.localDate=localDate;
//    }
}
