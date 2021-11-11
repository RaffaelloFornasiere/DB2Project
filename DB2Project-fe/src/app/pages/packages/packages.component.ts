import { Component, OnInit } from '@angular/core';
import {Package} from "../../interfaces/package";
import {PackageService} from "../../services/package.service";

@Component({
  selector: 'app-packages',
  templateUrl: './packages.component.html',
  styleUrls: ['./packages.component.scss']
})
export class PackagesComponent implements OnInit {
  packages: Package[] = [];
  width= 1;
  constructor(private packageService: PackageService) {
  }

  ngOnInit(): void {
   this.packageService.getPackages()
     .subscribe(packages =>
     {
       this.packages = packages
        this.width = packages.map(i => i.name.length).reduce((a,b) => a<b?a:b)*1.5;
     });

  }

}
