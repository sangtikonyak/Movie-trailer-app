package com.capstone.WatchLater.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "movie not found")
public class MovieNotFoundException extends Exception{
}
