package com.capstone.WatchLater.service;


import com.capstone.WatchLater.configuration.MovieDTO;
import com.capstone.WatchLater.entity.Movie;
import com.capstone.WatchLater.entity.WatchLater;
import com.capstone.WatchLater.exception.MovieAlreadyExistException;
import com.capstone.WatchLater.exception.MovieNotFoundException;
import com.capstone.WatchLater.exception.UserNotFoundException;
import com.capstone.WatchLater.repository.WatchLaterRepository;
import org.json.simple.JSONObject;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WatchLaterServiceImpl implements WatchLaterService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange exchange;

    @Autowired
    private WatchLaterRepository watchLaterRepository;

    @Override
    public WatchLater addMovie(Movie movie, String email) throws UserNotFoundException, MovieAlreadyExistException {
        if (watchLaterRepository.findById(email).isEmpty()) {
            List<Movie> movieList = new ArrayList<>();
            MovieDTO movieDTO = new MovieDTO();
            if(!movie.isWatched())
            {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("notWatchedMovie", movie);
                jsonObject.put("email", email);
                movieDTO.setJsonObject(jsonObject);
                rabbitTemplate.convertAndSend(exchange.getName(), "movie-routing", movieDTO);
            }
            else {
                movieList.add(movie);
            }
            WatchLater watchLater=new WatchLater(email,movieList);
            return watchLaterRepository.save(watchLater);
        }
        WatchLater watchLater = watchLaterRepository.findById(email).get();

        MovieDTO movieDTO = new MovieDTO();
            if(movie.isWatched())
            {
                if (watchLater.getMovieList() == null) {
                    watchLater.setMovieList(Arrays.asList(movie));
                } else {
                    List<Movie> movieList = watchLater.getMovieList();
                    String movieId = movie.getMovieId();
                    for(Movie m : movieList)
                    {
                        if(m.getMovieId().equals(movieId))
                        {
                            throw new MovieAlreadyExistException();
                        }
                    }
                    movieList.add(movie);
                    watchLater.setMovieList(movieList);
                }
            }
            else {
                JSONObject jsonObject = new JSONObject();
                Map<String,Object> movieMap=new HashMap<>();
                movieMap.put("email",email);
                movieMap.put("notWatchedMovie",movie);
                jsonObject.putAll(movieMap);
                movieDTO.setJsonObject(jsonObject);
                rabbitTemplate.convertAndSend(exchange.getName(), "movie-routing", movieDTO);
            }

            return watchLaterRepository.save(watchLater);

    }

        public List<Movie> deleteMovie (String movieId, String email) throws
        UserNotFoundException, MovieNotFoundException {
            if (watchLaterRepository.findById(email).isEmpty()) {
                throw new UserNotFoundException();
            }
            WatchLater watchLater = watchLaterRepository.findById(email).get();
            List<Movie> movieList = watchLater.getMovieList();
            if (movieList.removeIf(movie -> movie.getMovieId().equals(movieId))) {
                watchLater.setMovieList(movieList);
            } else {
                throw new MovieNotFoundException();
            }
            return watchLaterRepository.save(watchLater).getMovieList();
        }

        @Override
        public List<Movie> getMovies (String email) throws UserNotFoundException, MovieNotFoundException {
            if (watchLaterRepository.findById(email).isEmpty()) {
                throw new MovieNotFoundException();
            }
            List<Movie> movieList = watchLaterRepository.findById(email).get().getMovieList();
            return movieList;
        }
    }

