import {Component, OnInit} from '@angular/core';
import { Observable} from "rxjs";
import {BreakpointObserver, Breakpoints} from "@angular/cdk/layout";
import {map, shareReplay} from "rxjs/operators";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {TokenStorageService} from "./services/token-storage.service";
import {LoginComponent} from "./components/login/login.component";
import {User} from "./interfaces/user";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  // private roles: string[] = [];
  isLoggedIn = false;
  username?: string;
  title = 'Winders';
  dashboard = "/admin-dashboard";

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  pages = [
    {title: "Dashboard", link: "/user-dashboard"},
    {title: "Packages", link: "/packages"},
    {title: "Settings", link: "/settings"},
  ];

  setTitle(title:string){
    this.title = title;
  }

  constructor(
    //private app: AppService,
    private breakpointObserver: BreakpointObserver,
    private http: HttpClient,
    private router: Router,
    private tokenStorageService: TokenStorageService,
   ) {
    this.tokenStorageService.isAuthenticated.subscribe(
      {next: value => {
          this.isLoggedIn = value;
          if(this.isLoggedIn) {
            const user: User = this.tokenStorageService.getUser();
            console.log(user);
            this.username = user.username;
            // this.roles = user.roles;
          }
        }}
    )
  }

  ngOnInit(): void {

    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.username = user.username;
      // this.roles = user.roles;

      // this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      // this.showUserBoard = this.roles.includes('ROLE_USER');

    }
  }



  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }

}
