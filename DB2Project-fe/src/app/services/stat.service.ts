import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Order} from "../interfaces/Order";
import {Observable, of} from "rxjs";
import {catchError, tap} from "rxjs/operators";
import {Package} from "../interfaces/package";
import {User} from "../interfaces/user";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class StatService {
  constructor(private http: HttpClient){}

  getInsolventUsers():Observable<User[]>{
    return this.http.get<User[]>("/api/orders/users/insolvent",httpOptions)
      .pipe(
        tap(() => console.log('retrieved insolvent users')),
        catchError(this.handleError<User[]>('getInsolventUsers'))
      );
  }

  getAlerts():Observable<User[]>{
    return this.http.get<User[]>("/api/orders/alerts/",httpOptions)
      .pipe(
        tap(() => console.log('retrieved alters')),
        catchError(this.handleError<User[]>('getAlerts'))
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
