import {Component, OnDestroy, OnInit} from '@angular/core';
import {OptionalPackage} from "../../../interfaces/OptionalPackage";
import {User} from "../../../interfaces/user";
import {Package} from "../../../interfaces/package";
import {ValidityPeriod} from "../../../interfaces/ValidityPeriod";
import {Order} from "../../../interfaces/Order";
import {StatService} from "../../../services/stat.service";
import {Alert} from "../../../interfaces/Alert";


@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.scss']
})
export class AdminDashboardComponent implements OnInit, OnDestroy {
  public static title = "Dashboard"

  public averageOptionalPackages !: {first: Package, second: number}[];
  public bestOptionalPackage !: OptionalPackage;

  public totalPurchases !: {package: Package, purchases: number}[];
  public totalPurchasesVP !: {package: Package, validityPeriod: ValidityPeriod, purchases: number}[];

  public suspendedOrders !: Order[];
  public insolventUsers !: User[];
  public alerts !: Alert[];




  constructor(private statService: StatService) {
  }

  adminPages = [
    {title: "Dashboard", link: "/user-dashboard"},
    {title: "Packages", link: "/packages"},
    {title: "Settings", link: "/user-settings"},
  ]

  ngOnInit(): void {
    this.statService.getAlerts().subscribe({next: value => this.alerts = value});
    this.statService.getAverageOptionalPackages().subscribe({next: value => this.averageOptionalPackages = value})
    this.statService.getBestSellerOptionalPackage().subscribe({next: value => this.bestOptionalPackage = value})
    this.statService.getOrderPerPackage().subscribe(
      {
        next: (value:{first: Package, second:number}[]) => {
          this.totalPurchases = value.map((i) => {return {package: i.first, purchases: i.second}})
        }
      }
    )
    // this.statService.getOrderPerPackageAndVP().subscribe(
    //   {
    //     next: (value:{key: {first: Package,second: ValidityPeriod},value: number}[]) => {
    //       this.totalPurchases = value.map((i) =>
    //       {return {package: i.key.first, validityPeriod : i.key.second,  purchases: i.value}})
    //     }
    //   }
    // )
    this.statService.getInsolventUsers().subscribe({
      next: value => this.insolventUsers = value
    })
    // this.statService.getSuspendedOrders().subscribe({
    //   next: value => this.suspendedOrders = value
    // })


  }

  getPages(): any {
    return this.adminPages;
  }

  ngOnDestroy(): void {

  }


}
