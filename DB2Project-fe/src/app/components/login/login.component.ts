import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {TokenStorageService} from "../../services/token-storage.service";
import {NavbarService} from "../../services/navbar.service";

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
  errorMessage = '';
  role: string = "";
  needsToBeLogged = false;

  constructor(private authService: AuthService,
              private tokenStorage: TokenStorageService,
              private navbarService: NavbarService) {
    this.navbarService.loggingVisibilityChange.subscribe(
      value => {
      this.needsToBeLogged = value
    })

  }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLogged = true;
      this.role = this.tokenStorage.getUser().role;
    }
    this.needsToBeLogged = this.navbarService.isLoggingInWarnVisible;
  }

  onSubmit(): void {



    const {username, password} = this.form;
    this.authService.login(username, password).subscribe({
      next: data => {
        this.tokenStorage.saveUser(data);
        this.tokenStorage.saveToken(data.token);

        this.isLoginFailed = false;
        this.isLogged = true;
        this.role = this.tokenStorage.getUser().roles[0];
      },
      error: error => {
        this.errorMessage = error.message;
        this.isLoginFailed = true;
        this.isLogged = false;
      }
    })
  }

  reloadPage(): void {
    window.location.reload();
  }

}
