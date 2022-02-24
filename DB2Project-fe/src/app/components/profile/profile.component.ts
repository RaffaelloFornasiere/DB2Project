import { Component, OnInit } from '@angular/core';
import { TokenStorageService} from "../../services/token-storage.service";
import {User} from "../../interfaces/user";
import {FormControl, FormGroupDirective, NgForm, Validators} from "@angular/forms";
import {ErrorStateMatcher} from "@angular/material/core";




/** Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}


/**
 * This Component gets current User from Storage using TokenStorageService and show information
 * and allows user to edit them
 * (username, password, roles).
 */



@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  user: User | undefined;
  roles= [
    {key: "ROLE_USER", value: "User"},
    {key: "ROLE_ADMIN", value: "Admin"}
  ];
  rolesForm = new FormControl();
  emailFormControl = new FormControl('', [Validators.required, Validators.email]);
  matcher = new MyErrorStateMatcher();


  submitted= false;
  constructor(private token: TokenStorageService) {
  }
  ngOnInit(): void {
    this.user = this.token.getUser();
  }
  onSubmit() {
    this.submitted = true;
  }
}
