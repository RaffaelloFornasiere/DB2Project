import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {TokenStorageService} from "../../services/token-storage.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  form: any = {
    username: null,
    password: null
  };
  isLogged = false;
  isLoginFailed = false;
  errorMessage ='';
  roles: string[] = [];

  constructor(private authService: AuthService,
              private tokenStorage: TokenStorageService,
              ) { }

  ngOnInit(): void {
    if(this.tokenStorage.getToken()){
      this.isLogged = true;
      this.roles = this.tokenStorage.getUser().roles;
    }
  }

  onSubmit(): void{
    const {username, password} = this.form;
    this.authService.login(username, password).subscribe(
      data=>{
        console.log(data);
        this.tokenStorage.saveToken(data.token);
        this.tokenStorage.saveUser(data);

        this.isLoginFailed = false;
        this.isLogged = true;
        this.roles = this.tokenStorage.getUser().roles;
      },
      error => {
        this.errorMessage = error.message;
        this.isLoginFailed = true;
      }
    )
  }

  reloadPage(): void{
    window.location.reload();
  }

}
