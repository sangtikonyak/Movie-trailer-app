package com.capstone.WatchLater.repository;

import com.capstone.WatchLater.entity.Movie;
import com.capstone.WatchLater.entity.WatchLater;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@DataMongoTest
public class WatchLaterRepositoryTest {
    @Autowired
    private WatchLaterRepository watchLaterRepository;
    public Movie movie;
    private WatchLater watchLater;
    @BeforeEach
    public void setUP(){
        movie=new Movie("1","test","2017","xyz",true);
        List<Movie> movieList = Arrays.asList(movie);
        watchLater=new WatchLater("test@gmail.com", movieList);
    }

    @AfterEach
    public void tearDown(){
        movie=null;
        watchLater=null;
        watchLaterRepository.deleteAll();
    }

    @Test
    public void saveMovieAndReturnSuccess(){
       watchLaterRepository.insert(watchLater);
       WatchLater watchLater1=watchLaterRepository.findById(watchLater.getEmail()).get();
       assertNotNull(watchLater1);
       assertEquals(watchLater.getEmail(),watchLater1.getEmail());
    }

    @Test
    public void getMovieListAndReturnSuccess(){
        watchLaterRepository.insert(watchLater);
        WatchLater watchLater1=watchLaterRepository.findById(watchLater.getEmail()).get();
        assertNotNull(watchLater1);
        assertEquals(watchLater.getMovieList().get(0).getMovieId(),watchLater1.getMovieList().get(0).getMovieId());
    }

}
