import { Injectable } from '@angular/core';
import {Package} from "../interfaces/package";
import {User} from "../interfaces/user";
import {Observable, of} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {TokenStorageService} from "./token-storage.service";
import {Order} from "../interfaces/Order";
import {catchError, tap} from "rxjs/operators";



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
    console.log("by service: ", o)
    return this.http.post("/api/orders/buy/"+payment, o,httpOptions)
      .pipe(
        tap(() => console.log('fetched heroes')),
        catchError(this.handleError<Package>('buy'))
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
