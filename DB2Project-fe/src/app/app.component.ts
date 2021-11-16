import {Component, OnInit} from '@angular/core';
import {finalize, Observable} from "rxjs";
import {BreakpointObserver, Breakpoints} from "@angular/cdk/layout";
import {map, shareReplay} from "rxjs/operators";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {TokenStorageService} from "./services/token-storage.service";
import {LoginComponent} from "./components/login/login.component";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  private roles: string[] = [];
  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  showUserBoard = false;
  username?: string;
  title = 'DB2Project';

  constructor(
    //private app: AppService,
    private http: HttpClient,
    private router: Router,
    private tokenStorageService: TokenStorageService,
   ) {
    this.tokenStorageService.isAuthenticated.subscribe(
      {next: value => {
          this.isLoggedIn = value;
          console.log(value)
          if(this.isLoggedIn) {
            const user = this.tokenStorageService.getUser();
            console.log(user);
            this.username = user.username;
            this.roles = user.roles;
          }
        }}
    )
  }

  ngOnInit(): void {

    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.username = user.username;
      this.roles = user.roles;

      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.showModeratorBoard = this.roles.includes('ROLE_MODERATOR');
      this.showUserBoard = this.roles.includes('ROLE_USER');

    }
  }

  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }

}
