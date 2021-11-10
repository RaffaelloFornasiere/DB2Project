import { Component, OnInit } from '@angular/core';
import {Chart, ChartConfiguration, ChartData, ChartTypeRegistry, registerables} from 'chart.js';

Chart.register(...registerables);
@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.scss']
})
export class ChartComponent implements OnInit {
  chart: Chart | undefined;
  constructor() { }

  ngOnInit(): void {
    const DATA_COUNT = 12;
    const labels = [];
    for (let i = 0; i < DATA_COUNT; ++i) {
      labels.push(i.toString());
    }
    const datapoints = [0, 20, 20, 60, 60, 120, NaN, 180, 120, 125, 105, 110, 170];


    let data: ChartData<"line", number[], string> = {
      labels: labels,
      datasets: [
        {
          label: 'Cubic interpolation (monotone)',
          data: datapoints,
          borderColor: this.getRandomColor(),
          fill: false,
          cubicInterpolationMode: 'monotone',
          tension: 0.4
        }, {
          label: 'Cubic interpolation',
          data: datapoints,
          borderColor: this.getRandomColor(),
          fill: false,
          tension: 0.4
        }, {
          label: 'Linear interpolation (default)',
          data: datapoints,
          borderColor: this.getRandomColor(),
          fill: false
        }
      ]
    };
    let config: ChartConfiguration<keyof ChartTypeRegistry, number[], string> = {
      type: 'line',
      data: data,
      options: {
        responsive: true,
        plugins: {
          title: {
            display: true,
            text: 'Chart.js Line Chart - Cubic interpolation mode'
          },
        },
        interaction: {
          intersect: false,
        },
        scales: {
          x: {
            display: true,
            title: {
              display: true
            }
          },
          y: {
            display: true,
            title: {
              display: true,
              text: 'Value'
            },
            suggestedMin: -10,
            suggestedMax: 200
          }
        }
      },
    };
    new Chart(document.querySelector("canvas")!, config);
  }
  getRandomColor() :
    string
  {
    let letters = '0123456789ABCDEF';
    let color = '#';
    for (let i = 0; i < 6; i++) {
      color += letters[Math.floor(Math.random() * 16)];
    }

    return color;
  }


}
