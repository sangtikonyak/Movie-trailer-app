import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserServiceService } from "../services/user-service.service";
import { USER } from "../model/signup";
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { LoginComponent } from '../login/login.component';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

  constructor(private router: Router, private userService: UserServiceService,
    private fb: FormBuilder, private dialog: MatDialog) { }

  signUp: USER = {};
  signUpForm = this.fb.group(
    {
      userName: ['', [Validators.required]],
      mobileNo: ['', [Validators.required, Validators.pattern("^[0-9]{9}$")]],
      email: ['', [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$")]],
      password: ['', [Validators.required]],
      confirmPassword: ['', [Validators.required]]
    },
    { Validators: [this.validatePassword] }
  )

  signUpUser(signUp: any) {
    console.log(signUp.value);
    console.log(this.signUpForm.value);

    this.userService.signUp(this.signUpForm.value).subscribe({
      next: response => {
        alert("signup success")
        this.dialog.closeAll()
        this.dialog.open(LoginComponent, {
          maxWidth: '530px',
          maxHeight: '380px',
          panelClass: 'bg-color'
        })
      },
      error: err => alert("unsuccessful")
    });



  }

  validatePassword(formControl: AbstractControl) {
    const password = formControl.get("password")?.value;
    const confirmPass = formControl.get("confirmPassword")?.value;
    console.log("pass:" + password, "confirm :" + confirmPass);
    if (!password || !confirmPass) {
      return null;
    }
    if (password != confirmPass) {
      console.log("not equal");
      return { mustMatch: false };
    }
    return null;
  }
  get password() {
    return this.signUpForm.get("password");
  }
  get confirmPassword() {
    return this.signUpForm.get("confirmPassword");
  }

  get userName() {
    return this.signUpForm.get("userName");
  }
  get email() {
    return this.signUpForm.get("email");
  }

  get mobileNo() {
    return this.signUpForm.get("mobileNo");
  }


}
