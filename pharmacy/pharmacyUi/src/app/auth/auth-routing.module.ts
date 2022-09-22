import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from '../features/dashboard/dashboard.component';
import { LoginComponent } from './login/login.component';
import { SaleshistoryComponent } from './saleshistory/saleshistory.component';

const routes: Routes = [
  { path: "", redirectTo: "login", pathMatch: "full" },
  { path: "login", component: LoginComponent, pathMatch: "full", title: "login" },
  { path: "dashBoard", component: DashboardComponent, pathMatch: "full", title: "login" },
  { path: "saleshistory", component: SaleshistoryComponent, title: "login" }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthRoutingModule { }
