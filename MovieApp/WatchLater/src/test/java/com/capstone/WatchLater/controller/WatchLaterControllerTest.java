package com.capstone.WatchLater.controller;

import com.capstone.WatchLater.entity.Movie;
import com.capstone.WatchLater.entity.WatchLater;
import com.capstone.WatchLater.exception.MovieNotFoundException;
import com.capstone.WatchLater.service.WatchLaterServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class WatchLaterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private WatchLaterServiceImpl watchLaterService;

    @InjectMocks
    private WatchLaterController watchLaterController;

    private List<Movie> movieList;
    private Movie movie;

    private WatchLater watchLater;

    @BeforeEach
    public void setUP(){
        movie=new Movie("1","test","2023","test",true);
        movieList= Arrays.asList(movie);
        watchLater=new WatchLater("test@gmail.com",movieList);
        mockMvc= MockMvcBuilders.standaloneSetup(watchLaterController).build();
    }

    @AfterEach
    public void tearDown(){
        movie=null;
        watchLater=null;
    }

    @Test
    public void deleteMovieAndReturnFailure() throws Exception {
    when(watchLaterService.deleteMovie(any(),any())).thenThrow(MovieNotFoundException.class);
    mockMvc.perform(MockMvcRequestBuilders.delete("/movieApp/delete/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
        verify(watchLaterService,times(1)).deleteMovie(anyString(),anyString());

    }
    @Test
    public void deleteMovieAndReturnSuccess() throws Exception {
        when(watchLaterService.deleteMovie(any(),any())).thenReturn(movieList);
        mockMvc.perform(MockMvcRequestBuilders.delete("/movieApp/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(watchLaterService,times(1)).deleteMovie(anyString(),anyString());

    }
    private static String jsonToString(final Object ob) throws JsonProcessingException {
        String result;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(ob);
            result = jsonContent;
        } catch(JsonProcessingException e) {
            result = "JSON processing error";
        }

        return result;
    }
}
