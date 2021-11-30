import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.scss']
})
export class ConfirmationComponent implements OnInit {

  constructor(private route: ActivatedRoute) { }

  data: any;
  ngOnInit(): void {
    this.route.queryParamMap.subscribe(
      (params) => {
        this.data = JSON.parse(params.get('data')!)
      }
    )
    console.log(this.data)
  }
  camelToText(camel: string): string {
    let result = camel.replace(/([A-Z])/g, " $1");
    return result.charAt(0).toUpperCase() + result.slice(1);
  }
}
