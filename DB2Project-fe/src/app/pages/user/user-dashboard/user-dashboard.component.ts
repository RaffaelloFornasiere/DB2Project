import {Component, OnInit} from '@angular/core';
import {Color, Label, MultiDataSet, MultiLineLabel, SingleDataSet, SingleLineLabel} from "ng2-charts";
import {ChartType, Chart, ChartDataSets, ChartOptions} from "chart.js";
import {NavbarService} from "../../../services/navbar.service";
import {Package} from "../../../interfaces/package";
import {StatService} from "../../../services/stat.service";
import {Order} from "../../../interfaces/Order";
import {ValidityPeriod} from "../../../interfaces/ValidityPeriod";
import {TelecomService} from "../../../interfaces/TelecomService";
import Utils from "../../../Utils";
import {PurchaseService} from "../../../services/purchase.service";
import {D} from "@angular/cdk/keycodes";

// import {ChartConfiguration, ChartData, ChartTypeRegistry} from "chart.js";
// import {} from "/src/app/components/chart/utils.ts"

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.scss']
})
export class UserDashboardComponent implements OnInit {
  public static title = "Dashboard"

   orders !: {
    displayedColumns: string[];
    titles: string[]
    data: {
      ID: number,
      'Service Package': string
      'Order date': string,
      'Activation': string,
      'Deactivation': string
    }[];
  }
  graphSpecs: {
    service: string
    graphData: SingleDataSet,
    graphLabels: MultiLineLabel[],
    graphColors: Color[],
  }[] = [];
  totalCost: number = 0;

  constructor(private navbarService: NavbarService,
              private statService: StatService,
              private purchaseService: PurchaseService) {
    navbarService.loggingVisibilityChange.subscribe(value => {
      this.needsToBeLogged = value
    })
  }

  ngOnInit(): void {
    this.purchaseService.getOrdersPerUser().subscribe(data =>{
      this.orders = {
        displayedColumns: [],
        titles: [],
        data: []
      };


      data.forEach(v => this.orders.data.push(
          {
            ID: v.id!,
            'Service Package': v.servicePackage.name,
            'Order date': new Date(v.orderDate).toDateString(),
            'Activation': new Date(v.startDate).toDateString(),
            'Deactivation':
                new Date(new Date(v.startDate)
                    .setMonth(new Date(v.startDate).getMonth() + v.validityPeriod.months)).toDateString()
          }
      ))
      if(this.orders.data.length > 0)
      this.orders.displayedColumns = Object.keys(this.orders.data[0])
      this.orders.titles = this.orders.displayedColumns;

    })


    this.statService.getUsersCumulativeServices().subscribe(
      data => {

        console.log(data)
        Object.keys(data).forEach(k => {
          let used = Utils.getRandom(0.5, 0.7*data[k]);
          if (data[k] != 0 && k != 'cost')
            this.graphSpecs.push({
              service: k,
              graphData: [data[k]-used, used, 0.20 * data[k]],
              graphLabels: [['remaining'], ['Used']],
              graphColors: [
                {
                  borderColor: [this.colors[0], this.grey, 'rgb(255,255,255,0)'],
                  backgroundColor: [this.getLightenColor(this.colors[0]), this.getLightenColor(this.grey), 'rgb(255,255,255,0)']
                },
              ]
            })
          if(k == 'cost')
            this.totalCost = data[k]
        })
      }
    )
  }

  colors: string[] = ['#0f00de', '#de0076'];
  grey = '#BBBBBB'

  needsToBeLogged = true;


  userPages = [
    {title: "Dashboard", link: "/user-dashboard"},
    {title: "Packages", link: "/packages"},
  ]

  // config!: ChartConfiguration<keyof ChartTypeRegistry, number[], string>;



  getLightenColor(color: string) {
    return color + 'AA';
  }

  getPages(): any {
    return this.userPages;
  }


}
