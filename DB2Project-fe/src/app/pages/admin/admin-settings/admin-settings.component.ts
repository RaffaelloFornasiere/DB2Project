import {Component, OnInit, ViewChild} from '@angular/core';
import {Package} from "../../../interfaces/package";
import {PackageService} from "../../../services/package.service";
import {NavbarService} from "../../../services/navbar.service";
import {TelecomService} from "../../../interfaces/TelecomService";
import {ValidityPeriod} from "../../../interfaces/ValidityPeriod";
import {OptionalPackage} from "../../../interfaces/OptionalPackage";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MatFormField} from "@angular/material/form-field";
import {FormFieldComponent} from "../../../components/form-field/form-field.component";
import {F} from "@angular/cdk/keycodes";


enum FormType {
  STRING,
  NUMBER_INTEGER,
  NUMBER_DOUBLE,
  SELECT,
  SELECT_MULTIPLE,
}

export class Manager {
  constructor(private items: any[],
              private properties: { property: string, type: FormType, args?: any }[]) {
  }
}


@Component({
  selector: 'app-admin-settings',
  templateUrl: './admin-settings.component.html',
  styleUrls: ['./admin-settings.component.scss']
})
export class AdminSettingsComponent implements OnInit {
  readonly PACKAGE_MANAGER = 0;
  readonly SERVICE_MANAGER = 1;
  readonly OP_MANAGER = 2;
  readonly VP_MANAGER = 3;

  packages: Package[] = [];
  services: TelecomService[] = [];
  servicesNames: string[] = [];
  validityPeriods: ValidityPeriod[] = [];
  optionalPackages: OptionalPackage[] = [];

  buttonTile = "Create"


  @ViewChild('nameForm') nameForm!: FormFieldComponent;
  @ViewChild('servicesForm') servicesForm!: FormFieldComponent;

  packageFormGroup: FormGroup = new FormGroup({
    packageName: new FormControl('', [Validators.required, Validators.minLength(5)]),
    services: new FormControl([], [Validators.min(2)]),
    optionalPackages: new FormControl([], [Validators.required]),
    validityPeriods: new FormControl([])
  })

  selectedPackage?: number;

  newPackage(){
    this.buttonTile = "Create";
    this.selectedPackage = undefined;
    this.packageFormGroup.get('optionalPackages')?.setValue(null)
    this.packageFormGroup.get('packageName')?.setValue(null)
    this.packageFormGroup.get('services')?.setValue(null)
    this.packageFormGroup.get('validityPeriods')?.setValue(null)

  }


  selectPackage(packageId?: number) {
    if (packageId === undefined)
      return;
    this.selectedPackage = packageId;
    this.buttonTile = "Edit";
    this.packageService.getOptionalPackages(packageId).subscribe(data => {
      console.log("getOptionalPackages: ", data)
      this.packageFormGroup.get('optionalPackages')?.setValue(this.optionalPackages.filter(op => data.find(i => i.id === op.id)))
    });
    this.packageService.getDetails(packageId).subscribe(data => {
      console.log("getDetails: ", data)

      this.packageFormGroup.get('packageName')?.setValue(data.name)
      this.packageFormGroup.get('services')?.setValue(this.services.filter(s => data.telecomServices.find(i => i.id === s.id)));
    });
    this.packageService.getValidityPeriods(packageId).subscribe(
      data => {
        console.log("getValidityPeriods: ", data)
        this.packageFormGroup.get('validityPeriods')?.setValue(this.validityPeriods.filter(vp => data.find(i => i.id === vp.id)))
      }
    )

  }

  send() {
    if (this.selectedPackage === undefined) {
      let p: Package = {
        id: undefined,
        name: this.packageFormGroup.get('packageName')?.value,
        telecomServices: this.packageFormGroup.get('services')?.value
      }
      console.log(p)
      this.packageService.save(p, this.packageFormGroup.get('optionalPacakges')?.value,
        this.packageFormGroup.get('validityPeriods')?.value).subscribe((data) => {
        console.log(data)
      });
    } else {
      let p = this.packages.find(p => p.id === this.selectedPackage);
      if (p === undefined)
        console.error("errore")
      else {
        p!.name = this.packageFormGroup.get('packageName')?.value
        p!.telecomServices = this.packageFormGroup.get('services')?.value
        console.log(p)
        this.packageService.save(p, this.packageFormGroup.get('optionalPacakges')?.value,
          this.packageFormGroup.get('validityPeriods')?.value).subscribe((data) => {
          console.log(data)
        });
      }
    }
  }


  constructor(private packageService: PackageService,
              private navbarService: NavbarService
  ) {
    let i: string[] = this.services.map(s => s.name);

  }

  setupEditor() {

  }

  ngOnInit()
    :
    void {

    this.packageService.getAllPackages()
      .subscribe(packages => {
        this.packages = packages
      });

    this.packageService.getAllServices()
      .subscribe(
        services => {
          this.services = services
          this.servicesNames = services.map(s => s.name)
        }
      )
    this.packageService.getAllOptionalPackages()
      .subscribe(
        optionalPackages => this.optionalPackages = optionalPackages
      )
    this.packageService.getAllValidityPeriods()
      .subscribe(
        validityPeriods => this.validityPeriods = validityPeriods
      )

  }

}
