import {Component, OnInit} from '@angular/core';
import {PackageComponent} from "../../components/package/package.component";
import {Package} from "../../interfaces/package";
import {AppRoutingModule} from "../../app-routing.module";
import {ActivatedRoute} from "@angular/router";
import {PackageService} from "../../services/package.service";
import {PurchaseService} from "../../services/purchase.service";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {BuyDialogComponent} from "../../components/buy-dialog/buy-dialog.component";

@Component({
  selector: 'app-package-details',
  templateUrl: './package-details.component.html',
  styleUrls: ['./package-details.component.scss'],
})
export class PackageDetailsComponent implements OnInit {
  package!: Package;

  constructor(private route: ActivatedRoute,
              private packageService: PackageService,
              private purchaseService: PurchaseService,
              private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    let id = Number(this.route.snapshot.paramMap.get('id'));
    this.packageService.getDetails(id).subscribe(
      {
        next: data => {
          console.log(data)
          // @ts-ignore
          delete data.details['@type'];
          this.package = data;
        }
      }
    )
  }

  camelToText(camel: string): string {
    let result = camel.replace(/([A-Z])/g, " $1");
    return result.charAt(0).toUpperCase() + result.slice(1);
  }

  buy(p: Package) {
    let conf = new MatDialogConfig();
    conf.autoFocus = true;
    conf.data = {
      name: "Payment", description: "Wanna make payment successful?"
    }
    conf.height = '400px';
    conf.width = '600px';
    let result = this.dialog.open(BuyDialogComponent, conf);

    this.purchaseService.buy(p)
      .subscribe({
        next: value => {

        }
      })
  }

}
