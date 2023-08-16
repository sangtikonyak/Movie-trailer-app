import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class VideoService {

  constructor(private httpClient: HttpClient) { }
  VIDEO_API_PATH="https://api.themoviedb.org/3/movie/";
  API_KEY="api_key=f022a14e2949c595b04240c3211017f9";

  getVideo(movieId:any){
 return this.httpClient.get(`${this.VIDEO_API_PATH}${movieId}/videos?${this.API_KEY}`)
  }
}
