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
import {ServiceDetails} from "../../../interfaces/ServiceDetails";
import Utils from "../../../Utils";
import {MatTabGroup} from "@angular/material/tabs";


@Component({
  selector: 'app-admin-settings',
  templateUrl: './admin-settings.component.html',
  styleUrls: ['./admin-settings.component.scss']
})
export class AdminSettingsComponent implements OnInit {
  packages: Package[] = [];
  services: TelecomService[] = [];
  servicesNames: string[] = [];
  validityPeriods: ValidityPeriod[] = [];
  optionalPackages: OptionalPackage[] = [];
  serviceTypes: string[] = ["Fixed Internet", "Fixed Phone", "Mobile Internet", "Mobile Phone"]

  buttonTile = "Create"


  packageFormGroup: FormGroup = new FormGroup({
    packageName: new FormControl('', [Validators.required, Validators.minLength(5)]),
    services: new FormControl([], [Validators.min(2)]),
    optionalPackages: new FormControl([], [Validators.required]),
    validityPeriods: new FormControl([])
  })

  serviceFormGroup: FormGroup = new FormGroup({
    serviceName: new FormControl('', [Validators.required, Validators.minLength(5)]),
    serviceTypes: new FormControl([]),
    costMonth: new FormControl(null, [Validators.required]),
    sms: new FormControl(),
    minutes: new FormControl(),
    gigabytes: new FormControl(),
    extraSmsFee: new FormControl(),
    extraMinutesFee: new FormControl(),
    extraGBFee: new FormControl()
  })

