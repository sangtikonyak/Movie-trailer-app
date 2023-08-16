package com.capstone.FavouriteMovieService.service;
import com.capstone.FavouriteMovieService.exception.*;
import com.capstone.FavouriteMovieService.model.Movie;
import com.capstone.FavouriteMovieService.model.User;
import com.capstone.FavouriteMovieService.repository.FavouriteMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FavouriteMovieServiceImpl implements FavouriteMovieService{
    @Autowired
    private FavouriteMovieRepository favouriteMovieRepository;
    @Override
    public User saveMovie(Movie movie, String email) throws UserNotFoundException, MovieAlreadyExistException {
        if(favouriteMovieRepository.findById(email).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = favouriteMovieRepository.findByEmail(email);
        if(user.getFabMovies() == null)
        {
            user.setFabMovies(Arrays.asList(movie));
        }
        else {
            List<Movie> movies = user.getFabMovies();
            String movieId = movie.getMovieId();
            for(Movie m : movies)
            {
                if(m.getMovieId().equals(movieId))
                {
                    throw new MovieAlreadyExistException();
                }
            }
            movies.add(movie);
            user.setFabMovies(movies);
        }
        return favouriteMovieRepository.save(user);
    }

    @Override
    public List<Movie> deleteMovie(String movieId, String email) throws UserNotFoundException, MovieNotFoundException {
        if( favouriteMovieRepository.findById(email).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user=favouriteMovieRepository.findById(email).get();
//        String movieId = movie.getMovieId();
        List<Movie> movies=user.getFabMovies();
        if(movies.removeIf(fabMovie -> fabMovie.getMovieId().equals(movieId))){
            user.setFabMovies(movies);
        }
        else {
            throw new MovieNotFoundException();
        }
        return favouriteMovieRepository.save(user).getFabMovies();
    }


    @Override
    public List<Movie> getAllMovies(String email) throws UserNotFoundException{
        return favouriteMovieRepository.findById(email).get().getFabMovies();
    }

    public User addUser(User user)
    {
        return favouriteMovieRepository.save(user);
    }

}
