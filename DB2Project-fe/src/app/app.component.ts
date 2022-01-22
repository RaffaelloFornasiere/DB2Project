import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {BreakpointObserver, Breakpoints} from "@angular/cdk/layout";
import {filter, map, shareReplay} from "rxjs/operators";
import {HttpClient} from "@angular/common/http";
import {NavigationEnd, Router} from "@angular/router";
import {TokenStorageService} from "./services/token-storage.service";
import {LoginComponent} from "./components/login/login.component";
import {User} from "./interfaces/user";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  isLoggedIn = false;
  showLoginBar = true;
  username?: string;
  title = 'Teleco Application';

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  pages = [
    {title: "Dashboard", link: "/user-dashboard"},
    {title: "Packages", link: "/packages"},
    {title: "Settings", link: "/user-settings"},
  ];

  setTitle(title: string) {
    this.title = title;
  }

  constructor(
    private breakpointObserver: BreakpointObserver,
    private http: HttpClient,
    private router: Router,
    private tokenStorageService: TokenStorageService,
  ) {
    this.router.events.pipe(filter(event => event instanceof NavigationEnd))
      .subscribe({
          next:event=> {
            this.showLoginBar = !(event instanceof NavigationEnd && event.url === '/home' && !this.isLoggedIn);
          }
        }
      )
    this.tokenStorageService.authenticated.subscribe(
      {
        next: value => {
          this.isLoggedIn = value;
          if (this.isLoggedIn) {
            const user: User = this.tokenStorageService.getUser();
            this.username = user.username;

            let role = user.roles[0];
            this.pages.filter(p => p.title != 'Packages')
              .forEach(p => p.link = '/'
                + role.substring('ROLE_'.length).toLocaleLowerCase()
                + '-' + p.title.toLocaleLowerCase())
          }
        }
      }
    )
  }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.username = user.username;
    }
  }


  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }

}
