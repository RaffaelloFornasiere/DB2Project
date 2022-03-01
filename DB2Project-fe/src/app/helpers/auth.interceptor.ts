import {HTTP_INTERCEPTORS, HttpErrorResponse, HttpEvent, HttpResponse} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {HttpInterceptor, HttpHandler, HttpRequest} from "@angular/common/http";

import {TokenStorageService} from "../services/token-storage.service";
import {Observable, of, throwError} from "rxjs";
import {Router} from "@angular/router";
import {catchError, map} from "rxjs/operators";
import {NavbarService} from "../services/navbar.service";


/**
 * this class provides the intercept() method that inspect and transforms http requests
 * before they are sent to the server
 */
@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  readonly TOKEN_HEADER_KEY = 'Authorization';       // for Spring Boot back-end
  needsLogin = false;

  constructor(private token: TokenStorageService,
              private router: Router,
              private navbarService: NavbarService) {
  }


  /**
   * intercept() gets HTTPRequest object, change it and forward to
   * HttpHandler object’s handle() method. It transforms HTTPRequest object into an Observable<HttpEvents>.

   * @param req an HTTPRequest object
   * @param next HttpHandler object represents the next interceptor in the chain of interceptors.
   * The final 'next' in the chain is the Angular HttpClient.
   */
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authReq = req;
    const token = this.token.getToken();

    if (token != null) {
      authReq = req.clone(
        {headers: req.headers.set(this.TOKEN_HEADER_KEY, 'Bearer ' + token)});
    }
    return next.handle(authReq).pipe(
      map((event: HttpEvent<any>) => this.allOk(event)),
      catchError(x => this.handleAuthError(x)));
  }


  /**
   * if something goes wrong it checks if there is an auth error
   * and eventually leads the user to login page
   * @param err
   * @private
   */
  private handleAuthError(err: HttpErrorResponse): Observable<any> {
    //handle your auth error or rethrow
    if (err.status === 401 || err.status === 403) {
      this.token.signOut();
      this.navbarService.toggleSidebarVisibility(true);
      this.router.url
      this.router.navigate(["login"], {queryParams: {returnUrl: this.router.url}})
        .then(() => of(err.message))
      // or EMPTY may be appropriate here
    }
    return throwError(err);
  }

  /**
   * if everything is ok hides the login warning
   * @param data
   * @private
   */
  private allOk(data: HttpEvent<any>): HttpEvent<any> {
    if (data instanceof HttpResponse && data.status === 200)
      this.navbarService.toggleSidebarVisibility(false);
    return data;
  }
}

export const authInterceptorProviders = [
  {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}
];






