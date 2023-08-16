package com.example.capstone.Notification.service;

import com.example.capstone.Notification.configuration.MovieDTO;
import com.example.capstone.Notification.exception.MovieNotFoundException;
import com.example.capstone.Notification.exception.UserNotFoundException;
import com.example.capstone.Notification.model.Movie;

import java.util.List;

public interface NotificationService {
    public List<Movie> deleteMovie(String movieId, String email) throws UserNotFoundException, MovieNotFoundException;

    public List<Movie> getAllMovies(String email) throws UserNotFoundException;

    void saveMovie(MovieDTO movieDTO);
}
