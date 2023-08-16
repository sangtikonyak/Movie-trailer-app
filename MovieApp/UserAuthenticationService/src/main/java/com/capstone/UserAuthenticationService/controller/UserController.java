package com.capstone.UserAuthenticationService.controller;


import com.capstone.UserAuthenticationService.exceptions.InvalidCredentialException;
import com.capstone.UserAuthenticationService.exceptions.UserAlreadyExistException;
import com.capstone.UserAuthenticationService.model.User;
import com.capstone.UserAuthenticationService.model.UserSignUp;
import com.capstone.UserAuthenticationService.security.SecurityTokenGenerator;
import com.capstone.UserAuthenticationService.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final SecurityTokenGenerator securityTokenGenerator;
    private final UserServiceInterface userServiceInterface;
    private ResponseEntity<?> responseEntity;

    @Autowired
    public UserController(SecurityTokenGenerator securityTokenGenerator, UserServiceInterface userServiceInterface) {
        this.securityTokenGenerator = securityTokenGenerator;
        this.userServiceInterface = userServiceInterface;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserSignUp userSignUp) throws UserAlreadyExistException{
        return new ResponseEntity<>(userServiceInterface.signUp(userSignUp), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) throws InvalidCredentialException{

        User userFromDb = userServiceInterface.login(user);
        System.out.println("user from db"+ userFromDb);
        if (userFromDb != null ){
            return new ResponseEntity<>(securityTokenGenerator.generateToken(userFromDb),HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Authentication failed", HttpStatus.OK);
    }
}
