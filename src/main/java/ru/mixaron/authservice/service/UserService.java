package ru.mixaron.authservice.service;

import jakarta.ws.rs.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mixaron.authservice.model.User;
import ru.mixaron.authservice.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findUser(String mail) {
        return userRepository.findByMail(mail).orElseThrow(NotFoundException::new);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
