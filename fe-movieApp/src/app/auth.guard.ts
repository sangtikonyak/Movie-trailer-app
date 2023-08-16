import { ActivatedRouteSnapshot, CanActivate, CanActivateFn, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthServiceService } from './services/auth-service.service';
import { MatDialog } from '@angular/material/dialog';
import { LoginComponent } from './login/login.component';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class authGuard implements CanActivate{
  constructor(private authService : AuthServiceService, private router : Router){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if(this.authService.loginStatus)
    {
      return true;
    }
    else{
  // alert('login to view')
  this.router.navigateByUrl("/home")
    return false;
  }
  
  }
};
