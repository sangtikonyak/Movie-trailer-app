package com.example.capstone.Notification.service;

import com.example.capstone.Notification.configuration.MovieDTO;
import com.example.capstone.Notification.exception.MovieNotFoundException;
import com.example.capstone.Notification.exception.UserNotFoundException;
import com.example.capstone.Notification.model.Movie;
import com.example.capstone.Notification.model.User;
import com.example.capstone.Notification.repository.NotificationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public List<Movie> deleteMovie(String movieId, String email) throws UserNotFoundException, MovieNotFoundException {
        if (notificationRepository.findById(email).isEmpty()){
            throw new UserNotFoundException();
        }
        User user = notificationRepository.findById(email).get();
        List<Movie> movies = user.getMovieList();
        if (movies.removeIf(movie1 -> movie1.getMovieId().equals(movieId))){
            user.setMovieList(movies);
        }
        else {
            throw new MovieNotFoundException();
        }

        return notificationRepository.save(user).getMovieList();
    }

    @Override
    public List<Movie> getAllMovies(String email) throws UserNotFoundException {

        return notificationRepository.findById(email).get().getMovieList();
    }

    @Override
    @RabbitListener(queues = "movie.queue")
    public void saveMovie(MovieDTO movieDTO)  {
        User user=new User();
        ObjectMapper objectMapper=new ObjectMapper();
        String email=movieDTO.getJsonObject().get("email").toString();
        if(notificationRepository.findById(email).isEmpty()){
            user.setEmail(email);
            System.out.println("movie : " +movieDTO.getJsonObject().get("notWatchedMovie") );
            Movie movie=objectMapper.convertValue(movieDTO.getJsonObject().get("notWatchedMovie"), Movie.class);
            List<Movie> movieList= Arrays.asList(movie);
            user.setMovieList(movieList);
            notificationRepository.save(user);
        }
        else {
            User userFromDb=notificationRepository.findByEmail(email);
            List<Movie> movieList=userFromDb.getMovieList();
            System.out.println("movie : " +movieDTO.getJsonObject().get("notWatchedMovie") );
            Movie movie=objectMapper.convertValue(movieDTO.getJsonObject().get("notWatchedMovie"), Movie.class);
            if(!movieList.contains(movie))
            {
                movieList.add(movie);
                userFromDb.setMovieList(movieList);
            }
            notificationRepository.save(userFromDb);
        }

    }
}
