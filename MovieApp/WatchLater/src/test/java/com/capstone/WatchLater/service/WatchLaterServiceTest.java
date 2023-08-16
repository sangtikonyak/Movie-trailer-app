package com.capstone.WatchLater.service;

import com.capstone.WatchLater.entity.Movie;
import com.capstone.WatchLater.entity.WatchLater;
import com.capstone.WatchLater.exception.MovieNotFoundException;
import com.capstone.WatchLater.exception.UserNotFoundException;
import com.capstone.WatchLater.repository.WatchLaterRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class WatchLaterServiceTest {

    @Mock
    private WatchLaterRepository watchLaterRepository;

    @InjectMocks
    private WatchLaterServiceImpl watchLaterService;

    private List<Movie> movieList;
    private Movie movie;

    private WatchLater watchLater,watchLater1;

    @BeforeEach
    public void setUP(){
        movie=new Movie("1","test","2017","xyz",true);
        movieList= Arrays.asList(movie);
        watchLater=new WatchLater("test@gmail.com",movieList);
        Movie movie1 = new Movie("1", "test", "2023", "xyz",true);
        List<Movie> movieList1 = Arrays.asList(movie1);
      watchLater1=new WatchLater("test@gmail.com",movieList1);
    }

    @AfterEach
    public void tearDown(){
        movie=null;
        watchLater=null;
    }
    @Test
    public void addMovieAndReturnSuccess(){
     when(watchLaterRepository.findById(any())).thenReturn(Optional.ofNullable(null));
        Optional<WatchLater> opt=watchLaterRepository.findById(watchLater.getEmail());
     when(watchLaterRepository.save(any())).thenReturn(watchLater);
        assertEquals(watchLater,watchLaterRepository.save(watchLater));
        when(watchLaterRepository.save(any())).thenReturn(watchLater1);
     assertEquals(watchLater1,watchLaterRepository.save(watchLater1));
     verify(watchLaterRepository,times(2)).save(any());
    }

    @Test
    public void getMovieListAndReturnSuccess() throws UserNotFoundException, MovieNotFoundException {
        when(watchLaterRepository.findById(any())).thenReturn(Optional.ofNullable(watchLater));
        Optional<WatchLater> opt=watchLaterRepository.findById(watchLater.getEmail());
        List<Movie> movieList1=watchLaterService.getMovies(any());
        assertEquals(movieList1.get(0).getMovieId(),watchLater.getMovieList().get(0).getMovieId());
       verify(watchLaterRepository,times(3)).findById(any());

    }
    @Test
    public void deleteMovieAndReturnFailure() throws UserNotFoundException, MovieNotFoundException {
        when(watchLaterRepository.findById(any())).thenReturn(Optional.ofNullable(watchLater));
        Optional<WatchLater> opt=watchLaterRepository.findById(watchLater.getEmail());
        assertThrows(MovieNotFoundException.class,()->watchLaterService.deleteMovie(any(), watchLater.getEmail()));
        verify(watchLaterRepository,times(3)).findById(any());
    }

}
