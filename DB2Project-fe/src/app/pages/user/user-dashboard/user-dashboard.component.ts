import {Component, OnInit} from '@angular/core';
import {Color, Label, MultiDataSet, MultiLineLabel} from "ng2-charts";
import {ChartType, Chart, ChartDataSets, ChartOptions} from "chart.js";
import {NavbarService} from "../../../services/navbar.service";

// import {ChartConfiguration, ChartData, ChartTypeRegistry} from "chart.js";
// import {} from "/src/app/components/chart/utils.ts"

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.scss']
})
export class UserDashboardComponent implements OnInit {
  public static title = "Dashboard"


  usedSMS = this.getRandomInt(10, 40);
  usedMinutes = this.getRandomInt(10, 50);
  doughnutChartData: MultiDataSet = [
    [100 - this.usedSMS, this.usedSMS, 15],
    [100 - this.usedMinutes, this.usedMinutes, 13],
  ];
  doughnutChartLabels: MultiLineLabel[] = [
    ['remaining'], ['Used']
  ]

  colors: string[] = ['#0f00de', '#de0076'];
  grey = '#BBBBBB'
  doughnutChartColors: Color[] = [
    {
      borderColor: [this.colors[0], this.grey, 'rgb(255,255,255,0)'],
      backgroundColor: [this.getLightenColor(this.colors[0]), this.getLightenColor(this.grey), 'rgb(255,255,255,0)']
    },
    {
      borderColor: [this.colors[1], this.grey, 'rgb(255,255,255,0)'],
      backgroundColor: [this.getLightenColor(this.colors[1]), this.getLightenColor(this.grey), 'rgb(255,255,255,0)']
    },
  ];
  doughnutChartType: ChartType = 'doughnut';


  needsToBeLogged = true;

  constructor(private navbarService: NavbarService) {
    navbarService.loggingVisibilityChange.subscribe(value => {
      this.needsToBeLogged = value
    })
  }

  userPages = [
    {title: "Dashboard", link: "/user-dashboard"},
    {title: "Packages", link: "/packages"},
    // {title: "Settings", link: "/user-settings"},
  ]

  // config!: ChartConfiguration<keyof ChartTypeRegistry, number[], string>;
  getRandomInt(min: number, max: number): number {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min + 1)) + min;
  }

  ngOnInit(): void {
  }

  getLightenColor(color: string) {
    return color + 'AA';
  }

  getPages(): any {
    return this.userPages;
  }

  userData: {
    packageName: string,
    packageDetails?: { type: string, amount: number }[],
    packageConsumption?: { type: string, amount: number }[]
  }[] = [
    {
      packageName: "mobile phone",
      packageDetails: [
        {type: "sms", amount: 10000},
        {type: "minutes", amount: 10000},
      ],
      packageConsumption: [
        {type: "sms", amount: 8900},
        {type: "minutes", amount: 500},
      ]
    },
    {
      packageName: "mobile internet",
      packageDetails: [
        {type: "GigaBytes", amount: 100},
      ],
      packageConsumption: [
        {type: "GigaBytes", amount: 12},
      ]
    }
  ]

  getUserData() {

  }


}
