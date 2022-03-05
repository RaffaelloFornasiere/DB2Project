import { Injectable } from '@angular/core';
import {Package} from "../interfaces/package";
import {User} from "../interfaces/user";
import {Observable, of} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {TokenStorageService} from "./token-storage.service";
import {Order} from "../interfaces/Order";
import {catchError, tap} from "rxjs/operators";
import Utils from "../Utils";



const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};
@Injectable({
  providedIn: 'root'
})
export class PurchaseService {

  constructor(private http: HttpClient,
              private tokenStorageService: TokenStorageService) { }

  buy(o: Order, payment: boolean):Observable<any>{
    return this.http.post("/api/orders/buy/"+payment, o,httpOptions)
      .pipe(
        tap(() => console.log('buy')),
        catchError(Utils.handleError<Package>('buy'))
      );
  }

  retryPayment(o: Order, payment: boolean):Observable<any>{
    return this.http.post("/api/orders/retry-payment/"+payment, o, httpOptions)
      .pipe(
        tap(() => {
          console.log('retry payments')
        }),
        catchError(Utils.handleError<Package>('buy'))
      );
  }

  getRejectedOrders():Observable<Order[]>{
    return this.http.get<Order[]>("/api/orders/rejected-orders/")
      .pipe(
        tap((data) => console.log('get rejected orders done: ', data)),
        catchError(Utils.handleError<Order[]>('buy'))
      );
  }

}
