package com.app.springSecurity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import javax.xml.transform.Source;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class UserDto implements Serializable, Source {

    @NotBlank(message = "First name must not be blank")
    private String firstName;
    @NotBlank(message = "Last name must not be blank")
    private String lastName;
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone number")
    private String phoneNumber;
    @NotBlank(message = " invaid Gender")
    private String  gender;
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Invalid email format")
    private String email;

    private LocalDate localDate;

    @Override
    public void setSystemId(String systemId) {

    }

    @Override
    public String getSystemId() {
        return "";
    }

    @Override
    public boolean isEmpty() {
        return Source.super.isEmpty();
    }
}
