import {Component, OnInit} from '@angular/core';
import {Package} from "../../interfaces/package";
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
  map!: Map<string, string>;

  constructor(private route: ActivatedRoute,
              private packageService: PackageService,
              private purchaseService: PurchaseService,
              private dialog: MatDialog,
              private router: Router,
              private tokenService: TokenStorageService
  ) {
  }

  async ngOnInit(): Promise<void> {
    this.packageDetails = {} as PackageDetails;
    this.packageDetails.package = JSON.parse(this.route.snapshot.queryParams['data']);

    let id = this.packageDetails.package.id!;
    await this.packageService
      .getOptionalPackages(id).toPromise().then(res => this.packageDetails!.optionalPackages = res)
    await this.packageService
      .getValidityPeriods(id).toPromise().then(res => this.packageDetails!.validityPeriods = res)
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
        next: (data: any) => {
          data.package = this.packageDetails.package;
          result = JSON.stringify(data)
          if (!this.tokenService.isAuthenticated())
            this.router.navigate(['login'], {queryParams: {returnUrl: 'confirm', data: result}})
              .then()
          else
            this.router.navigate(['confirm'], {queryParams: {data: result}})
              .then()
        }
      }
    );


  }

}
