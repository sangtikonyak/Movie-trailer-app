package com.capstone.FavouriteMovieService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private String movieId;
    private String movieName;
    private String yearOfRelease;
    private String posterPath;
}
