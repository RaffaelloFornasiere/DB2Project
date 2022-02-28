import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable, of} from "rxjs";
import {Package} from "../interfaces/package";
import {catchError, map, tap} from 'rxjs/operators';
import {PackageDetails} from "../interfaces/packageDetails";
import {OptionalPackage} from "../interfaces/OptionalPackage";
import {ValidityPeriod} from "../interfaces/ValidityPeriod";
import {TelecomService} from "../interfaces/TelecomService";

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
        catchError(this.handleError<TelecomService[]>('getServices', []))
      );
  }

  getServices(packageId: number): Observable<TelecomService[]> {
    return this.http.get<TelecomService[]>("api/services/" + packageId)
      .pipe(
        // tap(_ => console.log('fetched heroes')),
        catchError(this.handleError<TelecomService[]>('getServices', []))
      );
  }

  getAllPackages(): Observable<Package[]> {
    return this.http.get<Package[]>(this.packagesUrl)
      .pipe(
        // tap(_ => console.log('fetched heroes')),
        catchError(this.handleError<Package[]>('getPackages', []))
      );
  }

  getAllOptionalPackages(): Observable<OptionalPackage[]> {
    return this.http.get<OptionalPackage[]>("api/optionalPackages/")
      .pipe(
        catchError(this.handleError<OptionalPackage[]>('getOptionalPackages', []))
      )
  }

  getOptionalPackages(packageId: number): Observable<OptionalPackage[]> {
    return this.http.get<OptionalPackage[]>("api/optionalPackages/package/" + packageId)
      .pipe(
        catchError(this.handleError<OptionalPackage[]>('getOptionalPackages', []))
      )
  }

  getAllValidityPeriods(): Observable<ValidityPeriod[]> {
    return this.http.get<ValidityPeriod[]>("api/packages/validity-periods/")
      .pipe(
        catchError(this.handleError<ValidityPeriod[]>('getOptionalPackages', []))
      )
  }

  getValidityPeriods(packageId: number): Observable<ValidityPeriod[]> {
    return this.http.get<ValidityPeriod[]>("api/packages/validity-periods/" + packageId)
      .pipe(
        catchError(this.handleError<ValidityPeriod[]>('getOptionalPackages', []))
      )
  }

  save(packageService: Package, optionalPackages: OptionalPackage[], validityPeriods: ValidityPeriod[]): Observable<Package> {
    let params = new HttpParams().set('package', JSON.stringify(packageService))
    return this.http.post<Package>("api/packages/save/",
      {params: params}, this.httpOptions)
      .pipe(
        catchError(this.handleError<Package>('getOptionalPackages',))
      )
  }


  getDetails(id: number): Observable<Package> {
    return this.http.get<Package>(this.packagesUrl + '/detail/' + id)
      .pipe(
        // tap(_ => console.log('fetched heroes')),
        catchError(this.handleError<Package>('getPackageDetail'))
      );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.log("error: " + JSON.stringify(error)); // log to console instead

      // TODO: better job of transforming error for user consumption
      console.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
