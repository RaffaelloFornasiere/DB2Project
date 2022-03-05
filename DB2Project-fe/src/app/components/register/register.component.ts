import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {FormControl, Validators} from "@angular/forms";


/**
 * This component binds form data (username, password) from template to
 * AuthService.register() method that returns an Observable object.
 *
 * For the inputs it is used the Form Validation:
 * username: required, minLength=3, maxLength=20
 * password: required, minLength=6
 */
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

  username = new FormControl(null, [Validators.required, Validators.minLength(4)])


  constructor(private authService: AuthService) {}
  ngOnInit(): void {}


  onSubmit(): void {
    const {username, password} = this.form;
    this.authService.register(username, password)
      .subscribe({
          next: data => {
            //console.log(data)
            this.isSuccessful = true;
            this.isSignUpFailed = false;
          },
          error: error => {
            this.errorMessage = error.error;
            this.isSignUpFailed = true;
          }
        }
      );
  }

}
