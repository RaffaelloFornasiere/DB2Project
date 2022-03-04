import {Component, OnInit} from '@angular/core';
import {PackageService} from "../../services/package.service";
import {Package} from "../../interfaces/package";
import {Router} from "@angular/router";
import {Order} from "../../interfaces/Order";
import {PurchaseService} from "../../services/purchase.service";
import {D} from "@angular/cdk/keycodes";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  packages!: Package[];
  responsiveOptions!: any;

  rejectedOrders!: Order[];

  constructor(private packageService: PackageService,
              private purchaseService: PurchaseService,
              private router: Router,
  ) {

  }

  goToDetails(packageId: number) {
    this.router.navigate(["detail"], {
      queryParams:
        {data: JSON.stringify(this.packages.find(i => i.id === packageId))}
    })
      .then()
  }

  goToOrder(order: Order) {
    let result = JSON.stringify({
      order: order,
      outcome: false
    });

    this.router.navigate(['result'], {queryParams: {data: result}})

  }

  ngOnInit(): void {
    this.packageService.getAllPackages().subscribe(data => {
      if (data)
        this.packages = data;
      // console.log("products: ", this.packages);
    })
    this.purchaseService.getRejectedOrders().subscribe(data => {
      this.rejectedOrders = data;
    })
  }

}
