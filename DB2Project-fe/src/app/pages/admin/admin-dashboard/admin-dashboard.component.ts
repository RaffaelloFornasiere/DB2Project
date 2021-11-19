import {Component, OnDestroy, OnInit} from '@angular/core';
import {ChartDataSets} from "chart.js";
import {Color, Label} from "ng2-charts";
import {ChartType} from "chart.js";

// import {Chart, ChartConfiguration, ChartData, ChartTypeRegistry} from "chart.js";

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.scss']
})
export class AdminDashboardComponent implements OnInit, OnDestroy {
  public static title = "Dashboard"

  constructor() {
  }

  adminPages = [
    {title: "Dashboard", link: "/user-dashboard"},
    {title: "Packages", link: "/packages"},
    {title: "Settings", link: "/user-settings"},
  ]
  // config!: ChartConfiguration<keyof ChartTypeRegistry, number[], string>;

  lineChartData: ChartDataSets[] = [
    {data: [85, 72, 78, 75, 77, 75], label: 'Crude oil prices'},
  ];

  lineChartLabels: Label[] = ['January', 'February', 'March', 'April', 'May', 'June'];

  lineChartOptions = {
    responsive: true,
  };

  lineChartColors: Color[] = [
    {
      borderColor: 'black',
      backgroundColor: 'rgba(255,255,0,0.28)',
    },
  ];

  lineChartLegend = true;
  lineChartPlugins = [];
  lineChartType: ChartType = 'line';

  ngOnInit(): void {
    const DATA_COUNT = 12;
    const labels = [];
    for (let i = 0; i < DATA_COUNT; ++i) {
      labels.push(i.toString());
    }
    const datapoints = [0, 20, 20, 60, 60, 120, NaN, 180, 120, 125, 105, 110, 170];


    // let data: ChartData<"line", number[], string> = {
    //   labels: labels,
    //   datasets: [
    //     {
    //       label: 'Cubic interpolation (monotone)',
    //       data: datapoints,
    //       borderColor: this.getRandomColor(),
    //       fill: false,
    //       cubicInterpolationMode: 'monotone',
    //       tension: 0.4
    //     }, {
    //       label: 'Cubic interpolation',
    //       data: datapoints,
    //       borderColor: this.getRandomColor(),
    //       fill: false,
    //       tension: 0.4
    //     }, {
    //       label: 'Linear interpolation (default)',
    //       data: datapoints,
    //       borderColor: this.getRandomColor(),
    //       fill: false
    //     }
    //   ]
    // };
    // this.config = {
    //   type: 'line',
    //   data: data,
    //   options: {
    //     responsive: true,
    //     plugins: {
    //       title: {
    //         display: true,
    //         text: 'Chart.js Line Chart - Cubic interpolation mode'
    //       },
    //     },
    //     interaction: {
    //       intersect: false,
    //     },
    //     scales: {
    //       x: {
    //         display: true,
    //         title: {
    //           display: true
    //         }
    //       },
    //       y: {
    //         display: true,
    //         title: {
    //           display: true,
    //           text: 'Value'
    //         },
    //         suggestedMin: -10,
    //         suggestedMax: 200
    //       }
    //     }
    //   },
    // };
  }

  getPages(): any {
    return this.adminPages;
  }

  getRandomColor(): string {
    let letters = '0123456789ABCDEF';
    let color = '#';
    for (let i = 0; i < 6; i++) {
      color += letters[Math.floor(Math.random() * 16)];
    }

    return color;
  }

  ngOnDestroy(): void {

  }


}
