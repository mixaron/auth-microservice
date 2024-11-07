package ru.mixaron.authservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String mail;
    private String password;
}
