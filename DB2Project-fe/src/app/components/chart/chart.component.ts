import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Chart, ChartConfiguration, ChartData, ChartType, ChartTypeRegistry, registerables} from 'chart.js';
import {templateSourceUrl} from "@angular/compiler";

Chart.register(...registerables);

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.scss']
})
export class ChartComponent implements OnInit, OnDestroy {
  chart: Chart | undefined;


  @Input() config!: ChartConfiguration<keyof ChartTypeRegistry, number[], string>;

  ngOnInit(): void {
    new Chart(document.querySelector("canvas")!, this.config!);
  }

  ngOnDestroy(): void {

  }

}
