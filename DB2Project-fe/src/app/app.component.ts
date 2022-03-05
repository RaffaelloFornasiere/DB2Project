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
import {UserService} from "./services/user.service";


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

  isHandset$!: Observable<boolean>;

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
    private userService: UserService
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
          this.setupSideBar();
        }
      }
    )
    this.showLoginBar = true;
    this.isLoggedIn = false;
  }

  setupSideBar(){
    if (this.isLoggedIn) {
      const user: User = this.tokenStorageService.getUser()!;
      this.username = user.username;
      this.role = user.roles[0];

    }
    this.navElements = this.userService.getPages();

  }

  ngOnInit(): void {
    console.log("")
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    this.setupSideBar();
    this.isHandset$ = this.breakpointObserver.observe(Breakpoints.Handset)
      .pipe(
        map(result => result.matches),
        shareReplay()
      )

  }


  logout(): void {
    this.tokenStorageService.signOut();
    // window.location.reload();
  }

}
