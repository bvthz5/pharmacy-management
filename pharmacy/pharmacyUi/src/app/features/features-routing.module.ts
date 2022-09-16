import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthguardGuard } from '../common-lib/guard/authguard.guard';
import { CompanyComponent } from './company/company.component';
import { SalesComponent } from './sales/sales.component';

const routes: Routes = [
  {
    path:"company",component:CompanyComponent,pathMatch:"full",title:"company",canActivate:[AuthguardGuard]
  },
  {
    path:"home",component:CompanyComponent,pathMatch:"full",title:"home",canActivate:[AuthguardGuard]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FeaturesRoutingModule { }
