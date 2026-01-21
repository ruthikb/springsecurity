package com.app.springSecurity.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
@Table(name = "user_entity")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private  String firstName;
    private String lastName;

    private String phoneNumber;
    private  char gender;
    private String email;
    private LocalDate localDate;
}
