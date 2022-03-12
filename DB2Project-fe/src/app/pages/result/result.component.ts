import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {PackageService} from "../../services/package.service";
import {ValidityPeriod} from "../../interfaces/ValidityPeriod";
import {OptionalPackage} from "../../interfaces/OptionalPackage";
import {User} from "../../interfaces/user";
import {PurchaseService} from "../../services/purchase.service";
import {Order} from "../../interfaces/Order";
import {Package} from "../../interfaces/package";

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.scss']
})
export class ResultComponent implements OnInit {
  data!: {
    order: {
      id: number,
      orderDate: Date,
      user: User,
      servicePackage: Package,
      validityPeriod: ValidityPeriod,
      optionalPackages: OptionalPackage[],
      startDate: Date
    }
    outcome: boolean
  }

  constructor(private route: ActivatedRoute,
              private router: Router,
              private purchaseService: PurchaseService) {
    this.route.queryParamMap.subscribe(
      (params: any) => {
        console.log(params)
        this.data = JSON.parse(this.route.snapshot.queryParams['data']);
      }
    )
  }

  payment: boolean = false;

  retryPayment() {
    let order: Order = {
      id: this.data.order.id,
      orderDate: this.data.order.orderDate,
      servicePackage: this.data.order.servicePackage,
      optionalPackages: this.data.order.optionalPackages,
      validityPeriod: this.data.order.validityPeriod,
      startDate: this.data.order.startDate
    }
    this.purchaseService.buy(order, this.payment)
      .subscribe(data => {
          let result = JSON.stringify({
            order: data.first,
            outcome: data.second
          });
          this.router.navigate(['result'], {queryParams: {data: result}})
        }
      )
  }

  ngOnInit(): void {
  }

}
