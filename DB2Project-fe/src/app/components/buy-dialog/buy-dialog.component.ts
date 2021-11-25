import {Component, Inject, OnDestroy, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {PackageDetails} from "../../interfaces/packageDetails";

@Component({
  selector: 'app-buy-dialog',
  templateUrl: './buy-dialog.component.html',
  styleUrls: ['./buy-dialog.component.scss']
})
export class BuyDialogComponent implements OnInit, OnDestroy {
  formOptionalPackages = new FormControl();
  formPeriods = new FormControl();


  constructor(private dialogRef: MatDialogRef<BuyDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: {
                title: string,
                packageDetails: PackageDetails
              }) {
  }


  ngOnInit() {
  }

  res!: any;

  success() {
    this.res = {payment: "success", optionalPackages: this.formOptionalPackages.value, period: this.formPeriods.value};
    this.ngOnDestroy()
  }

  failure() {
    this.res = {payment: "failure", optionalPackages: this.formOptionalPackages.value, period: this.formPeriods.value};
    this.ngOnDestroy()


  }

  ngOnDestroy(): void {
    if (!this.res)
      this.res = {payment: "undefined"};
    this.dialogRef.close(this.res);
  }

}
