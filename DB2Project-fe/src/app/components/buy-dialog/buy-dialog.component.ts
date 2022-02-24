import {Component, Inject, OnDestroy, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from "@angular/forms";
import {PackageDetails} from "../../interfaces/packageDetails";
import {ErrorStateMatcher} from "@angular/material/core";


/** Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}


@Component({
  selector: 'app-buy-dialog',
  templateUrl: './buy-dialog.component.html',
  styleUrls: ['./buy-dialog.component.scss']
})
export class BuyDialogComponent implements OnInit, OnDestroy {
  formOptionalPackages = new FormControl(null, [Validators.required]);
  formPeriods = new FormControl(null, [Validators.required]);
  startDate: Date = new Date();
  result: boolean = true;

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
    this.res = {payment: this.result, optionalPackages: this.formOptionalPackages.value, validityPeriod: this.formPeriods.value,  startDate: this.startDate};
    this.ngOnDestroy()
  }

  ngOnDestroy(): void {
    this.dialogRef.close(this.res);
  }

}
