import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DashboardComponent} from "./pages/dashboard/dashboard.component";
import {PackagesComponent} from "./pages/packages/packages.component";
import {SettingsComponent} from "./pages/settings/settings.component";
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {UserSettingsComponent} from "./pages/user-settings/user-settings.component";
import {AdminSettingsComponent} from "./pages/admin-settings/admin-settings.component";


const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent},
  { path: 'packages', component: PackagesComponent},
  { path: 'settings', component: SettingsComponent},
  { path: "user", component: UserSettingsComponent},
  { path: "admin", component: AdminSettingsComponent},

  { path: "login", component: LoginComponent},
  { path: "register", component: RegisterComponent},

]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
