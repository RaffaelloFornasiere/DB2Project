import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AdminDashboardComponent} from "./pages/admin/admin-dashboard/admin-dashboard.component";
import {PackagesComponent} from "./pages/packages/packages.component";
import {SettingsComponent} from "./pages/settings/settings.component";
import {UserSettingsComponent} from "./pages/user/user-settings/user-settings.component";
import {AdminSettingsComponent} from "./pages/admin/admin-settings/admin-settings.component";
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {ProfileComponent} from "./components/profile/profile.component";


const routes: Routes = [
  { path: '', redirectTo: '/admin-dashboard', pathMatch: 'full' },
  { path: 'admin-dashboard', component: AdminDashboardComponent},
  { path: 'packages', component: PackagesComponent},
  { path: 'settings', component: SettingsComponent},
  { path: "user", component: UserSettingsComponent},
  { path: "admin", component: AdminSettingsComponent},

  { path: "login", component: LoginComponent},
  { path: "register", component: RegisterComponent},
  { path: "profile", component: ProfileComponent}
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
