package com.capstone.FavouriteMovieService.service;

import com.capstone.FavouriteMovieService.exception.MovieAlreadyExistException;
import com.capstone.FavouriteMovieService.exception.MovieNotFoundException;
import com.capstone.FavouriteMovieService.exception.UserNotFoundException;
import com.capstone.FavouriteMovieService.model.Movie;
import com.capstone.FavouriteMovieService.model.User;


import java.util.List;

public interface FavouriteMovieService {
    public User saveMovie(Movie movie, String email) throws UserNotFoundException, MovieAlreadyExistException;

    public List<Movie> deleteMovie(String movieId, String email) throws UserNotFoundException, MovieNotFoundException;

    public List<Movie> getAllMovies(String email) throws UserNotFoundException;

    public User addUser(User user);
}
