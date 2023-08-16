package com.capstone.WatchLater.repository;

import com.capstone.WatchLater.entity.WatchLater;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface WatchLaterRepository extends MongoRepository<WatchLater,String> {
//    @Query("{movieList: {movieId : ?0 }}")
//    public boolean deleteByMovieId(String movieId);

}
