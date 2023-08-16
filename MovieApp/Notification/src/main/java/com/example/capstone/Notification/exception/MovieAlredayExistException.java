package com.example.capstone.Notification.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Movie Already Exists")
public class MovieAlredayExistException extends Exception{
}
