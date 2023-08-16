package com.example.capstone.Notification.controller;

import com.example.capstone.Notification.exception.MovieNotFoundException;
import com.example.capstone.Notification.exception.UserNotFoundException;
import com.example.capstone.Notification.model.Movie;
import com.example.capstone.Notification.service.NotificationServiceImpl;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v3")
public class NotificationController {

    @Autowired
    private NotificationServiceImpl notificationService;

    private ResponseEntity<?> responseEntity;

    @GetMapping("/user/movies")
    public ResponseEntity<?> getAllMovies(HttpServletRequest request) throws UserNotFoundException {
        try {
            System.out.println("header" +request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("email from claims :: " + claims.getSubject());
            String email = claims.getSubject();
            System.out.println("email :: "+email);
           // String email = "test@gmail.com";
            responseEntity = new ResponseEntity<>(notificationService.getAllMovies(email), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }

    @DeleteMapping("/user/delete/{movieId}")
    public ResponseEntity<?> deleteMovie(@PathVariable("movieId") String movieId,HttpServletRequest request) throws MovieNotFoundException, UserNotFoundException {
        try {
             System.out.println("header" +request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("email from claims :: " + claims.getSubject());
            String email = claims.getSubject();
            System.out.println("email :: "+email);
           // String email="test@gmail.com";
            responseEntity = new ResponseEntity<>(notificationService.deleteMovie(movieId, email), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (MovieNotFoundException e) {
            throw new MovieNotFoundException();
        }
        return responseEntity;
    }

}
