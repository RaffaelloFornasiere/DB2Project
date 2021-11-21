import {HTTP_INTERCEPTORS, HttpErrorResponse, HttpEvent, HttpResponse} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {HttpInterceptor, HttpHandler, HttpRequest} from "@angular/common/http";

import {TokenStorageService} from "../services/token-storage.service";
import {Observable, of, throwError} from "rxjs";
import {Router} from "@angular/router";
import {catchError, map} from "rxjs/operators";
import {AppComponent} from "../app.component";
import {NavbarService} from "../services/navbar.service";
// import {callback} from "chart.js/helpers";

const TOKEN_HEADER_KEY = 'Authorization';       // for Spring Boot back-end

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private token: TokenStorageService,
              private router: Router,
              private navbarService: NavbarService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authReq = req;
    const token = this.token.getToken();

    if (token != null) {
      authReq = req.clone(
        {headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token)});
    }
    return next.handle(authReq).pipe(
      map((event: HttpEvent<any>) => this.allOk(event)),
      catchError(x => this.handleAuthError(x)));
  }

  needsLogin = false;

  private handleAuthError(err: HttpErrorResponse): Observable<any> {
    //handle your auth error or rethrow
    if (err.status === 401 || err.status === 403) {
      this.navbarService.toggleSidebarVisibility(true);
      this.router.navigateByUrl("login");
      return of(err.message); // or EMPTY may be appropriate here
    }
    return throwError(err);
  }

  private allOk(data: HttpEvent<any>): HttpEvent<any> {
    if (data instanceof HttpResponse && data.status === 200)
      this.navbarService.toggleSidebarVisibility(false);
    return data;
  }
}

export const authInterceptorProviders = [
  {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}
];






