import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {
  // userBaseUrl: string = "http://localhost:8080/api/v1";

  constructor(private httpClient: HttpClient) { }
  login(loginData: any) {
    return this.httpClient.post('http://localhost:8080/api/v1/login', loginData);
  }
  
  signUp(userSignUp: any) {
    return this.httpClient.post('http://localhost:8080/api/v1/signup', userSignUp);
  }
}
