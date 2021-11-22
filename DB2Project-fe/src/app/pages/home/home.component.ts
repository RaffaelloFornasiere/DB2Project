import {Component, OnInit} from '@angular/core';
import {PackageService} from "../../services/package.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  products!: { id: number, name: string, type: string, price: number }[];
  responsiveOptions!: any;

  constructor(private packageService: PackageService) {
    packageService.getPackages().subscribe(data => {
      if (data)
        this.products = data.map(n => {
          return {id: n.id, name: n.name, type: this.getRandomType(), price: this.getRandomInt(5,25)}
        })
      console.log(this.products);
    })
  }

  getRandomType(): string {
    let types = ["mobile phone", "mobile internet", "fixed internet", "fixed phone"]
    let i = this.getRandomInt(0, 3);
    return types[i];
  }

  getRandomInt(min: number, max: number): number {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min + 1)) + min;
  }


  ngOnInit(): void {
  }

}
