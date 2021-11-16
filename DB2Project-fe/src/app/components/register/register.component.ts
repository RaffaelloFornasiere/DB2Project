import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {FormBuilder, Validators} from "@angular/forms";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  form: any = {
    username: null,
    password: null
  };
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';


  constructor(private authService: AuthService) {
  }


  ngOnInit(): void {
  }

  onSubmit(): void {
    const {username, password} = this.form;
    this.authService.register(username, password)
      .subscribe({
          next: data => {
            console.log("data: ", data);
            this.isSuccessful = true;
            this.isSignUpFailed = false;
          },
          error: error => {
            console.log("error: ", error);
            this.errorMessage = error.error;
            this.isSignUpFailed = true;
          }
        }
      );
  }

}
