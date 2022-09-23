import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthguardGuard } from '../common-lib/guard/authguard.guard';
import { AddEditCompanyComponent } from './add-edit-company/add-edit-company.component';
import { AddEditMedicineComponent } from './add-edit-medicine/add-edit-medicine.component';
import { CompanyDetainViewComponent } from './company-detain-view/company-detain-view.component';
import { CompanyComponent } from './company/company.component';
import { InvoiceComponent } from './invoice/invoice.component';
import { MedicineViewComponent } from './medicine-view/medicine-view.component';
import { MedicineComponent } from './medicine/medicine.component';
import { SalesComponent } from './sales/sales.component';

const routes: Routes = [
  {
    path: "company", component: CompanyComponent, pathMatch: "full", title: "company", canActivate: [AuthguardGuard]
  },
  {
    path: "home", component: CompanyComponent, pathMatch: "full", title: "home", canActivate: [AuthguardGuard]
  },
  {
    path: "invoice", component: InvoiceComponent, pathMatch: "full", title: "invoice", canActivate: [AuthguardGuard]
  },
  {
    path: "addCompany", component: AddEditCompanyComponent, pathMatch: "full", title: "addCompany", canActivate: [AuthguardGuard]
  },
  {
    path: "addCompany/:id", component: AddEditCompanyComponent, pathMatch: "full", canActivate: [AuthguardGuard]
  }
  ,
  {
    path: "medicine", component: MedicineComponent, pathMatch: "full", canActivate: [AuthguardGuard]
  },
  {
    path: "medicineAdd", component: AddEditMedicineComponent, pathMatch: "full", canActivate: [AuthguardGuard]
  },
  {
    path: "medicineAdd/:id", component: AddEditMedicineComponent, pathMatch: "full", canActivate: [AuthguardGuard]
  },
  {
    path: "medicineView/:id", component: MedicineViewComponent, pathMatch: "full", canActivate: [AuthguardGuard]
  }
  ,
  {
    path: "companyView/:id", component: CompanyDetainViewComponent, pathMatch: "full", canActivate: [AuthguardGuard]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FeaturesRoutingModule { }
