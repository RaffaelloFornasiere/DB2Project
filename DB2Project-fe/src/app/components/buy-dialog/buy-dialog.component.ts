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
  startDate: Date = new Date();
  makeItFail: boolean = false;

  constructor(private dialogRef: MatDialogRef<BuyDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: {
                title: string,
                packageDetails: PackageDetails
              }) {
  }


  ngOnInit() {

  }

  res!: any;

  close(){
    this.res = {payment: this.makeItFail, optionalPackages: this.formOptionalPackages.value, validityPeriod: this.formPeriods.value,  startDate: this.startDate};
    this.ngOnDestroy()
  }

  ngOnDestroy(): void {
    this.dialogRef.close(this.res);
  }

}
