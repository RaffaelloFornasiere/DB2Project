import {Injectable} from '@angular/core';
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class NavbarService {
  isLoggingInWarnVisible = false;

  loggingVisibilityChange: Subject<boolean> = new Subject<boolean>();
  constructor() {
    this.loggingVisibilityChange.subscribe((value) => {
      this.isLoggingInWarnVisible = value
    });
  }

  toggleSidebarVisibility(value: boolean) {
    this.loggingVisibilityChange.next(this.isLoggingInWarnVisible = value);
  }


}
