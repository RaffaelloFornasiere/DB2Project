import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../interfaces/user";
import {catchError, tap} from "rxjs/operators";
import {Order} from "../interfaces/Order";
import Utils from "../Utils";

const AUTH_API = 'api/auth/';
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};


//This service sends signup, login HTTP POST requests to back-end
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {
  }

  login(username: string, password: string): Observable<any> {
    return this.http.post(AUTH_API + 'login',
      {username, password},
      httpOptions);
  }

  editUser(user: User):Observable<User>{
    return this.http.post<User>("/api/users/edit", user, httpOptions)
  }

  register(user: any): Observable<any> {
    return this.http.post(AUTH_API + 'signup',
      user, httpOptions)
  }

  checkUsername(username: string):Observable<boolean>{
    return this.http.get<boolean>("/api/home/users/check-username/" + username)
  }

}

