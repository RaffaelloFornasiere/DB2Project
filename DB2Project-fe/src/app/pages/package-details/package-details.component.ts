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

  ngOnInit(): void {
    let id = Number(this.route.snapshot.paramMap.get('id'));

    this.packageService.getDetails(id).subscribe(
      {
        next: (data: Package) => {
          console.log("data: ", data)
          // @ts-ignore
          // data.telecoServices.forEach(i => delete i.details["@type"])
          this.packageDetails = {
            package: data,
            optionalPackages: [],
            validityPeriods:
              [
                {id: 1, name: "period1", period: "jun-aug"},
                {id: 2, name: "period2", period: "jul-sep"},
                {id: 3, name: "period3", period: "gen-oct"}
              ]
          };
          this.packageService.getOptionalPackages(id).subscribe(
            {
              next: (data: OptionalPackage[]) => {
                this.packageDetails.optionalPackages = data;
              }
            }
          )
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

          if (!this.tokenService.isAuthenticated())
            this.router.navigate(['login'], {queryParams: {returnUrl: 'confirm', data: result}})

          let order: Order = {
            id: null,
            servicePackage: p,
            user: this.tokenService.getUser(),
            orderDate: new Date(),
            optionalPackages: data.optionalPackages,
            validityPeriod: data.validityPeriod,
            startDate: data.startDate
          }
          let a = {order: order, payment: data.payment}
          result = JSON.stringify(a);
          this.router.navigate(['confirm'], {queryParams: {data: result}});
        }
      }
    );


  }

}
