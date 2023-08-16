import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Movie } from '../model/Movie';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(private httpClient: HttpClient) { }
  basePath='http://localhost:8082/api/v3/';
  movie: any = {};

  fetchMovies() {
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem("token")
    });
    let requestOptions = { headers: httpHeaders }
    return this.httpClient.get(`${this.basePath}user/movies`,requestOptions);
  }

  deleteMovie(movieId?: any) {
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem("token")
    });
    console.log(movieId)
    let requestOptions = { headers: httpHeaders }
    return this.httpClient.delete<Movie>(`${this.basePath}user/delete/${movieId}`,requestOptions);
  }

}