  optionalPackageFormGroup: FormGroup = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.minLength(5)]),
    description: new FormControl(null, [Validators.required]),
    monthlyFee: new FormControl(null, [Validators.required]),
  })

  validityPeriodFormGroup: FormGroup = new FormGroup({
    fee: new FormControl(null, [Validators.required]),
    months: new FormControl(null, [Validators.required]),
  })

  selected: (number | undefined)[] = [undefined, undefined, undefined, undefined];
  pageSelected = 3;

  constructor(private packageService: PackageService,
              private navbarService: NavbarService
  ) {
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


  selectPackage(packageId?: number) {
    this.selected[this.pageSelected] = packageId;
    if (packageId === undefined) {
      this.packageFormGroup.get('optionalPackages')?.setValue(null)
      this.packageFormGroup.get('packageName')?.setValue(null)
      this.packageFormGroup.get('services')?.setValue(null)
      this.packageFormGroup.get('validityPeriods')?.setValue(null)
      return;
    }

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

  sendPackage() {
    if (this.selected[this.pageSelected] === undefined) {
      let p: Package = {
        id: undefined,
        name: this.packageFormGroup.get('packageName')?.value,
        telecomServices: this.packageFormGroup.get('services')?.value
      }
      console.log(p)
      this.packageService.savePackage(p, this.packageFormGroup.get('optionalPacakges')?.value,
        this.packageFormGroup.get('validityPeriods')?.value).subscribe((data) => {
        console.log(data)
      });
    } else {
      let p = this.packages.find(p => p.id === this.selected[this.pageSelected]);
      if (p === undefined)
        console.error("errore")
      else {
        p!.name = this.packageFormGroup.get('packageName')?.value
        p!.telecomServices = this.packageFormGroup.get('services')?.value
        console.log(p)
        this.packageService.savePackage(p, this.packageFormGroup.get('optionalPacakges')?.value,
          this.packageFormGroup.get('validityPeriods')?.value).subscribe((data) => {
          console.log(data)
        });
      }
    }
  }

  selectService(serviceId?: number) {
    this.selected[this.pageSelected] = serviceId;
    if (serviceId === undefined) {
      this.buttonTile = "Create";
      this.serviceFormGroup.get('serviceName')?.setValue(null)
      this.serviceFormGroup.get('serviceTypes')?.setValue(null)
      this.serviceFormGroup.get('costMonth')?.setValue(null)
      this.serviceFormGroup.get('sms')?.setValue(null)
      this.serviceFormGroup.get('minutes')?.setValue(null)
      this.serviceFormGroup.get('gigabytes')?.setValue(null)
      this.serviceFormGroup.get('extraSmsFee')?.setValue(null)
      this.serviceFormGroup.get('extraMinutesFee')?.setValue(null)
      this.serviceFormGroup.get('extraGBFee')?.setValue(null)
      return;
    }

    this.buttonTile = "Edit";
    this.packageService.getService(serviceId).subscribe((data: any) => {
      console.log("getOptionalPackages: ", data)
      this.serviceFormGroup.get('serviceName')?.setValue(data.name)
      this.serviceFormGroup.get('serviceTypes')?.setValue(Utils.fromJavaType((data.details as ServiceDetails)["@type"]))
      this.serviceFormGroup.get('costMonth')?.setValue(data.details.costMonth)
      this.serviceFormGroup.get('sms')?.setValue(data.details.sms)
      this.serviceFormGroup.get('minutes')?.setValue(data.details.minutes)
      this.serviceFormGroup.get('gigabytes')?.setValue(data.details.gigabytes)
      this.serviceFormGroup.get('extraSmsFee')?.setValue(data.details.extraSmsFee)
      this.serviceFormGroup.get('extraMinutesFee')?.setValue(data.details.extraMinutesFee)
      this.serviceFormGroup.get('extraGBFee')?.setValue(data.details.extraGigaBytesFee)
    });
  }


  sendService() {
    let s: TelecomService = {id: undefined, name: "", details: {}}

    s.id = this.services.filter(s => s.id === this.selected[this.pageSelected]).map(s => s.id)[0];
    s.name = this.serviceFormGroup.get('serviceName')?.value
    s.details["@type"] = Utils.toJavaType(this.serviceFormGroup.get('serviceTypes')?.value)

    s.details.costMonth = this.serviceFormGroup.get('costMonth')?.value
    s.details.sms = this.serviceFormGroup.get('sms')?.value
    s.details.minutes = this.serviceFormGroup.get('minutes')?.value
    s.details.gigabytes = this.serviceFormGroup.get('gigabytes')?.value
    s.details.extraSmsFee = this.serviceFormGroup.get('extraSmsFee')?.value
    s.details.extraMinutesFee = this.serviceFormGroup.get('extraMinutesFee')?.value
    s.details.extraGBFee = this.serviceFormGroup.get('extraGBFee')?.value

    Object.keys(s.details).forEach(key => s.details[key] === undefined && delete s.details[key])

    console.log("service: ", s)
    this.packageService.saveService(s!).subscribe((data) => {
      console.log(data)
    });
  }


  selectOptionalPackage(packageId?: number) {
    this.selected[this.pageSelected] = packageId;
    if (packageId === undefined) {
      this.buttonTile = "Create";
      this.optionalPackageFormGroup.get('name')?.setValue(null)
      this.optionalPackageFormGroup.get('description')?.setValue(null)
      this.optionalPackageFormGroup.get('monthlyFee')?.setValue(null)
      return;
    }

    this.buttonTile = "Edit";
    let data = this.optionalPackages.find(op => op.id === packageId)!
    console.log(data)
    this.optionalPackageFormGroup.get('name')?.setValue(data.name)
    this.optionalPackageFormGroup.get('description')?.setValue(data.description)
    this.optionalPackageFormGroup.get('monthlyFee')?.setValue(data.monthlyFee)
  }

  sendOptionalPackage() {
    let s: OptionalPackage = {id: undefined, name: "", description: "", monthlyFee: 0}

    s.id = this.optionalPackages.filter(s => s.id === this.selected[this.pageSelected]).map(s => s.id)[0];
    s.name = this.optionalPackageFormGroup.get('name')?.value
    s.description = this.optionalPackageFormGroup.get('description')?.value
    s.monthlyFee = this.optionalPackageFormGroup.get('monthlyFee')?.value

    console.log("service: ", s)
    this.packageService.saveOptionalPackage(s!).subscribe((data) => {
      console.log(data)
    });
  }


  selectValidityPeriod(validityId?: number) {
    this.selected[this.pageSelected] = validityId;
    if (validityId === undefined) {
      this.buttonTile = "Create";
      this.validityPeriodFormGroup.get('fee')?.setValue(null)
      this.validityPeriodFormGroup.get('months')?.setValue(null)
      return;
    }

    this.buttonTile = "Edit";
    let data = this.validityPeriods.find(op => op.id === validityId)!
    console.log(data)
    this.validityPeriodFormGroup.get('fee')?.setValue(data.fee)
    this.validityPeriodFormGroup.get('months')?.setValue(data.months)
  }

  sendValidityPeriod() {
    let s: OptionalPackage = {id: undefined, name: "", description: "", monthlyFee: 0}

    s.id = this.optionalPackages.filter(s => s.id === this.selected[this.pageSelected]).map(s => s.id)[0];
    s.description = this.validityPeriodFormGroup.get('fee')?.value
    s.monthlyFee = this.validityPeriodFormGroup.get('months')?.value

    console.log("service: ", s)
    this.packageService.saveOptionalPackage(s!).subscribe((data) => {
      console.log(data)
    });
  }

}
