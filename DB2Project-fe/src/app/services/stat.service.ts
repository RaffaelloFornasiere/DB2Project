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

  getUsersReport():Observable<any[]>{
    return this.http.get<any[]>("/api/report/users-report",httpOptions)
      .pipe(
        tap(() => console.log('getInsolventUsers done')),
        catchError(Utils.handleError<any[]>('getInsolventUsers'))
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

  getPackageReport(): Observable<any[]>{
    return this.http.get<any[]>("/api//report/optionalPackages/average/",httpOptions)
      .pipe(
        tap(() => console.log('getAverageOptionalPackages done')),
        catchError(Utils.handleError<any[]>('getAverageOptionalPackages'))
      );
  }

  getBestSellerOptionalPackage(): Observable<any[]>{
    return this.http.get<any[]>("/api/report/optionalPackages/best-seller/",httpOptions)
      .pipe(
        tap(() => console.log('getAverageOptionalPackages done')),
        catchError(Utils.handleError<any[]>('getAverageOptionalPackages'))
      );
  }

  getOrderPerPackage(): Observable<{first: Package, second: number}[]>{
    return this.http.get<{first: Package, second: number}[]>("/api/orders/packages/",httpOptions)
      .pipe(
        tap(() => console.log('getOrderPerPackage done')),
        catchError(Utils.handleError<{first: Package, second: number}[]>('getOrderPerPackage'))
      );
  }

  getOrderPerPackageAndVP(): Observable<any[]>{
    return this.http.get<any[]>("/api/report/packages-and-vp/",httpOptions)
      .pipe(
        tap(() => console.log('getOrderPerPackageAndVP done')),
        catchError(Utils.handleError<any[]>('getOrderPerPackageAndVP'))
      );
  }

}
