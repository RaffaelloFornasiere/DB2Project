import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {OptionalPackage} from "../../../interfaces/OptionalPackage";
import {User} from "../../../interfaces/user";
import {Package} from "../../../interfaces/package";
import {ValidityPeriod} from "../../../interfaces/ValidityPeriod";
import {Order} from "../../../interfaces/Order";
import {StatService} from "../../../services/stat.service";
import {Alert} from "../../../interfaces/Alert";
import {BaseChartDirective, Color, MultiDataSet} from "ng2-charts";
import {ChartDataSets} from "chart.js";
import {PurchaseService} from "../../../services/purchase.service";
import {D} from "@angular/cdk/keycodes";


@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.scss']
})
export class AdminDashboardComponent implements OnInit, OnDestroy {
  public static title = "Dashboard"

  public averageOptionalPackages !: { first: Package, second: number }[];
  public bestOptionalPackage !: OptionalPackage;

  public totalPurchases !: { package: Package, purchases: number }[];
  public totalPurchasesVP !: { package: Package, validityPeriod: ValidityPeriod, purchases: number }[];

  public suspendedOrders !: Order[];
  public insolventUsers !: User[];
  public alerts !: Alert[];


  constructor(private statService: StatService,
              private purchaseService: PurchaseService) {

  }

  adminPages = [
    {title: "Dashboard", link: "/user-dashboard"},
    {title: "Packages", link: "/packages"},
    {title: "Settings", link: "/user-settings"},
  ]

  @ViewChild('chart') chart!: BaseChartDirective;

  DATA_COUNT = 12;
  labels: any[] = [];
  ready: boolean = false;

  datapoints: number[] = [];

  data: ChartDataSets[] = [
    {
      label: 'Total Orders',
      data: this.datapoints,
      borderColor: '#ff0000',
      fill: false,
      cubicInterpolationMode: 'monotone',
      // tension: 0.4
    },
    // {
    //   label: 'Cubic interpolation',
    //   data: this.datapoints,
    //   borderColor: '#0000ff',
    //   fill: false,
    //   // tension: 0.4
    // },
    // {
    //   label: 'Linear interpolation (default)',
    //   data: this.datapoints,
    //   borderColor: '#00ff00',
    //   fill: false
    // }
  ];

  getDates(startDate: Date, stopDate: Date) {
    let dateArray = new Array();
    let currentDate = new Date(startDate);
    let stop = new Date(stopDate)
    while (currentDate <= stop) {
      dateArray.push(new Date(currentDate));
      currentDate = new Date(currentDate.getTime() + 3600 * 1000 * 24)
    }
    return dateArray;
  }

  addDays(days: number): Date {
    let futureDate = new Date();
    futureDate.setDate(futureDate.getDate() + days);
    return futureDate;
  }

  ngOnInit(): void {
    // this.statService.getAlerts().subscribe({next: value => this.alerts = value});
    // this.statService.getAverageOptionalPackages().subscribe({next: value => this.averageOptionalPackages = value})
    // this.statService.getBestSellerOptionalPackage().subscribe({next: value => this.bestOptionalPackage = value})
    // this.statService.getOrderPerPackage().subscribe(
    //   {
    //     next: (value: { first: Package, second: number }[]) => {
    //       this.totalPurchases = value.map((i) => {
    //         return {package: i.first, purchases: i.second}
    //       })
    //     }
    //   }
    // )
    //
    // this.statService.getInsolventUsers().subscribe({
    //   next: value => this.insolventUsers = value
    // })



    this.purchaseService.getOrdersPerDay().subscribe(data => {
      this.datapoints = [];
      for (let i = 0; i < data.length; i++) {
        this.datapoints.push(data[i].second);
        this.labels.push(new Date(data[i].first).toDateString());
      }
      console.log(this.datapoints)
      console.log(this.labels)
      this.data = [
        {
          label: 'Total Orders',
          data: this.datapoints,
          borderColor: '#ff0000',
          fill: false,
          cubicInterpolationMode: 'monotone',
          // tension: 0.4
        },
      ];
    })


  }


  getPages()
    :
    any {
    return this.adminPages;
  }

  ngOnDestroy()
    :
    void {
  }


}
