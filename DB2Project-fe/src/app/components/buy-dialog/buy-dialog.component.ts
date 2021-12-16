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
    this.res = {optionalPackages: this.formOptionalPackages.value, period: this.formPeriods.value};
    this.ngOnDestroy()
  }

  failure() {
    this.res = {optionalPackages: this.formOptionalPackages.value, period: this.formPeriods.value};
    this.ngOnDestroy()
  }

  ngOnDestroy(): void {
    this.dialogRef.close(this.res);
  }

}
