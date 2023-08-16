package com.capstone.UserAuthenticationService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "user already exists")
public class UserAlreadyExistException extends Exception{
}
