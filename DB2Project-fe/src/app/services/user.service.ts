import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {TokenStorageService} from "./token-storage.service";
import {retry} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private tokenService: TokenStorageService) { }

  getPages():{title: string, link: string}[]{
    let user = this.tokenService.getUser();
    if( user && user.roles[0] == 'ROLE_USER')
      return    [{title: "Dashboard", link: "/user-dashboard"},
        {title: "Settings", link: "/user-settings"},
        {title: "Profile", link: "/user-profile"}];
    if( user && user.roles[0] == 'ROLE_ADMIN')
      return [
        {title: "Dashboard", link: "/admin-dashboard"},
        {title: "Settings", link: "/admin-settings"},
        {title: "Profile", link: "/admin-profile"}];
    else
      return [{title: "Dashboard", link: "/dashboard"}]
  }
}
