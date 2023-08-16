package com.example.capstone.Notification.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User with specified email and password not present")
public class UserNotFoundException extends Exception{
}
