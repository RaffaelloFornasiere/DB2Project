import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {AbstractControl, AsyncValidatorFn, FormControl, FormGroup, ValidatorFn, Validators} from "@angular/forms";
import {Observable, of} from "rxjs";
import {map} from "rxjs/operators";
import {F} from "@angular/cdk/keycodes";
import {User} from "../../interfaces/user";


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

  usernameFormControl = new FormControl(null, [Validators.required], this.usernameValidatorAsync())
  registerFormGroup = new FormGroup(
    {
      name: new FormControl(null, [Validators.required, Validators.minLength(4)]),
      surname: new FormControl(null, [Validators.required, Validators.minLength(4)]),
      username: new FormControl(null, [Validators.required], this.usernameValidatorAsync()),
      email: new FormControl(null, [Validators.required], this.usernameValidatorAsync()),
      password: new FormControl(null, [Validators.required, Validators.minLength(6)]),
      password2: new FormControl(null, [this.password2Validator()])

    }
  )

  constructor(private authService: AuthService) {
  }

  ngOnInit(): void {
  }


  submit(): void {
    if (this.registerFormGroup.invalid) {
      this.registerFormGroup.markAllAsTouched();
      return;
    }
    const {username, password} = this.form;
    let user = {
      username: this.registerFormGroup.get('username')?.value,
      name: this.registerFormGroup.get('name')?.value,
      surname: this.registerFormGroup.get('surname')?.value,
      email: this.registerFormGroup.get('email')?.value,
      password: this.registerFormGroup.get('password')?.value,
    }
    this.authService.register(user)
      .subscribe({
          next: data => {
            console.log(data)
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

  getErrorMessage(control: AbstractControl) {
    if (control)
      return 'Username must be more then 4 characters';
    return '';
  }


  password2Validator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
      if ((this.registerFormGroup != undefined) && (this.registerFormGroup.get('password')?.value !=
        control.value))
        return {'passwordDifferent': true};
      else
        return null;
    }
  }

  usernameValidatorAsync(): AsyncValidatorFn {
    return (control: AbstractControl): Observable<{ [key: string]: any } | null> => {
      if (control.value && control.value.length < 4)
        return of({'minLength': true});


      return this.authService.checkUsername(control.value)
        .pipe(
          map((exist: boolean) => exist ? {'alreadyExist': true} : null)
        );
    }
  }
}
