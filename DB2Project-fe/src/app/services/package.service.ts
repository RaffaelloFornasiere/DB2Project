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
      .pipe(
        // tap(_ => console.log('fetched heroes')),
        catchError(Utils.handleError<TelecomService[]>('getServices', []))
      );
  }

  getService(serviceId: number): Observable<TelecomService> {
    return this.http.get<TelecomService>("api/services/" + serviceId)
      .pipe(
        // tap(_ => console.log('fetched heroes')),
        catchError(Utils.handleError<TelecomService>('getServices',))
      );
  }

  getServices(packageId: number): Observable<TelecomService[]> {
    return this.http.get<TelecomService[]>("api/services/" + packageId)
      .pipe(
        // tap(_ => console.log('fetched heroes')),
        catchError(Utils.handleError<TelecomService[]>('getServices', []))
      );
  }

  getAllPackages(): Observable<Package[]> {
    return this.http.get<Package[]>(this.packagesUrl)
      .pipe(
        // tap(_ => console.log('fetched heroes')),
        catchError(Utils.handleError<Package[]>('getPackages', []))
      );
  }

  getAllOptionalPackages(): Observable<OptionalPackage[]> {
    return this.http.get<OptionalPackage[]>("api/optionalPackages/")
      .pipe(
        catchError(Utils.handleError<OptionalPackage[]>('getOptionalPackages', []))
      )
  }

  getOptionalPackage(packageId: number): Observable<OptionalPackage> {
    return this.http.get<OptionalPackage>("api/optionalPackages/" + packageId)
      .pipe(
        catchError(Utils.handleError<OptionalPackage>('getOptionalPackages',))
      )
  }

  getOptionalPackages(packageId: number): Observable<OptionalPackage[]> {
    return this.http.get<OptionalPackage[]>("api/optionalPackages/package/" + packageId)
      .pipe(
        catchError(Utils.handleError<OptionalPackage[]>('getOptionalPackages', []))
      )
  }

  saveOptionalPackage(optionalPackage: OptionalPackage): Observable<OptionalPackage> {
    let params = new HttpParams().set('package', JSON.stringify(optionalPackage))
    return this.http.post<OptionalPackage>("api/optionalPackages/save/",
      {params: params}, this.httpOptions)
      .pipe(
        catchError(Utils.handleError<OptionalPackage>('getOptionalPackages',))
      )
  }

  getAllValidityPeriods(): Observable<ValidityPeriod[]> {
    return this.http.get<ValidityPeriod[]>("api/packages/validity-periods/")
      .pipe(
        catchError(Utils.handleError<ValidityPeriod[]>('getOptionalPackages', []))
      )
  }

  getValidityPeriods(packageId: number): Observable<ValidityPeriod[]> {
    return this.http.get<ValidityPeriod[]>("api/packages/validity-periods/" + packageId)
      .pipe(
        catchError(Utils.handleError<ValidityPeriod[]>('getOptionalPackages', []))
      )
  }

  saveValidityPeriod(validityPeriod: ValidityPeriod): Observable<ValidityPeriod> {
    return this.http.post<ValidityPeriod>("api/validityPeriod/save/",
      validityPeriod, this.httpOptions)
      .pipe(
        catchError(Utils.handleError<ValidityPeriod>('getOptionalPackages',))
      )
  }

  savePackage(packageService: Package, optionalPackages: OptionalPackage[], validityPeriods: ValidityPeriod[]): Observable<Package> {
    return this.http.post<Package>("api/packages/save/", [
      JSON.stringify(packageService),
      JSON.stringify(optionalPackages),
      JSON.stringify(validityPeriods)
    ])
      .pipe(
        catchError(Utils.handleError<Package>('getOptionalPackages',))
      )
  }

  deletePackage(packageId: number){
    return this.http.delete("api/packages/"+packageId)
      .pipe(catchError(Utils.handleError("delete package")))
  }

  saveService(telecomService: TelecomService): Observable<TelecomService> {
    let params = new HttpParams().set('package', JSON.stringify(telecomService))
    return this.http.post<TelecomService>("api/packages/save/",
      {params: params}, this.httpOptions)
      .pipe(
        catchError(Utils.handleError<TelecomService>('getOptionalPackages',))
      )
  }


  getDetails(id: number): Observable<Package> {
    return this.http.get<Package>('api/home/packages/detail/' + id)
      .pipe(
        // tap(_ => console.log('fetched heroes')),
        catchError(Utils.handleError<Package>('getPackageDetail'))
      );
  }


}
