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
    // MainNavComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
