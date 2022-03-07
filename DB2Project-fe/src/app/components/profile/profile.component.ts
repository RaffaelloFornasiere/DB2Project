import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "../../services/token-storage.service";
import {User} from "../../interfaces/user";
import {
  AbstractControl, AsyncValidatorFn,
  FormControl,
  FormGroup,
  ValidatorFn,
  Validators
} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {Observable, of} from "rxjs";
import {map} from "rxjs/operators";
import {MatSnackBar} from '@angular/material/snack-bar';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  user!: User;


  constructor(private token: TokenStorageService,
              private authService: AuthService,
              public snackBar: MatSnackBar
  ) {
  }

  registerFormGroup = new FormGroup({});

  ngOnInit() {
    this.user = this.token.getUser()!;
    this.registerFormGroup = new FormGroup(
      {
        name: new FormControl(this.user.name, [Validators.required, Validators.minLength(4)]),
        surname: new FormControl(this.user.surname, [Validators.required, Validators.minLength(4)]),
        username: new FormControl(this.user.username, [Validators.required], this.usernameValidatorAsync()),
        password: new FormControl(null, [ Validators.minLength(6)]),
        password2: new FormControl(null, [this.password2Validator()])

      }
    )
  }


  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 2000,
    });
  }

  submit() {
    let user: any = {
      name: this.registerFormGroup.get('name')?.value,
      surname: this.registerFormGroup.get('surname')?.value,
      username: this.registerFormGroup.get('username')?.value,
    }
    if(!this.isVoid(this.registerFormGroup.get('password')?.value))
      user.password = this.registerFormGroup.get('password')?.value;

    this.authService.editUser(user).subscribe({
        next: data => {
          this.user = data;
          console.log(data)
          this.openSnackBar("User edited", "close")


        },
        error: err => {
          console.log(err)
          this.openSnackBar("Error: "+ err, "close")
        }
      }
    )
  }

  isVoid(value: any):boolean{
    if(value === undefined || value === null || value === "" )
      return true;
    return false;
  }

  cancel() {
    window.location.reload();
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
