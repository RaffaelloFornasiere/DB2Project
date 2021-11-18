import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { MainNavComponent } from './main-nav/main-nav.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { PackagesComponent } from './pages/packages/packages.component';
import { SettingsComponent } from './pages/settings/settings.component';
import { ChartComponent } from './components/chart/chart.component';
import { PackageComponent } from './components/package/package.component';
import { PackageDetailsComponent } from './pages/package-details/package-details.component';
import { HttpClientModule } from '@angular/common/http';
import {MatCardModule} from "@angular/material/card";
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { UserSettingsComponent } from './pages/user-settings/user-settings.component';
import { AdminSettingsComponent } from './pages/admin-settings/admin-settings.component';
import {authInterceptorProviders} from "./helpers/auth.interceptor";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { ProfileComponent } from './components/profile/profile.component';
import {FormControl} from '@angular/forms';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule, MatSelectTrigger} from "@angular/material/select";
import {ErrorStateMatcher, ShowOnDirtyErrorStateMatcher} from "@angular/material/core";

@NgModule({
  declarations: [
    AppComponent,
    MainNavComponent,
    DashboardComponent,
    PackagesComponent,
    SettingsComponent,
    ChartComponent,
    PackageComponent,
    PackageDetailsComponent,
    LoginComponent,
    RegisterComponent,
    UserSettingsComponent,
    AdminSettingsComponent,
    ProfileComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatFormFieldModule,
    MatToolbarModule,
    MatSelectModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatCardModule,

    FormsModule,

    HttpClientModule,

    ReactiveFormsModule,
  ],
  providers: [authInterceptorProviders,
    {provide: ErrorStateMatcher, useClass: ShowOnDirtyErrorStateMatcher}]
  ,
  bootstrap: [AppComponent]
})
export class AppModule { }
