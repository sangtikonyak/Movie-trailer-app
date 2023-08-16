import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  constructor() { };
  loginStatus:boolean = false;

  login(){
    this.loginStatus = true;
  }

  logout(){
    this.loginStatus = false;
  }
}
