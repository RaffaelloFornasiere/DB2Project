import {Component, OnInit} from '@angular/core';
import {Package} from "../../../interfaces/package";
import {PackageService} from "../../../services/package.service";
import {TelecomService} from "../../../interfaces/TelecomService";
import {ValidityPeriod} from "../../../interfaces/ValidityPeriod";
import {OptionalPackage} from "../../../interfaces/OptionalPackage";
import {AbstractControl, FormControl, FormGroup, ValidatorFn, Validators} from "@angular/forms";
import {ServiceDetails} from "../../../interfaces/ServiceDetails";
import Utils from "../../../Utils";
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";


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
    packageName: new FormControl('', [Validators.required, Validators.minLength(5), this.packageNameValidator()]),
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
  pageSelected = 0;

  constructor(private packageService: PackageService,
              private route: ActivatedRoute,
              private router: Router,
              public snackBar: MatSnackBar,
  ) {
  }

  ngOnInit(): void {
    this.pageSelected = this.route.snapshot.queryParams['page']

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

  packageNameValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
      console.log(control.value)
      if (this.packages.find(i => i.name === control.value))
        return {'alreadyExist': true};
      else
        return null;
    }
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
    let data = this.packages.find(p => p.id === this.selected[this.pageSelected])!;
    console.log("getDetails: ", data)
    this.packageFormGroup.get('packageName')?.setValue(data.name)
    this.packageFormGroup.get('services')?.setValue(this.services.filter(s => data.telecomServices.find(i => i.id === s.id)));

    this.packageService.getValidityPeriods(packageId).subscribe(
      data => {
        console.log("getValidityPeriods: ", data)
        this.packageFormGroup.get('validityPeriods')?.setValue(this.validityPeriods.filter(vp => data.find(i => i.id === vp.id)))
      }
    )
  }

  sendPackage() {
    let p: Package = {
      id: this.packages.find(p => p.id === this.selected[this.pageSelected])?.id,
      name: this.packageFormGroup.get('packageName')?.value,
      telecomServices: this.packageFormGroup.get('services')?.value
    }

    this.packageService.savePackage(p, this.packageFormGroup.get('optionalPackages')?.value,
      this.packageFormGroup.get('validityPeriods')?.value).subscribe(
      {
        next: () => {
          this.openSnackBar("Done", "Reload")
            .onAction().subscribe(() => this.reload())
        },
        error: () => this.openSnackBar("Can't save package", "ok")
      });

  }

  deletePackage(packageId: number) {
    this.packageService.deletePackage(packageId).subscribe(
      {
        next: () => {
          this.openSnackBar("Done", "Reload")
            .onAction().subscribe(() => this.reload())
        },
        error: () => this.openSnackBar("Can't delete package", "ok")
      });
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
    let data = this.services.find(s => s.id === serviceId)!;
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

  }


  sendService() {
    let s: TelecomService = {id: undefined, name: "", details: {}}

    s.id = this.services.find(s => s.id === this.selected[this.pageSelected])?.id;
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
    this.packageService.saveService(s!).subscribe(
      {
        next: () => {
          this.openSnackBar("Done", "Reload")
            .onAction().subscribe(() => this.reload())
        },
        error: () => this.openSnackBar("Can't save service", "ok")
      });
  }

  deleteService(serviceId: number) {
    this.packageService.deleteService(serviceId).subscribe(
      {
        next: () => {
          this.openSnackBar("Done", "Reload")
            .onAction().subscribe(() => this.reload())
        },
        error: () => this.openSnackBar("Can't delete service", "ok")
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

    this.packageService.saveOptionalPackage(s!).subscribe(
      {
        next: () => {
          this.openSnackBar("Done", "Reload")
            .onAction().subscribe(() => this.reload())
        },
        error: () => this.openSnackBar("Can't send optional package", "ok")
      });

  }

  deleteOptionalPackage(optionalPackageId: number) {
    this.packageService.deleteOptionalPackage(optionalPackageId).subscribe(
      {
        next: () => {
          this.openSnackBar("Done", "Reload")
            .onAction().subscribe(() => this.reload())
        },
        error: () => this.openSnackBar("Can't send optional package", "ok")
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
    this.validityPeriodFormGroup.get('fee')?.setValue(data.fee)
    this.validityPeriodFormGroup.get('months')?.setValue(data.months)
  }

  sendValidityPeriod() {
    let s: ValidityPeriod = {id: undefined, fee: 0, months: 0}

    s.id = this.optionalPackages.filter(s => s.id === this.selected[this.pageSelected]).map(s => s.id)[0];
    s.fee = this.validityPeriodFormGroup.get('fee')?.value
    s.months = this.validityPeriodFormGroup.get('months')?.value

    console.log("service: ", s)
    this.packageService.saveValidityPeriod(s!).subscribe(
      {
        next: () => {
          this.openSnackBar("Done", "Reload")
            .onAction().subscribe(() => this.reload())
        },
        error: () => this.openSnackBar("Can't send optional package", "ok")
      });
  }


  deleteValidityPeriod(validityPeriodId: number) {
    this.packageService.deleteValidityPeriod(validityPeriodId).subscribe(
      {
        next: () => {
          this.openSnackBar("Done", "Reload")
            .onAction().subscribe(() => this.reload())
        },
        error: () => this.openSnackBar("Can't send optional package", "ok")
      });
  }

  openSnackBar(message: string, action: string) {
    return this.snackBar.open(message, action, {
      duration: 2000,
    });
  }

  reload() {
    this.router.navigate(["/admin-settings"], {queryParams: {page: this.pageSelected}}).then(() => location.reload());
  }

}
