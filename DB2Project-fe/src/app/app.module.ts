import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BreakpointObserver, LayoutModule} from '@angular/cdk/layout';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatIconModule} from '@angular/material/icon';
import {MatListModule} from '@angular/material/list';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {AdminDashboardComponent} from './components/admin/admin-dashboard/admin-dashboard.component';
import {ChartComponent} from './components/chart/chart.component';
import {PackageDetailsComponent} from './components/package-details/package-details.component';
import {HttpClientModule} from '@angular/common/http';
import {MatCardModule} from "@angular/material/card";
import {LoginComponent} from './components/login/login.component';
import {RegisterComponent} from './components/register/register.component';
import {UserSettingsComponent} from './components/user/user-settings/user-settings.component';
import {AdminSettingsComponent} from './components/admin/admin-settings/admin-settings.component';
import {authInterceptorProviders} from "./helpers/auth.interceptor";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ProfileComponent} from './components/profile/profile.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";
import {ErrorStateMatcher, MatNativeDateModule, ShowOnDirtyErrorStateMatcher} from "@angular/material/core";
import {UserDashboardComponent} from "./components/user/user-dashboard/user-dashboard.component";
import {ChartsModule} from "ng2-charts";
import {HomeComponent} from './components/home/home.component';
import {CarouselModule} from "primeng/carousel";
import {ButtonModule} from "primeng/button";
import {DataViewModule} from 'primeng/dataview';
import {MatDialogModule} from "@angular/material/dialog";
import {BuyDialogComponent} from './components/buy-dialog/buy-dialog.component';
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {ConfirmationComponent} from './components/confirmation/confirmation.component';
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatInputModule} from "@angular/material/input";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatTabsModule} from "@angular/material/tabs";
import {ResultComponent} from './components/result/result.component';
import {MAT_SNACK_BAR_DEFAULT_OPTIONS, MatSnackBarModule} from "@angular/material/snack-bar";
import {NgxJsonViewerModule} from "ngx-json-viewer";
import {MatTableModule} from "@angular/material/table";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatExpansionModule} from "@angular/material/expansion";


@NgModule({
  declarations: [
    AppComponent,
    AdminDashboardComponent,
    UserDashboardComponent,
    ChartComponent,
    PackageDetailsComponent,
    LoginComponent,
    RegisterComponent,
    UserSettingsComponent,
    AdminSettingsComponent,
    ProfileComponent,
    HomeComponent,
    BuyDialogComponent,
    ConfirmationComponent,
    ResultComponent,

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
    ChartsModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    CarouselModule,
    ButtonModule,
    DataViewModule,
    MatDialogModule,
    MatAutocompleteModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatInputModule,
    MatCheckboxModule,
    LayoutModule,
    MatTabsModule,
    MatSnackBarModule,
    NgxJsonViewerModule,
    MatTableModule,
    MatPaginatorModule,
    MatExpansionModule,
  ],
  providers: [authInterceptorProviders, MatDatepickerModule,
    {provide: ErrorStateMatcher, useClass: ShowOnDirtyErrorStateMatcher},
    {provide: MAT_SNACK_BAR_DEFAULT_OPTIONS, useValue: {duration: 2500}}],
  bootstrap: [AppComponent],
})
export class AppModule {
}
