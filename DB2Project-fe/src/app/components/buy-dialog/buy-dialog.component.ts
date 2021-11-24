import {Component, Inject, OnDestroy, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-buy-dialog',
  templateUrl: './buy-dialog.component.html',
  styleUrls: ['./buy-dialog.component.scss']
})
export class BuyDialogComponent implements OnInit, OnDestroy {
  formOptionalPackages = new FormControl();
  formPeriods = new FormControl();

  optionalPackages: { name: string, description: string, cost: number }[] =
    [
      {name: "opt1", description: "descOpt1", cost: 1},
      {name: "opt2", description: "descOpt2", cost: 2},
      {name: "opt3", description: "descOpt3", cost: 3}
    ]
  validityPeriods: { name: string, description: string, cost: number }[] =
    [
      {name: "period1", description: "descOpt1", cost: 1},
      {name: "period2", description: "descOpt2", cost: 2},
      {name: "period3", description: "descOpt3", cost: 3}
    ]

  constructor(private dialogRef: MatDialogRef<BuyDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: { title: string, message: string }) {
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
