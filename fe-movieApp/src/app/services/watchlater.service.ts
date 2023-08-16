import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Movie } from '../model/Movie';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WatchlaterService {

  constructor(private httpClient: HttpClient) { }
  basePath='http://localhost:8081/watch-later';
  movie: any = {};

  fetchMovies() {
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem("token")
    });
    let requestOptions = { headers: httpHeaders }
    return this.httpClient.get(`${this.basePath}/movies`,requestOptions);
  }

  addMovie(movie: Movie): Observable<Movie[]> {
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem("token")
    });
    let requestOptions = { headers: httpHeaders }
    return this.httpClient.post<Movie[]>(`${this.basePath}/add-movie`, movie,requestOptions);
  }

  deleteMovie(movieId?: any) {
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem("token")
    });
    console.log(movieId)
    let requestOptions = { headers: httpHeaders }
    return this.httpClient.delete<Movie>(`${this.basePath}/delete/${movieId}`,requestOptions);
  }

}
