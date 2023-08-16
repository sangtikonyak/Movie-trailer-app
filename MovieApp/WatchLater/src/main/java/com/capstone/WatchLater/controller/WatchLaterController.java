package com.capstone.WatchLater.controller;

import com.capstone.WatchLater.entity.Movie;
import com.capstone.WatchLater.exception.MovieAlreadyExistException;
import com.capstone.WatchLater.exception.MovieNotFoundException;
import com.capstone.WatchLater.exception.UserNotFoundException;
import com.capstone.WatchLater.service.WatchLaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/watch-later/")
public class WatchLaterController {

    @Autowired
    private WatchLaterService watchLaterService;


    @PostMapping("add-movie")
    public ResponseEntity<?> addMovie(@RequestBody Movie movie,HttpServletRequest request) throws UserNotFoundException, MovieAlreadyExistException {
        String email= (String) request.getAttribute("email");
        System.out.println("email from request :"+ email);
        return new ResponseEntity<>(watchLaterService.addMovie(movie, email), HttpStatus.CREATED);

    }
    @DeleteMapping("delete/{movieId}")
    public ResponseEntity<?> deleteMovie(@PathVariable("movieId") String movieId,HttpServletRequest request) throws UserNotFoundException, MovieNotFoundException {
        String email= (String) request.getAttribute("email");
        System.out.println("email from request :"+ email);
            return new ResponseEntity<>(watchLaterService.deleteMovie(movieId,email),HttpStatus.OK);

    }

    @GetMapping("movies")
    public ResponseEntity<?> getAllMovies(HttpServletRequest request) throws UserNotFoundException, MovieNotFoundException {
        String email= (String) request.getAttribute("email");
        System.out.println("email from request :"+ email);
        return new ResponseEntity<>(watchLaterService.getMovies(email),HttpStatus.OK);
    }


}
