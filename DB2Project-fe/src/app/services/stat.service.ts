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
import {TokenStorageService} from "./token-storage.service";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class StatService {
  constructor(private http: HttpClient,
              private token: TokenStorageService){}

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

  getUsersCumulativeServices():Observable<any>{
    return this.http.get<any>("/api/report/user-cumulative-services/" + this.token.getUser()?.username,httpOptions)
      .pipe(
        tap(() => console.log('getUsersCumulativeServices done')),
        catchError(Utils.handleError<any>('getUsersCumulativeServices'))
      );
  }

  getAlerts():Observable<Alert[]>{
    return this.http.get<Alert[]>("/api/report/alerts/",httpOptions)
      .pipe(
        tap(() => console.log('getAlerts done')),
        catchError(Utils.handleError<Alert[]>('getAlerts'))
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

  getActivePackages(): Observable<Order[]>{
    return this.http.get<Order[]>("/api/report/user/active-packages/" + this.token.getUser()?.username,httpOptions)
  }

  getOrderPerPackageAndVP(): Observable<any[]>{
    return this.http.get<any[]>("/api/report/packages-and-vp/",httpOptions)
      .pipe(
        tap(() => console.log('getOrderPerPackageAndVP done')),
        catchError(Utils.handleError<any[]>('getOrderPerPackageAndVP'))
      );
  }

}
