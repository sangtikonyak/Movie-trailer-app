package com.capstone.UserAuthenticationService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "invalid email or password")
public class InvalidCredentialException extends Exception{
}
