import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CompanyComponent } from './company/company.component';
import { SalesComponent } from './sales/sales.component';

const routes: Routes = [
  {
    path:"company",component:CompanyComponent,pathMatch:"full",title:"company"
  },
  {
    path:"home",component:CompanyComponent,pathMatch:"full",title:"home"
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FeaturesRoutingModule { }
