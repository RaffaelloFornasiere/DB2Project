import {Component, OnInit} from '@angular/core';
import {finalize, Observable} from "rxjs";
import {BreakpointObserver, Breakpoints} from "@angular/cdk/layout";
import {map, shareReplay} from "rxjs/operators";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {AppService} from "./services/app.service";
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import Validation from "./helpers/validation";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  form: FormGroup;
  submitted = false;

  title = 'DB2Project-fe';
  constructor(private app: AppService,
              private http: HttpClient,
              private router: Router,

              private formBuilder: FormBuilder) {
    this.app.authenticate(undefined, undefined);
  }
  logout() {
    this.http.post('logout', {})
      .pipe(finalize(() => {
      this.app.authenticated = false;
      this.router.navigateByUrl('/login');
    })).subscribe();
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group(
      {
        fullname: ['', Validators.required],
        username: [
          '',
          [
            Validators.required,
            Validators.minLength(6),
            Validators.maxLength(40)
          ]
        ],
        password: [
          '',
          [
            Validators.required,
            Validators.minLength(6),
          ]
        ],
        confirmPassword: ['', Validators.required],
        acceptTerms: [false, Validators.requiredTrue]
      },
      {
        validators: [Validation.match('password', 'confirmPassword')]
      }
    )
  }

  get f(): {[key: string]: AbstractControl}{
    return this.form.controls;
  }

  onSubmit(): void{
    this.submitted = true;
    if(this.form.invalid)
      return;
    console.log(JSON.stringify(this.form.value, null, 2))
  }

  onReset(): void{
    this.submitted = false;
    this.form.reset();
  }

}
