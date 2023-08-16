package com.capstone.UserAuthenticationService.service;

import com.capstone.UserAuthenticationService.exceptions.InvalidCredentialException;
import com.capstone.UserAuthenticationService.exceptions.UserAlreadyExistException;
import com.capstone.UserAuthenticationService.model.User;
import com.capstone.UserAuthenticationService.model.UserSignUp;

public interface UserServiceInterface {
    User signUp(UserSignUp user) throws UserAlreadyExistException;
    User login(User user) throws InvalidCredentialException;
}
