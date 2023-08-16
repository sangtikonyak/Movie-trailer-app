package com.example.capstone.Notification.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private String movieId;
    private String movieName;
    private String yearOfRelease;
    private String posterPath;
    private boolean watched;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return watched == movie.watched && Objects.equals(movieId, movie.movieId) && Objects.equals(movieName, movie.movieName) && Objects.equals(yearOfRelease, movie.yearOfRelease) && Objects.equals(posterPath, movie.posterPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, movieName, yearOfRelease, posterPath, watched);
    }
}
