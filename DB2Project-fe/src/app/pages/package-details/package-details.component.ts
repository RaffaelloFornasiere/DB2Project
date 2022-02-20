import {Component, OnInit} from '@angular/core';
import {Package} from "../../interfaces/package";
import {ActivatedRoute, Router} from "@angular/router";
import {PackageService} from "../../services/package.service";
import {PurchaseService} from "../../services/purchase.service";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {BuyDialogComponent} from "../../components/buy-dialog/buy-dialog.component";
import {PackageDetails} from "../../interfaces/packageDetails";
import {TokenStorageService} from "../../services/token-storage.service";
import {OptionalPackage} from "../../interfaces/OptionalPackage";
import {Order} from "../../interfaces/Order";
import {ValidityPeriod} from "../../interfaces/ValidityPeriod";

@Component({
  selector: 'app-package-details',
  templateUrl: './package-details.component.html',
  styleUrls: ['./package-details.component.scss'],
})
export class PackageDetailsComponent implements OnInit {
  packageDetails!: PackageDetails;

  constructor(private route: ActivatedRoute,
              private packageService: PackageService,
              private purchaseService: PurchaseService,
              private dialog: MatDialog,
              private router: Router,
              private tokenService: TokenStorageService
  ) {
  }

  async ngOnInit(): Promise<void> {
    let id = Number(this.route.snapshot.paramMap.get('id'));
    this.packageDetails = {
      package: await this.packageService
        .getDetails(id).toPromise(),
      optionalPackages: await this.packageService
        .getOptionalPackages(id).toPromise(),
      validityPeriods: await this.packageService
        .getValidityPeriods(id).toPromise()
    };
    console.log(this.packageDetails.validityPeriods)
  }

  camelToText(camel: string): string {
    let result = camel.replace(/([A-Z])/g, " $1");
    return result.charAt(0).toUpperCase() + result.slice(1);
  }

  buy(p: Package | undefined) {
    if(p == undefined)
      return;
    let conf = new MatDialogConfig();
    conf.autoFocus = true;
    conf.width = '800px';
    conf.data = {
      title: "Buy " + p.name + "package",
      packageDetails: this.packageDetails
    }
    let result: any;
    this.dialog.open(BuyDialogComponent, conf).afterClosed().subscribe({
        next: (data: { payment: boolean, optionalPackages: [], validityPeriod: any, startDate: Date }) => {
          if (data === undefined)
            return;
          console.log(this.tokenService.isAuthenticated())
          let order: Order = {
            id: null,
            servicePackage: p,
            orderDate: new Date(),
            optionalPackages: data.optionalPackages,
            validityPeriod: data.validityPeriod,
            startDate: data.startDate
          }
          let a = {order: order, payment: data.payment}
          result = JSON.stringify(a);
          if (!this.tokenService.isAuthenticated())
            this.router.navigate(['login'],
              {queryParams: {returnUrl: 'confirm', data: result}})
          else
            this.router.navigate(['confirm'],
              {queryParams: {data: result}});
        }
      }
    );


  }

}
