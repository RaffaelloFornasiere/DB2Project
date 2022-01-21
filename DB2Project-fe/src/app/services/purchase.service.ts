import { Injectable } from '@angular/core';
import {Package} from "../interfaces/package";
import {User} from "../interfaces/user";
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {TokenStorageService} from "./token-storage.service";
import {Order} from "../interfaces/Order";



const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};
@Injectable({
  providedIn: 'root'
})
export class PurchaseService {

  constructor(private http: HttpClient,
              private tokenStorageService: TokenStorageService) { }

  buy(o: Order):Observable<any>{
    return this.http.post("/orders/save", {"package": o, "user": this.tokenStorageService.getUser()}
    ,httpOptions);
  }
}
