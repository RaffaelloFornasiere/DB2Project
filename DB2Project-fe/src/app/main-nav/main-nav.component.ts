import {Component} from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {Observable} from 'rxjs';
import {map, shareReplay} from 'rxjs/operators';
import {NavigationExtras} from "@angular/router";

@Component({
  selector: 'app-main-nav',
  templateUrl: './main-nav.component.html',
  styleUrls: ['./main-nav.component.scss']
})
export class MainNavComponent {
  title = "Dashboard";

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

  setTitle(title:string){
    this.title = title;
  }

  constructor(private breakpointObserver: BreakpointObserver){
  }
}
