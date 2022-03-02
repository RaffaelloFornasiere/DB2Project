import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {PurchaseService} from "../../services/purchase.service";

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.scss']
})
export class ConfirmationComponent implements OnInit {

  constructor(private route: ActivatedRoute,
              private purchaseService: PurchaseService) { }

  data: any;
  ngOnInit(): void {
    this.route.queryParamMap.subscribe(
      (params: any) => {
        this.data = JSON.parse(params.get('data')!)
        console.log("data: ", this.data)
      }
    )
  }


  buy(){
     this.purchaseService.buy(this.data.order, this.data.payment)
       .subscribe({next:data => console.log(data)})
  }
}
