import { Component } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import {DashboardComponent} from "../pages/dashboard/dashboard.component";

@Component({
  selector: 'app-main-nav',
  templateUrl: './main-nav.component.html',
  styleUrls: ['./main-nav.component.scss']
})
export class MainNavComponent {

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );
  pages = [
    {title: "Dashboard", link: "/dashboard"},
    {title: "Packages", link: "/packages"},
    {title: "Settings", link: "/settings"},
];
  constructor(private breakpointObserver: BreakpointObserver) {
      }

}
