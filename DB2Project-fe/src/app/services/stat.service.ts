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
import Utils from "../Utils";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class StatService {
  constructor(private http: HttpClient){}

  getInsolventUsers():Observable<User[]>{
    return this.http.get<User[]>("/api/report/users/insolvent",httpOptions)
      .pipe(
        tap(() => console.log('getInsolventUsers done')),
        catchError(Utils.handleError<User[]>('getInsolventUsers'))
      );
  }

  getAlerts():Observable<Alert[]>{
    return this.http.get<Alert[]>("/api/report/alerts/",httpOptions)
      .pipe(
        tap(() => console.log('getAlerts done')),
        catchError(Utils.handleError<Alert[]>('getAlerts'))
      );
  }

  getSuspendedOrders():Observable<Order[]>{
    return this.http.get<Order[]>("/api/orders/suspended/",httpOptions)
      .pipe(
        tap(() => console.log('getSuspendedOrders done')),
        catchError(Utils.handleError<Order[]>('getSuspendedOrders'))
      );
  }

  getAverageOptionalPackages(): Observable<{first: Package, second: number}[]>{
    return this.http.get<{first: Package, second: number}[]>("/api/optionalPackages/average/",httpOptions)
      .pipe(
        tap(() => console.log('getAverageOptionalPackages done')),
        catchError(Utils.handleError<{first: Package, second: number}[]>('getAverageOptionalPackages'))
      );
  }

  getBestSellerOptionalPackage(): Observable<OptionalPackage>{
    return this.http.get<OptionalPackage>("/api/report/optionalPackages/best-seller/",httpOptions)
      .pipe(
        tap(() => console.log('getAverageOptionalPackages done')),
        catchError(Utils.handleError<OptionalPackage>('getAverageOptionalPackages'))
      );
  }

  getOrderPerPackage(): Observable<{first: Package, second: number}[]>{
    return this.http.get<{first: Package, second: number}[]>("/api/orders/packages/",httpOptions)
      .pipe(
        tap(() => console.log('getOrderPerPackage done')),
        catchError(Utils.handleError<{first: Package, second: number}[]>('getOrderPerPackage'))
      );
  }

  getOrderPerPackageAndVP(): Observable<{key: {first: Package,second: ValidityPeriod},value: number}[]>{
    return this.http.get<{key: {first: Package,second: ValidityPeriod},value: number}[]>("/api/orders/packages-and-vp/",httpOptions)
      .pipe(
        tap(() => console.log('getOrderPerPackageAndVP done')),
        catchError(Utils.handleError<{key: {first: Package,second: ValidityPeriod},value: number}[]>('getOrderPerPackageAndVP'))
      );
  }

}
