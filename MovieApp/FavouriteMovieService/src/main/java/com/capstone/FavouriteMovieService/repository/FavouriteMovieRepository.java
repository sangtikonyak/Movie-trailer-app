package com.capstone.FavouriteMovieService.repository;

import com.capstone.FavouriteMovieService.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FavouriteMovieRepository extends MongoRepository<User,String> {
    User findByEmail(String email);
}
