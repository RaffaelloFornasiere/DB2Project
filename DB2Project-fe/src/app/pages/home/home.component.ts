import {Component, OnInit} from '@angular/core';
import {PackageService} from "../../services/package.service";
import {Package} from "../../interfaces/package";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  packages!: Package[];
  responsiveOptions!: any;

  constructor(private packageService: PackageService) {
    packageService.getPackages().subscribe(data => {
      if (data)
        this.packages = data;
      console.log("products: ", this.packages);
    })
  }

  getRandomInt(min: number, max: number): number {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min + 1)) + min;
  }


  ngOnInit(): void {
  }

}
