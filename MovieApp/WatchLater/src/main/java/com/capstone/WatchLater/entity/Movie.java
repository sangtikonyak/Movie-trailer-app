package com.capstone.WatchLater.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Movie {
    private String movieId;

    private String movieName;

    private String yearOfRelease;

    private String posterPath;

    private boolean watched;

}

