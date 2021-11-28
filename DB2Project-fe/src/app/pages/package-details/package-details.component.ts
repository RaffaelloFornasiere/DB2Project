import {Component, OnInit} from '@angular/core';
import {PackageComponent} from "../../components/package/package.component";
import {Package} from "../../interfaces/package";
import {AppRoutingModule} from "../../app-routing.module";
import {ActivatedRoute, Router} from "@angular/router";
import {PackageService} from "../../services/package.service";
import {PurchaseService} from "../../services/purchase.service";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {BuyDialogComponent} from "../../components/buy-dialog/buy-dialog.component";
import {PackageDetails} from "../../interfaces/packageDetails";
import {TokenStorageService} from "../../services/token-storage.service";

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
        next: data => {
          console.log(data)
          // @ts-ignore
          delete data.details['@type'];
          this.packageDetails = {
            package: data,
            optionalPackages:
              [
                {
                  id: 1,
                  name: "optional package 1",
                  description: "xaaaaaaaaaaaaaaaaaaaaaaaaaaaaall sky sport and family",
                  cost: 1
                },
                {id: 2, name: "optional package 2", description: "descOpt2", cost: 2},
                {id: 3, name: "optional package 3", description: "descOpt3", cost: 3}
              ],
            validityPeriods:
              [
                {id: 1, name: "period1", period: "jun-aug"},
                {id: 2, name: "period2", period: "jul-sep"},
                {id: 3, name: "period3", period: "gen-oct"}
              ]
          };
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
    this.dialog.open(BuyDialogComponent, conf).afterClosed().subscribe(data => {
        console.log(data)
        console.log(data.payment)
        result = data;
        if (!this.tokenService.isAuthenticated())
          this.router.navigate(['login'], {queryParams: {returnUrl: this.router.url}}).then(
            () => {
              this.router.navigate(['confirm'], {queryParams: {data: result}})
            }
          );
      }
    );


  }

}
