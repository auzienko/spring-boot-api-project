package ru.myapp.contactbook.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.myapp.contactbook.models.Role;
import ru.myapp.contactbook.models.User;
import ru.myapp.contactbook.models.UserDto;
import ru.myapp.contactbook.repositories.UserRepository;
import ru.myapp.contactbook.security.AppUserPrincipal;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder bCryptEncoder;

    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder bCryptEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.bCryptEncoder = bCryptEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByNickname(nickname);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(nickname);
        }
        return new AppUserPrincipal(user.get());
    }

    @Override
    public Optional<User> signUp(UserDto entity) {
        Optional<User> tmp = userRepository.findByNickname(entity.getNickname());
        if (!tmp.isPresent()) {
            userRepository.save(
                    new User(Role.USER,
                            entity.getNickname(),
                            bCryptEncoder.encode(entity.getPassword()))
            );
            return userRepository.findByNickname(entity.getNickname());
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> signIn(String nickname, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                nickname, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);


        return Optional.empty();
    }

    @Override
    public Optional<User> save(User entity) {
        User user = userRepository.save(entity);
        return Optional.of(user);
    }
}
