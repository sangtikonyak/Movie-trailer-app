package com.capstone.UserAuthenticationService.service;

import com.capstone.UserAuthenticationService.exceptions.InvalidCredentialException;
import com.capstone.UserAuthenticationService.exceptions.UserAlreadyExistException;
import com.capstone.UserAuthenticationService.model.User;
import com.capstone.UserAuthenticationService.model.UserDTO;
import com.capstone.UserAuthenticationService.model.UserSignUp;
import com.capstone.UserAuthenticationService.proxy.UserMovieProxy;
import com.capstone.UserAuthenticationService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserServiceInterface{

    private UserRepository userRepository;

    private UserMovieProxy userMovieProxy;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMovieProxy userMovieProxy) {
        this.userRepository = userRepository;
        this.userMovieProxy = userMovieProxy;
    }

    @Override
    public User signUp(UserSignUp user) throws UserAlreadyExistException {
        if (userRepository.findById(user.getEmail()).isPresent()){
            throw new UserAlreadyExistException();
        }
        UserDTO userDTO=new UserDTO(user.getEmail());
            ResponseEntity<?> response = userMovieProxy.addUser(userDTO);
            System.out.println(response.getBody());
        User user1 = new User(user.getEmail(),user.getPassword());
        return userRepository.save(user1);
    }

    @Override
    public User login(User user) throws InvalidCredentialException {
        User userFromDb = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (userFromDb == null){
            throw new InvalidCredentialException();
        }
        return userFromDb;
    }
}
