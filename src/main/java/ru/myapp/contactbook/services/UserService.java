package ru.myapp.contactbook.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.myapp.contactbook.models.User;
import ru.myapp.contactbook.models.UserDto;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<User> signUp(UserDto entity);
    Optional<User> signIn(String nickname, String password);
    Optional<User> save(User entity);
}
