import { Component, OnInit } from '@angular/core';
import { TokenStorageService} from "../../services/token-storage.service";
import {User} from "../../interfaces/user";
import {
  FormBuilder,
  FormControl,
  FormGroup,
  FormGroupDirective,
  NgForm,
  ValidationErrors,
  ValidatorFn,
  Validators
} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {getLocaleFirstDayOfWeek} from "@angular/common";



@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  user!: User;


  constructor(private token: TokenStorageService,
              private authService: AuthService,
              ) {
  }

  submit() {
    this.authService.editUser(this.user).subscribe({
      next:data => {
        console.log(data)},
      error: err => {
        console.log(err)}
    }

    )
  }

  cancel(){
    window.location.reload();
  }


  ngOnInit() {
    this.user = this.token.getUser();

  }

}
