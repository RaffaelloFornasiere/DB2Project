import {Component, OnInit, ViewChild} from '@angular/core';
import {Observable} from "rxjs";
import {BreakpointObserver, Breakpoints} from "@angular/cdk/layout";
import {filter, map, shareReplay} from "rxjs/operators";
import {HttpClient} from "@angular/common/http";
import {NavigationEnd, Router} from "@angular/router";
import {TokenStorageService} from "./services/token-storage.service";
import {LoginComponent} from "./components/login/login.component";
import {User} from "./interfaces/user";
import {MatSidenav} from "@angular/material/sidenav";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  @ViewChild('drawer')
  public sidenav!: MatSidenav;

  isLoggedIn = false;
  showLoginBar = true;
  username?: string;
  title = 'Teleco Application';
  role!: String;

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  pages = [
    {title: "Dashboard", link: "/dashboard"},
    {title: "Packages", link: "/packages"},
    {title: "Settings", link: "/settings"},
    {title: "Profile", link: "/profile"},
  ];

  navElements !: { title: string, link: string }[];

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
          next: event => {
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
            this.role = user.roles[0];

            this.navElements = this.pages.filter(p => p.title != 'Packages')
              .map((p) => {
                  return {
                    title: p.title,
                    link:
                      '/'
                      + this.role.substring('ROLE_'.length).toLocaleLowerCase()
                      + '-'
                      + p.title.toLocaleLowerCase()
                  }
                }
              );
            this.navElements.push({title: "Packages", link: "/packages"})
          } else {
            console.log("ciaooooo")
            this.navElements = [{title: "Packages", link: "/packages"}];
          }
        }
      }
    )
  }

  ngOnInit(): void {

    this.isLoggedIn = !!this.tokenStorageService.getToken();
    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      console.log("user: ", user)
      this.username = user.username;
      this.role = user.roles[0];
    }
  }


  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }

}
