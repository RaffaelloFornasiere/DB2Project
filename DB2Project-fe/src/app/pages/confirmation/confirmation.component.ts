import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {PurchaseService} from "../../services/purchase.service";
import {Order} from "../../interfaces/Order";
import {Package} from "../../interfaces/package";
import {Option} from "@angular/cli/models/interface";
import {OptionalPackage} from "../../interfaces/OptionalPackage";
import {ValidityPeriod} from "../../interfaces/ValidityPeriod";

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.scss']
})
export class ConfirmationComponent implements OnInit {

  constructor(private route: ActivatedRoute,
              private router: Router,
              private purchaseService: PurchaseService

  ) { }

  data!: {
    payment: boolean,
    package: Package,
    optionalPackages: OptionalPackage[],
    validityPeriod: ValidityPeriod,
    startDate: Date
  };
  ngOnInit(): void {
    this.route.queryParamMap.subscribe(
      (params: any) => {
        this.data = JSON.parse(params.get('data')!)
        console.log(this.data);
      }
    )
  }


  buy(){

    let order: Order = {
      id: null,
      orderDate: new Date(),
      servicePackage: this.data.package,
      validityPeriod: this.data.validityPeriod,
      optionalPackages: this.data.optionalPackages,
      startDate: this.data.startDate
    }
     this.purchaseService.buy(order, this.data.payment)
       .subscribe({
         next:data => {
           if(data === undefined)
             return;

           let result = JSON.stringify({
              order: data.first,
              outcome: data.second
           });

           this.router.navigate(['result'], {queryParams: {data: result}})
         },
         error: err => {
           console.log(err)
         }

       })
  }
}
