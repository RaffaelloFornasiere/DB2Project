import { Injectable } from '@angular/core';
import {workspaceSchemaPath} from "@angular/cli/utilities/config";
import {Subject} from "rxjs";
import {User} from "../interfaces/user";

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';


@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() { }

  signOut(): void {
    window.sessionStorage.clear();
    this.authenticated.next(false);
  }

  public saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY)
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  authenticated: Subject<boolean> = new Subject<boolean>();
  public getToken(): string | null {
    return window.sessionStorage.getItem(TOKEN_KEY);
  }

  isAuthenticated():boolean{
    return this.getUser() != undefined;
  }

  public saveUser(user: any): void{
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
    this.authenticated.next(true)
  }
  public getUser(): any {
    let user = window.sessionStorage.getItem(USER_KEY);
    if(user)
      return JSON.parse(user);

  }
}
