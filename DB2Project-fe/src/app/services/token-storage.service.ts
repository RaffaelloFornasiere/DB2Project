import { Injectable } from '@angular/core';
import {workspaceSchemaPath} from "@angular/cli/utilities/config";
import {Subject} from "rxjs";

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';


@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() { }

  signOut(): void {
    window.sessionStorage.clear();
    this.isAuthenticated.next(false);
  }

  public saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY)
    console.log("saving token: " + token)
    window.sessionStorage.setItem(TOKEN_KEY, token);
    this.isAuthenticated.next(true)
  }

  isAuthenticated: Subject<boolean> = new Subject<boolean>();
  public getToken(): string | null {
    return window.sessionStorage.getItem(TOKEN_KEY);
  }

  public saveUser(user: any): void{
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }
  public getUser(): any {
    const user = window.sessionStorage.getItem(USER_KEY);
    if(user){
      return JSON.parse(user);
    }
  }
}
