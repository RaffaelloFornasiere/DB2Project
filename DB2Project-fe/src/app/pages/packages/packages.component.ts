import { Component, OnInit } from '@angular/core';
import {Package} from "../../interfaces/package";
import {PackageService} from "../../services/package.service";
import {NavbarService} from "../../services/navbar.service";

@Component({
  selector: 'app-packages',
  templateUrl: './packages.component.html',
  styleUrls: ['./packages.component.scss']
})
export class PackagesComponent implements OnInit {
  packages: Package[] = [];
  width= 1;
  needsToBeLogged=false;
  constructor(private packageService: PackageService,
              private navbarService: NavbarService) {
    navbarService.loggingVisibilityChange.subscribe(value => {this.needsToBeLogged = value})
  }

  ngOnInit(): void {
   this.packageService.getPackages()
     .subscribe(packages =>
     {
       this.packages = packages
     });

  }

}
