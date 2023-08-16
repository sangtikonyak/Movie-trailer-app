import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Movie } from '../model/Movie';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FavmoviesService {

  constructor(private httpClient: HttpClient) { }
  // FAV_MOVIES = "http://localhost:3000/results";
  FAV_MOVIES="http://localhost:8085/api/v2";
  // POSTER_PATH = "https://image.tmdb.org/t/p/w500/";

  movie: any = {};

  fetchMovies() {
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem("token")
    });
    let requestOptions = { headers: httpHeaders }
    return this.httpClient.get(`${this.FAV_MOVIES}/user/movies`,requestOptions);
  }

  fetchMoviesById(movieId: any) {
    return this.httpClient.get(`${this.FAV_MOVIES}/${movieId}`);
  }

  addMovie(movie: Movie): Observable<Movie[]> {
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem("token")
    });
    let requestOptions = { headers: httpHeaders }
    return this.httpClient.post<Movie[]>(`${this.FAV_MOVIES}/user/movie`, movie,requestOptions);
  }

  deleteMovie(movieId?: any) {
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem("token")
    });
    console.log(movieId)
    let requestOptions = { headers: httpHeaders }
    return this.httpClient.delete<Movie>(`${this.FAV_MOVIES}/user/delete/${movieId}`,requestOptions);
  }
}
