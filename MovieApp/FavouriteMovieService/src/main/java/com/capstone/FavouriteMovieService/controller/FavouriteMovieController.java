package com.capstone.FavouriteMovieService.controller;

import com.capstone.FavouriteMovieService.exception.*;

import com.capstone.FavouriteMovieService.model.Movie;
import com.capstone.FavouriteMovieService.model.User;
import com.capstone.FavouriteMovieService.service.FavouriteMovieServiceImpl;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v2")
public class FavouriteMovieController {
    @Autowired
    private FavouriteMovieServiceImpl favouriteMovieService;
    private ResponseEntity<?> responseEntity;

    @PostMapping("/add-user")
    public ResponseEntity<?> addUser(@RequestBody User user){
        user.setFabMovies(new ArrayList<>());
        return new ResponseEntity<>(favouriteMovieService.addUser(user), HttpStatus.OK);
    }

    @GetMapping("/user/movies")
    public ResponseEntity<?> getAllUserFavMoviesFromList(HttpServletRequest request) throws UserNotFoundException {
        try{
            System.out.println("header" +request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("email from claims :: " + claims.getSubject());
            String email = claims.getSubject();
            System.out.println("email :: "+email);

            responseEntity = new ResponseEntity<>(favouriteMovieService.getAllMovies(email), HttpStatus.OK);
        }catch(UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }

        return responseEntity;
    }

    @PostMapping("/user/movie")
    public ResponseEntity<?> saveFavouriteMovieToList(@RequestBody Movie movie,HttpServletRequest request) throws UserNotFoundException, MovieAlreadyExistException {
        try {

            System.out.println("header" +request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("email from claims :: " + claims.getSubject());
            String email = claims.getSubject();
            System.out.println("email :: "+email);
            responseEntity = new ResponseEntity<>(favouriteMovieService.saveMovie(movie, email), HttpStatus.CREATED);
        }
        catch (UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        catch(MovieAlreadyExistException e)
        {
            throw new MovieAlreadyExistException();
        }
        return responseEntity;
    }

    @DeleteMapping("/user/delete/{movieId}")
    public ResponseEntity<?> deleteMovie(@PathVariable("movieId") String movieId,HttpServletRequest request) throws UserNotFoundException, MovieNotFoundException {
        try {
             System.out.println("header" +request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("email from claims :: " + claims.getSubject());
            String email = claims.getSubject();
            System.out.println("email :: "+email);

            responseEntity = new ResponseEntity<>(favouriteMovieService.deleteMovie(movieId, email), HttpStatus.OK);
        }
        catch (UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        catch (MovieNotFoundException e)
        {
            throw new MovieNotFoundException();
        }
        return responseEntity;
    }
}
