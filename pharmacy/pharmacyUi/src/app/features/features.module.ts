import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FeaturesRoutingModule } from './features-routing.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CommonLibModule } from '../common-lib/common-lib.module';
import { HeaderComponent } from './header/header.component';
import { CompanyComponent } from './company/company.component';
import { SalesComponent } from './sales/sales.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { InterceptorInterceptor } from '../common-lib/interceptor/interceptor.interceptor';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { InvoiceComponent } from './invoice/invoice.component';
import { AddEditCompanyComponent } from './add-edit-company/add-edit-company.component';
import { MedicineComponent } from './medicine/medicine.component';
import { AddEditMedicineComponent } from './add-edit-medicine/add-edit-medicine.component';
import { MedicineViewComponent } from './medicine-view/medicine-view.component';
import { CompanyDetainViewComponent } from './company-detain-view/company-detain-view.component';
import { SaleshistoryComponent } from './saleshistory/saleshistory.component';
import { MedicineViewmodalComponent } from './medicine-viewmodal/medicine-viewmodal.component';


@NgModule({
  declarations: [
    DashboardComponent,
    HeaderComponent,
    CompanyComponent,
    SalesComponent,
    InvoiceComponent,
    AddEditCompanyComponent,
    MedicineComponent,
    AddEditMedicineComponent,
    MedicineViewComponent,
    CompanyDetainViewComponent,
    SaleshistoryComponent,
    MedicineViewmodalComponent
  ],
  imports: [
    CommonModule,
    FeaturesRoutingModule,
    ReactiveFormsModule,
    FormsModule,

  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: InterceptorInterceptor,
      multi: true
    }

  ],
})
export class FeaturesModule { }
