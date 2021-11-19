import {Component, OnInit} from '@angular/core';
import {Color, Label, MultiDataSet} from "ng2-charts";
import {ChartType, Chart} from "chart.js";
import {MultiLineLabel} from "ng2-charts/lib/base-chart.directive";
// import {ChartConfiguration, ChartData, ChartTypeRegistry} from "chart.js";
// import {} from "/src/app/components/chart/utils.ts"

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.scss']
})
export class UserDashboardComponent implements OnInit {
  public static title = "Dashboard"

  constructor() {
  }
  doughnutChartLabels: MultiLineLabel[] = [
    ['Used'], ['Remaining'],
  ];
  doughnutChartData: MultiDataSet = [
    [55, 25],
    [1,2]
  ];
  doughnutChartColors: Color[] = [
    {
      borderColor: [ this.getRandomColor(), this.getRandomColor()],
      backgroundColor: [ this.getRandomColor(), this.getRandomColor()]
    },
    {
      borderColor: [ this.getRandomColor(), this.getRandomColor()],
      backgroundColor: [ this.getRandomColor(), this.getRandomColor()]
    },
  ];

  doughnutChartType: ChartType = 'doughnut';

  userPages = [
    {title: "Dashboard", link: "/user-dashboard"},
    {title: "Packages", link: "/packages"},
    {title: "Settings", link: "/user-settings"},
  ]

  // config!: ChartConfiguration<keyof ChartTypeRegistry, number[], string>;


  ngOnInit(): void {
  }


  getRandomColor(): string {
    let letters = '0123456789ABCDEF';
    let color = '#';
    for (let i = 0; i < 6; i++) {
      color += letters[Math.floor(Math.random() * 16)];
    }

    return color;
  }

  getPages():any{
    return this.userPages;
  }


}
