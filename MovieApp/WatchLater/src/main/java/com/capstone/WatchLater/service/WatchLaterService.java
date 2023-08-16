package com.capstone.WatchLater.service;



import com.capstone.WatchLater.entity.Movie;
import com.capstone.WatchLater.entity.WatchLater;
import com.capstone.WatchLater.exception.MovieAlreadyExistException;
import com.capstone.WatchLater.exception.MovieNotFoundException;
import com.capstone.WatchLater.exception.UserNotFoundException;

import java.util.List;

public interface WatchLaterService {
    public WatchLater addMovie(Movie movie, String email) throws UserNotFoundException, MovieAlreadyExistException;

    public List<Movie> deleteMovie(String movieId,String email) throws UserNotFoundException, MovieNotFoundException;

    public List<Movie> getMovies(String email) throws UserNotFoundException, MovieNotFoundException;

}
