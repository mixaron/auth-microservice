package ru.mixaron.authservice.service;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mixaron.authservice.dto.UserDto;
import ru.mixaron.authservice.model.User;
import ru.mixaron.authservice.util.JwtProvider;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public String login(UserDto userDto) {
        User user = userService.findUser(userDto.getMail());
        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        return jwtProvider.generateToken(user);
    }

    public String register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return jwtProvider.generateToken(user);
    }
}
