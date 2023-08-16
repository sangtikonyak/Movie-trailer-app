import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { LoginComponent } from '../login/login.component';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AuthServiceService } from '../services/auth-service.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit, OnChanges {

  constructor(private matDialog: MatDialog, private router: Router, private auth: AuthServiceService ) { }

  dialogRef: any;
  login: any;
  showFiller = false;

  @Input()
  getLoginStatus: any;

  ngOnChanges(): void {
    this.login = this.getLoginStatus;
  }

  ngOnInit(): void {
    this.login = this.auth.loginStatus;
  }
  openDialog() {
    this.dialogRef = this.matDialog.open(LoginComponent, {
      maxWidth: '530px',
      maxHeight: '380px',
    })
    this.afterClosed()
  }

  logout() {
    this.auth.logout();
    localStorage.removeItem("token")
    this.router.navigateByUrl("home").then(() => window.location.reload())
  }
  searchword?: string;

  @Output() searchcriteria = new EventEmitter<String>();
  searchThis() {
    this.searchcriteria.emit(this.searchword)
  }

  goToFavourite() {
    this.router.navigateByUrl("/favourites")
    if(!this.login)
    {
      this.openDialog()
    }
    
  }
  goToWatchlater() {
    this.router.navigateByUrl("/watch-later")
    if(!this.login)
    {
      this.openDialog()
    }
  }
  goToNotification() {
    this.router.navigateByUrl("/notification")
    if(!this.login)
    {
      this.openDialog()
    }

  }
  goToHome() {
    this.router.navigateByUrl("/home")
  }
  private afterClosed() {
    this.dialogRef.afterClosed().subscribe((result: any) => {
      console.log(this.auth.loginStatus)
      this.login = this.auth.loginStatus;
    })

  }

}

