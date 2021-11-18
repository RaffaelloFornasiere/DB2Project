import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.scss']
})
export class UserDashboardComponent implements OnInit {
  public static title = "Dashboard"

  constructor() {
  }


  userPages = [
    {title: "Dashboard", link: "/user-dashboard"},
    {title: "Packages", link: "/packages"},
    {title: "Settings", link: "/user-settings"},
  ]

  getPages():any{
    return this.userPages;
  }

  ngOnInit(): void {

  }

}
