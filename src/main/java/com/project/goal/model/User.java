package com.project.goal.model;


import com.project.goal.dto.UserRegistrationDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    public String number;
    public String name;
    public String password;
    public String email;

    public User() {
    }

    public User(UserRegistrationDto userRegistrationDto) {
        this.number = userRegistrationDto.number;
        this.name = userRegistrationDto.name;
        this.password = userRegistrationDto.password;
        this.email = userRegistrationDto.email;
    }
}


