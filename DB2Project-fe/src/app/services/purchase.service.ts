import { Injectable } from '@angular/core';
import {Package} from "../interfaces/package";
import {User} from "../interfaces/user";
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {TokenStorageService} from "./token-storage.service";



const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};
@Injectable({
  providedIn: 'root'
})
export class PurchaseService {

  constructor(private http: HttpClient,
              private tokenStorageService: TokenStorageService) { }

  buy(p: Package):Observable<any>{

    return this.http.post("/buy", {"package": p, "user": this.tokenStorageService.getUser()}
    ,httpOptions);
  }
}
