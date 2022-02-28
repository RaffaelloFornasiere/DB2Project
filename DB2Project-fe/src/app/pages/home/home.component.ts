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
    packageService.getAllPackages().subscribe(data => {
      if (data)
        this.packages = data;
      console.log("products: ", this.packages);
    })
  }



  ngOnInit(): void {
  }

}
