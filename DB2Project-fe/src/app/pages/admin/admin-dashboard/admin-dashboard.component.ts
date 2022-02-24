import {Component, OnDestroy, OnInit} from '@angular/core';
import {Color, Label} from "ng2-charts";
import {ChartType} from "chart.js";
import {OptionalPackage} from "../../../interfaces/OptionalPackage";
import {User} from "../../../interfaces/user";
import {Package} from "../../../interfaces/package";
import {ValidityPeriod} from "../../../interfaces/ValidityPeriod";
import {Order} from "../../../interfaces/Order";


@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.scss']
})
export class AdminDashboardComponent implements OnInit, OnDestroy {
  public static title = "Dashboard"

  public averageOptionalPackages !: OptionalPackage[];
  public bestOptionalPackage !: OptionalPackage;

  public totalPurchases !: {package: Package, purchases: number}[];
  public totalPurchasesVP !: {package: Package, validityPeriod: ValidityPeriod, purchases: number}[];

  public suspendedOrders !: Order[];
  public insolventUsers !: User[];
  public alerts !: {id: number, username: string}[];




  constructor() {
  }

  adminPages = [
    {title: "Dashboard", link: "/user-dashboard"},
    {title: "Packages", link: "/packages"},
    {title: "Settings", link: "/user-settings"},
  ]

  ngOnInit(): void {

  }

  getPages(): any {
    return this.adminPages;
  }

  ngOnDestroy(): void {

  }


}
