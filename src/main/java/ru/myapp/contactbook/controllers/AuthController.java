package ru.myapp.contactbook.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.myapp.contactbook.models.User;
import ru.myapp.contactbook.models.UserDto;
import ru.myapp.contactbook.services.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth/ver/")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("1-0-0/signin")
    public ResponseEntity<String> signIn_1_0_0(@RequestBody UserDto userDto){
        userService.signIn(userDto.getNickname(), userDto.getPassword());
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

    @PostMapping("1-0-0/signup")
    public ResponseEntity<?> signUp_1_0_0(@RequestBody UserDto userDto){
        Optional<User> user = userService.signUp(userDto);
        if(!user.isPresent()){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}
