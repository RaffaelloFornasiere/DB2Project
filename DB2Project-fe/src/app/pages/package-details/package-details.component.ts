import { Component, OnInit } from '@angular/core';
import {PackageComponent} from "../../components/package/package.component";

@Component({
  selector: 'app-package-details',
  templateUrl: './package-details.component.html',
  styleUrls: ['./package-details.component.scss']
})
export class PackageDetailsComponent implements OnInit {
  constructor() { }

  ngOnInit(): void {
  }

}
