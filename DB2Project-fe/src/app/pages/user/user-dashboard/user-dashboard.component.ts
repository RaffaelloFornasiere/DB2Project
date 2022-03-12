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

// import {ChartConfiguration, ChartData, ChartTypeRegistry} from "chart.js";
// import {} from "/src/app/components/chart/utils.ts"

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.scss']
})
export class UserDashboardComponent implements OnInit {
  public static title = "Dashboard"

  activeOrders: Order[] = [];
  graphSpecs: {
    service: string
    graphData: SingleDataSet,
    graphLabels: MultiLineLabel[],
    graphColors: Color[],
  }[] = [];
  totalCost: number = 0;

  constructor(private navbarService: NavbarService,
              private statService: StatService) {
    navbarService.loggingVisibilityChange.subscribe(value => {
      this.needsToBeLogged = value
    })
  }

  ngOnInit(): void {
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
