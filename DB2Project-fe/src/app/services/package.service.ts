import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable, of} from "rxjs";
import {Package} from "../interfaces/package";
import { catchError, map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PackageService {
  private packagesUrl = "api/home/packages"
  constructor(
    private http: HttpClient
  ) { }


  getPackages(): Observable<Package[]>{
    return this.http.get<Package[]>(this.packagesUrl)
      .pipe(
        // tap(_ => console.log('fetched heroes')),
        catchError(this.handleError<Package[]>('getPackages', []))
      );
  }

  getDetails(id: number): Observable<Package>{
    return this.http.get<Package>(this.packagesUrl+'/detail/' + id)
      .pipe(
        // tap(_ => console.log('fetched heroes')),
        catchError(this.handleError<Package>('getPackageDetail'))
      );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.log("error: " + error); // log to console instead

      // TODO: better job of transforming error for user consumption
      console.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
