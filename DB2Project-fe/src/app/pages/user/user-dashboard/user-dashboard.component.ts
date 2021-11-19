import {Component, OnInit} from '@angular/core';
import {ChartConfiguration, ChartData, ChartTypeRegistry} from "chart.js";
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


  userPages = [
    {title: "Dashboard", link: "/user-dashboard"},
    {title: "Packages", link: "/packages"},
    {title: "Settings", link: "/user-settings"},
  ]

  config!: ChartConfiguration<keyof ChartTypeRegistry, number[], string>;


  ngOnInit(): void {
    const DATA_COUNT = 12;
    const labels = [];
    for (let i = 0; i < DATA_COUNT; ++i) {
      labels.push(i.toString());
    }
    const datapoints = [0, 20, 20, 60, 60, 120, 10, 180, 120, 125, 105, 110, 170];


    const data = {
      labels: ['Red', 'Orange', 'Yellow', 'Green', 'Blue'],
      datasets: [
        {
          label: 'Dataset 1',
          data: datapoints,
          backgroundColor: Object.values({
            red: 'rgb(255, 99, 132)',
            orange: 'rgb(255, 159, 64)',
            yellow: 'rgb(255, 205, 86)',
            green: 'rgb(75, 192, 192)',
            blue: 'rgb(54, 162, 235)',
            purple: 'rgb(153, 102, 255)',
            grey: 'rgb(201, 203, 207)'
          }),
        }
      ]
    };
    this.config = {
      type: 'doughnut',
      data: data,
      options: {
        responsive: true,
        plugins: {
          legend: {
            position: 'top',
          },
          title: {
            display: true,
            text: 'Chart.js Doughnut Chart'
          }
        }
      },
    };
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
