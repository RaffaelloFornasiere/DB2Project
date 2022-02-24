import { Injectable } from '@angular/core';
import {Subject} from "rxjs";


/**
 * TokenStorageService
 * This service manages token and user information in Browser's session storage.
 * When we log out the session is cleared
 */
@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  /**
   * these are the keys used to access the session storage
   */
  public readonly TOKEN_KEY = 'auth-token';
  public readonly USER_KEY = 'auth-user';

  authenticated: Subject<boolean> = new Subject<boolean>();

  constructor() { }

  signOut(): void {
    window.sessionStorage.clear();
    this.authenticated.next(false);
  }

  /**
   * saves the token in the Browser's session storage
   * @param token the jwt token
   */
  public saveToken(token: string): void {
    window.sessionStorage.removeItem(this.TOKEN_KEY)
    window.sessionStorage.setItem(this.TOKEN_KEY, token);
  }

  /**
   * retrieves the saved token (if any)
   */
  public getToken(): string | null {
    return window.sessionStorage.getItem(this.TOKEN_KEY);
  }

  /**
   * returns true if some user is authenticated
   */
  isAuthenticated():boolean{
    return this.getUser() != undefined;
  }

  /**
   * saves the user data inside the browser's session storage
   * @param user: the user to be saved
   */
  public saveUser(user: any): void{
    window.sessionStorage.removeItem(this.USER_KEY);
    window.sessionStorage.setItem(this.USER_KEY, JSON.stringify(user));
    this.authenticated.next(true)
  }

  /**
   * retrieves the current user (if any)
   */
  public getUser(): any {
    let user = window.sessionStorage.getItem(this.USER_KEY);
    if(user)
      return JSON.parse(user);

  }
}
