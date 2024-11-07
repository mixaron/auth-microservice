package ru.mixaron.authservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mixaron.authservice.dto.UserDto;
import ru.mixaron.authservice.model.User;
import ru.mixaron.authservice.service.AuthService;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody UserDto userDto) {
        return authService.login(userDto);
    }
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return authService.register(user);
    }
}
