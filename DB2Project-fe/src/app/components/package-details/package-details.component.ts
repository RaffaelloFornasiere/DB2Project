import {Component, OnInit} from '@angular/core';
import {Package} from "../../interfaces/package";
import {ActivatedRoute, Router} from "@angular/router";
import {PackageService} from "../../services/package.service";
import {OrderService} from "../../services/order.service";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {BuyDialogComponent} from "../buy-dialog/buy-dialog.component";
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
              private purchaseService: OrderService,
              private dialog: MatDialog,
              private router: Router,
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
    console.log(conf.data

    )
    let result: any;
    this.dialog.open(BuyDialogComponent, conf).afterClosed().subscribe({
        next: (data: any) => {
          if (data == "cancel")
            return;
          data.package = this.packageDetails.package;
          result = JSON.stringify(data)

          this.router.navigate(['confirm'], {queryParams: {data: result}})
            .then()
        }
      }
    );


  }

}
