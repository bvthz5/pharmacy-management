import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthguardGuard } from '../common-lib/guard/authguard.guard';
import { LoginGuard } from '../common-lib/guard/login.guard';
import { DashboardComponent } from '../features/dashboard/dashboard.component';
import { SalesComponent } from '../features/sales/sales.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  { path: "", component: LoginComponent, pathMatch: "full" ,canActivate:[LoginGuard] },
  { path: "login", component: LoginComponent, pathMatch: "full", title: "login",canActivate:[LoginGuard] },
  { path: "home", component: SalesComponent, pathMatch: "full", title: "home",canActivate:[AuthguardGuard] }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule { }
