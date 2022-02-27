import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Order} from "../interfaces/Order";
import {Observable, of} from "rxjs";
import {catchError, tap} from "rxjs/operators";
import {Package} from "../interfaces/package";
import {User} from "../interfaces/user";
import {OptionalPackage} from "../interfaces/OptionalPackage";
import {ValidityPeriod} from "../interfaces/ValidityPeriod";
import {Alert} from "../interfaces/Alert";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class StatService {
  constructor(private http: HttpClient){}

  getInsolventUsers():Observable<User[]>{
    return this.http.get<User[]>("/api/users/insolvent",httpOptions)
      .pipe(
        tap(() => console.log('getInsolventUsers done')),
        catchError(this.handleError<User[]>('getInsolventUsers'))
      );
  }

  getAlerts():Observable<Alert[]>{
    return this.http.get<Alert[]>("/api/orders/alerts/",httpOptions)
      .pipe(
        tap(() => console.log('getAlerts done')),
        catchError(this.handleError<Alert[]>('getAlerts'))
      );
  }

  getSuspendedOrders():Observable<Order[]>{
    return this.http.get<Order[]>("/api/orders/suspended/",httpOptions)
      .pipe(
        tap(() => console.log('getSuspendedOrders done')),
        catchError(this.handleError<Order[]>('getSuspendedOrders'))
      );
  }

  getAverageOptionalPackages(): Observable<{first: Package, second: number}[]>{
    return this.http.get<{first: Package, second: number}[]>("/api/optionalPackages/average/",httpOptions)
      .pipe(
        tap(() => console.log('getAverageOptionalPackages done')),
        catchError(this.handleError<{first: Package, second: number}[]>('getAverageOptionalPackages'))
      );
  }

  getBestSellerOptionalPackage(): Observable<OptionalPackage>{
    return this.http.get<OptionalPackage>("/api/optionalPackages/best-seller/",httpOptions)
      .pipe(
        tap(() => console.log('getAverageOptionalPackages done')),
        catchError(this.handleError<OptionalPackage>('getAverageOptionalPackages'))
      );
  }

  getOrderPerPackage(): Observable<{first: Package, second: number}[]>{
    return this.http.get<{first: Package, second: number}[]>("/api/orders/packages/",httpOptions)
      .pipe(
        tap(() => console.log('getOrderPerPackage done')),
        catchError(this.handleError<{first: Package, second: number}[]>('getOrderPerPackage'))
      );
  }

  getOrderPerPackageAndVP(): Observable<{key: {first: Package,second: ValidityPeriod},value: number}[]>{
    return this.http.get<{key: {first: Package,second: ValidityPeriod},value: number}[]>("/api/orders/packages-and-vp/",httpOptions)
      .pipe(
        tap(() => console.log('getOrderPerPackageAndVP done')),
        catchError(this.handleError<{key: {first: Package,second: ValidityPeriod},value: number}[]>('getOrderPerPackageAndVP'))
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
