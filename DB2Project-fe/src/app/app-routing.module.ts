import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AdminDashboardComponent} from "./components/admin/admin-dashboard/admin-dashboard.component";
import {UserSettingsComponent} from "./components/user/user-settings/user-settings.component";
import {AdminSettingsComponent} from "./components/admin/admin-settings/admin-settings.component";
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {ProfileComponent} from "./components/profile/profile.component";
import {UserDashboardComponent} from "./components/user/user-dashboard/user-dashboard.component";
import {HomeComponent} from "./components/home/home.component";
import {PackageDetailsComponent} from "./components/package-details/package-details.component";
import {ConfirmationComponent} from "./components/confirmation/confirmation.component";
import {ResultComponent} from "./components/result/result.component";


const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'detail', component: PackageDetailsComponent},
  { path: 'confirm', component: ConfirmationComponent},
  { path: 'result', component: ResultComponent},

  { path: 'admin-dashboard', component: AdminDashboardComponent},
  { path: 'admin-settings', component: AdminSettingsComponent},
  { path: "admin-profile", component: ProfileComponent},

  { path: 'user-dashboard', component: UserDashboardComponent},
  { path: 'user-settings', component: UserSettingsComponent},
  { path: "user-profile", component: ProfileComponent},

  { path: "user", component: UserSettingsComponent},
  { path: "admin", component: AdminSettingsComponent},

  { path: "login", component: LoginComponent},
  { path: "register", component: RegisterComponent},
  { path: "profile", component: ProfileComponent},
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
