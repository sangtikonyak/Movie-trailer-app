import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HomeServiceService {

  constructor(private httpClient : HttpClient) { }

  MOVIE_API_PATH="https://api.themoviedb.org/3/movie";
  API_KEY="api_key=f022a14e2949c595b04240c3211017f9";
  POSTER_PATH= "https://image.tmdb.org/t/p/w500/";


  // path to get individual movie details

  
  movie:any={};

  fetchMovies(){
    return this.httpClient.get(`${this.MOVIE_API_PATH}/now_playing?${this.API_KEY}`);
  }
  fetchMoviesByPage(pageNo:any){
    return this.httpClient.get(`${this.MOVIE_API_PATH}/now_playing?${this.API_KEY}&page=${pageNo}`);
  }

  fetchMoviesById(movieId: any){
   return this.httpClient.get(`${this.MOVIE_API_PATH}/${movieId}?${this.API_KEY}`);
  }

  fetchPopularMovies(){
    return this.httpClient.get(`${this.MOVIE_API_PATH}/top_rated?${this.API_KEY}`);
  }
}
