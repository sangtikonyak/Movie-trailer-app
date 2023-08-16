import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserServiceService } from "../services/user-service.service";
import { USER } from "../model/signup";
import { AuthServiceService } from "../services/auth-service.service";
import { MatDialog, MatDialogRef } from "@angular/material/dialog";
import { SignupComponent } from "../signup/signup.component";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  constructor(private authService: AuthServiceService, private router: Router, private userService: UserServiceService,
    private snackBar: MatSnackBar, private matDialog: MatDialog, private dialogRef: MatDialogRef<LoginComponent>) { };

  user: USER = {};
  responseInfo: any;

  onSubmit(userForm: any) {
    this.user = userForm.value;
    console.log(this.user);
    this.userService.login(this.user).subscribe({
      next: response => {
        console.log(response);
        this.responseInfo = response;
        console.log(this.responseInfo.token);
        localStorage.setItem("token", this.responseInfo.token);
        this.authService.login();
        this.dialogRef.close()
        this.snackBar.open('login successfully', 'welcome', {
          duration: 2000,
          panelClass: ['mat-toolbar', 'mat-ptimary']
        });
      },
      error: response => {
        console.log(response);
        this.authService.logout();
        this.snackBar.open('login failed', 'invalid email or password', {
          duration: 2000,
          panelClass: ['mat-toolbar', 'mat-primary']
        });
      }
    })
  }

  openSignup() {
    this.dialogRef.close();
    this.matDialog.open(SignupComponent, {
      width: '600px',
      height: '460px',
    })
  }

}
