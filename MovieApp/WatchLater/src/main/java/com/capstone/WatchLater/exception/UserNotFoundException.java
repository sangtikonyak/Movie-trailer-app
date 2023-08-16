package com.capstone.WatchLater.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "user not found")
public class UserNotFoundException extends Exception{
}
