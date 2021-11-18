import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.scss']
})
export class AdminDashboardComponent implements OnInit {
  public static title = "Dashboard"

  constructor() { }
  adminPages = [
    {title: "Dashboard", link: "/user-dashboard"},
    {title: "Packages", link: "/packages"},
    {title: "Settings", link: "/user-settings"},
  ]

  getPages():any{
    return this.adminPages;
  }
  ngOnInit(): void {

  }

}
