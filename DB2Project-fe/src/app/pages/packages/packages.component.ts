import { Component, OnInit } from '@angular/core';
import {Package} from "../../interfaces/package";

@Component({
  selector: 'app-packages',
  templateUrl: './packages.component.html',
  styleUrls: ['./packages.component.scss']
})
export class PackagesComponent implements OnInit {
  packages: Package[] = [];

  constructor() {
    this.packages = [
      {name: "aaa", id: 3},
      {name: "bbb", id: 4},

    ]
  }

  ngOnInit(): void {
  }

}
