import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {TokenStorageService} from "../../services/token-storage.service";
import {NavbarService} from "../../services/navbar.service";
import {ActivatedRoute, Router} from "@angular/router";


/**
 * Login Component also uses AuthService to work with Observable object. Besides, it calls
 * TokenStorageService methods to check loggedIn status and save Token, User info to Session Storage.
 *
 * it also manages the status bar for the login
 */
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
  returnUrl!: string;
  returnData: any;

  constructor(private authService: AuthService,
              private route: ActivatedRoute,
              private router: Router,
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
      this.role = this.tokenStorage.getUser()!.roles[0];

    }
    console.log("logging");
    this.needsToBeLogged = this.navbarService.isLoggingInWarnVisible;
    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    this.returnData = this.route.snapshot.queryParams['data'];
  }

  onSubmit(): void {
    const {username, password} = this.form;
    this.authService.login(username, password).subscribe({
      next: data => {
        console.log("login done: ", data)
        this.tokenStorage.saveUser(data);
        this.tokenStorage.saveToken(data.token);

        this.isLoginFailed = false;
        this.isLogged = true;
        this.role = this.tokenStorage.getUser()!.roles[0];
        this.router.navigate([this.returnUrl], {queryParams: {data: this.returnData}})
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
