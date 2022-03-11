import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable, of} from "rxjs";
import {Package} from "../interfaces/package";
import {catchError, map, tap} from 'rxjs/operators';
import {PackageDetails} from "../interfaces/packageDetails";
import {OptionalPackage} from "../interfaces/OptionalPackage";
import {ValidityPeriod} from "../interfaces/ValidityPeriod";
import {TelecomService} from "../interfaces/TelecomService";
import Utils from "../Utils";

@Injectable({
  providedIn: 'root'
})
export class PackageService {
  private packagesUrl = "api/home/packages"

  constructor(
    private http: HttpClient
  ) {
  }

  readonly httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };


  getAllServices(): Observable<TelecomService[]> {
    return this.http.get<TelecomService[]>("api/services")
  }

  getService(serviceId: number): Observable<TelecomService> {
    return this.http.get<TelecomService>("api/services/" + serviceId)
  }

  getServices(packageId: number): Observable<TelecomService[]> {
    return this.http.get<TelecomService[]>("api/services/" + packageId)
  }

  getAllPackages(): Observable<Package[]> {
    return this.http.get<Package[]>(this.packagesUrl)
  }

  getAllOptionalPackages(): Observable<OptionalPackage[]> {
    return this.http.get<OptionalPackage[]>("api/optionalPackages/")
  }


  getOptionalPackages(packageId: number): Observable<OptionalPackage[]> {
    return this.http.get<OptionalPackage[]>("api/home/optionalPackages/package/" + packageId)
  }

  saveOptionalPackage(optionalPackage: OptionalPackage): Observable<OptionalPackage> {
    return this.http.post<OptionalPackage>("api/optionalPackages/save/",
      optionalPackage, this.httpOptions)
  }

  deleteOptionalPackage(optionalPackageId: number){
    return this.http.delete("api/optionalPackages/delete/"+optionalPackageId)
  }

  getAllValidityPeriods(): Observable<ValidityPeriod[]> {
    return this.http.get<ValidityPeriod[]>("api/packages/validity-periods/")
  }

  getValidityPeriods(packageId: number): Observable<ValidityPeriod[]> {
    return this.http.get<ValidityPeriod[]>("api/home/packages/validity-periods/" + packageId)
  }

  saveValidityPeriod(validityPeriod: ValidityPeriod): Observable<ValidityPeriod> {
    return this.http.post<ValidityPeriod>("api/validityPeriod/save/",
      validityPeriod, this.httpOptions)
  }

  deleteValidityPeriod(validityPeriodId: number){
    return this.http.delete("api/validityPeriod/delete/"+validityPeriodId)
  }

  savePackage(packageService: Package, optionalPackages: OptionalPackage[], validityPeriods: ValidityPeriod[]): Observable<Package> {
    return this.http.post<Package>("api/packages/save/", [
      JSON.stringify(packageService),
      JSON.stringify(optionalPackages),
      JSON.stringify(validityPeriods)
    ])
  }

  deletePackage(packageId: number){
    return this.http.delete("api/packages/delete/"+packageId)
  }

  saveService(telecomService: TelecomService): Observable<TelecomService> {
    return this.http.post<TelecomService>("api/services/save/",
      telecomService, this.httpOptions)
  }

  deleteService(serviceId: number){
    return this.http.delete("api/services/delete/"+serviceId)
  }


  getDetails(id: number): Observable<Package> {
    return this.http.get<Package>('api/home/packages/detail/' + id)
  }


}
