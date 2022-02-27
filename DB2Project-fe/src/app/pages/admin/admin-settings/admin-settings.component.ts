import {Component, OnInit, ViewChild} from '@angular/core';
import {Package} from "../../../interfaces/package";
import {PackageService} from "../../../services/package.service";
import {NavbarService} from "../../../services/navbar.service";
import {TelecomService} from "../../../interfaces/TelecomService";
import {ValidityPeriod} from "../../../interfaces/ValidityPeriod";
import {OptionalPackage} from "../../../interfaces/OptionalPackage";
import {FormControl} from "@angular/forms";
import {MatFormField} from "@angular/material/form-field";
import {FormFieldComponent} from "../../../components/form-field/form-field.component";


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
  servicesNames : string[] = [];
  validityPeriods: ValidityPeriod[] = [];
  optionalPackages: OptionalPackage[] = [];

  buttonTile = "Create"

  width = 1;
  needsToBeLogged = false;
  managers: Manager[] = [];
  @ViewChild('nameForm') nameForm!: FormFieldComponent;
  @ViewChild('servicesForm') servicesForm!: FormFieldComponent;


  getData(){
    console.log(this.nameForm.getData());
    console.log(this.servicesForm.getData());

  }

  packageForm: FormControl = new FormControl();

  constructor(private packageService: PackageService,
              private navbarService: NavbarService) {
    let i:string[] = this.services.map(s => s.name);

    this.managers.push(
      new Manager(this.packages, [
        {property: "id", type: FormType.NUMBER_INTEGER},
        {property: "name", type: FormType.STRING},
        {property: "telecomServices", type: FormType.SELECT, args: this.services},
      ]),
      new Manager(this.services, [
        {property: "id", type: FormType.NUMBER_INTEGER},
        {property: "name", type: FormType.STRING},
        {
          property: "details", type: FormType.SELECT, args: [
            {
              service: "fixedPhone", properties: [
                {property: "costMonth", type: "number"}
              ]
            },
            {
              service: "fixedInternet", properties: [
                {property: "gigabytes", type: "number"},
                {property: "extraGigaBytesFee", type: "number"},
                {property: "costMonth", type: "number"}
              ]
            },
            {
              service: "mobileInternet", properties: [
                {property: "gigabytes", type: "number"},
                {property: "extraGigaBytesFee", type: "number"},
                {property: "costMonth", type: "number"}
              ]
            },
            {
              service: "mobilePhone", properties: [
                {property: "sms", type: "number"},
                {property: "minutes", type: "number"},
                {property: "extraSmsFee", type: "number"},
                {property: "extraMinutesFee", type: "number"}
              ]
            }
          ]
        },
      ]),
      new Manager(this.optionalPackages, [
        {property: "id", type: FormType.NUMBER_INTEGER},
        {property: "name", type: FormType.STRING},
        {property: "description", type: FormType.STRING},
        {property: "monthly_fee", type: FormType.NUMBER_DOUBLE},
      ]),
      new Manager(this.validityPeriods, [
        {property: "id", type: FormType.NUMBER_INTEGER},
        {property: "months", type: FormType.NUMBER_INTEGER},
        {property: "fee", type: FormType.NUMBER_DOUBLE},
      ])
    )
  }

  ngOnInit(): void {
    this.packageService.getPackages()
      .subscribe(packages => {
        this.packages = packages
      });

    this.packageService.getServices()
      .subscribe(
        services => {
          this.services = services
          this.servicesNames = services.map(s => s.name)
        }
      )


  }

}
