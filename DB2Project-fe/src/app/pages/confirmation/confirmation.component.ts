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
  camelToText(camel: string): string {
    let result = camel.replace(/([A-Z])/g, " $1");
    return result.charAt(0).toUpperCase() + result.slice(1);
  }

  buy(){
    this.purchaseService
  }
}
