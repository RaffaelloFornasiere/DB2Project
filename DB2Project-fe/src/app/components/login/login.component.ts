import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {TokenStorageService} from "../../services/token-storage.service";
import {NavbarService} from "../../services/navbar.service";
import {ActivatedRoute, Router} from "@angular/router";

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
      this.role = this.tokenStorage.getUser().role;

    }
    console.log("loggin");
    this.needsToBeLogged = this.navbarService.isLoggingInWarnVisible;
    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
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
        this.router.navigateByUrl(this.returnUrl);
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
