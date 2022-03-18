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
import {OrderService} from "../../../services/order.service";
import {D} from "@angular/cdk/keycodes";
import Utils from "../../../Utils";
import {MatPaginator} from "@angular/material/paginator";


@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.scss']
})
export class AdminDashboardComponent implements OnInit {
  public static title = "Dashboard"

  public packageReport !: {
    displayedColumns: string[];
    titles: string[]
    data: {
      servicePackage: string,
      totalSold: number,
      totalWithOptional: number,
      totalWithoutOptional: number,
      averageOptional: number,
    }[];
  }

  public optionalPackageReport !: {
    displayedColumns: string[];
    titles: string[]
    data: {
      optionalPackage: string,
      score: string,
    }[];
  };
  panelOpenState = false;

  public validityPeriodReport !: {
    displayedColumns: string[];
    titles: string[]
    data: any[];
  };

  public usersReport!: {
    user: User,
    suspendedOrders: {first: Order, second: {id: number, username: string}[]}[],
    badge: number,
    alert: Alert,
  }[]


  constructor(private statService: StatService,
              private purchaseService: OrderService) {

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

  print(a: any) {
    console.log(a)
  }

  ngOnInit(): void {

    // this.statService.getAlerts().subscribe({next: value => this.alerts = value});
    this.statService.getPackageReport()
      .subscribe({
        next: value => {
          this.packageReport = {
            displayedColumns: [],
            titles: [],
            data: []
          };

          value.forEach(v => this.packageReport.data.push(
            {
              servicePackage: v.servicePackage.name,
              totalSold: v.totalSold,
              averageOptional: v.averageOptional,
              totalWithOptional: v.totalWOptional,
              totalWithoutOptional: v.totalWoOptional,
            }
          ))
          this.packageReport.displayedColumns = Object.keys(this.packageReport.data[0])
          this.packageReport.titles = this.packageReport.displayedColumns.map(
            c => Utils.camelToText(c)
          )

        }
      })
    this.statService.getBestSellerOptionalPackage().subscribe(
      {
        next: value => {
          this.optionalPackageReport = {
            displayedColumns: [],
            titles: [],
            data: []
          }
          value.forEach(v => this.optionalPackageReport.data.push(
            {
              optionalPackage: v.optionalPackage.name,
              score: (v.score * 100).toFixed(0) + '%'
            }));
          this.optionalPackageReport.displayedColumns = Object.keys(this.optionalPackageReport.data[0])
          this.optionalPackageReport.titles = this.optionalPackageReport.displayedColumns.map(
            c => Utils.camelToText(c)
          )
        }
      })
    this.statService.getOrderPerPackageAndVP().subscribe(
      {
        next: value => {
          this.validityPeriodReport = {
            displayedColumns: [],
            titles: [],
            data: []
          }
          let data: {
            servicePackage: string,
            validityPeriod: string
            totalSold: number,
          }[] = [];
          value.forEach(v => data.push(
            {
              servicePackage: v.salesValidityId.servicePackage.name,
              validityPeriod: this.encodeValidityPeriod(v.salesValidityId.validityPeriod),
              totalSold: v.total
            }));

          let table: any = []
          let servicePackages = data.map(i => i.servicePackage);
          let validityPeriods = data.map(i => i.validityPeriod)
          servicePackages = servicePackages.filter((e, i) => servicePackages.indexOf(e) === i);
          validityPeriods = validityPeriods.filter((e, i) => validityPeriods.indexOf(e) === i);
          servicePackages.forEach(servicePackage => {
            let obj: any = {};
            obj.servicePackage = servicePackage;
            validityPeriods.forEach(vp => {
              obj[vp] = data.filter(i => i.servicePackage === servicePackage
                && i.validityPeriod == vp).map(row => row.totalSold)
                .reduce((a, b) => a + b, 0)
            })
            table.push(obj);
          })

          this.validityPeriodReport.data = table;
          this.validityPeriodReport.displayedColumns = Object.keys(table[0])
          this.validityPeriodReport.titles = this.validityPeriodReport.displayedColumns.map(i => i);
          this.validityPeriodReport.titles[0] = "";
        }
      }
    )

    this.statService.getUsersReport().subscribe({
      next: value => {
        this.usersReport = [];
        console.log(value)
        value.forEach(v => this.usersReport.push({
          user: v.user,
          suspendedOrders: v.suspendedOrders,
          badge: v.suspendedOrders.map((i:any)=>i.second.length).reduce((a:number,b:number) => a+b),
          alert: v.alert
        }))

        console.log(this.usersReport)
      }
    })


    this.purchaseService.getOrdersPerDay().subscribe(
      data => {
        this.datapoints = [];
        for (let i = 0; i < data.length; i++) {
          this.datapoints.push(data[i].second);
          this.labels.push(new Date(data[i].first).toDateString());
        }
        this.data = [
          {
            label: 'Total Orders',
            data: this.datapoints,
            borderColor: '#ff0000',
            fill: false,
            cubicInterpolationMode: 'monotone',
          },
        ];
      })
  }

  encodeValidityPeriod(vp: ValidityPeriod) {
    return "Months: " + vp.months
      + ", fee: " + vp.fee.toFixed(2)
  }

  getPages(): any {
    return this.adminPages;
  }
}
