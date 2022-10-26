import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
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
import { ExpiredMedicineComponent } from './expired-medicine/expired-medicine.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { UserProPicComponent } from './user-pro-pic/user-pro-pic.component';
import { SpinnerComponent } from './spinner/spinner.component';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';




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
    MedicineViewmodalComponent,
    ExpiredMedicineComponent,
    ForgotPasswordComponent,
    UserProPicComponent,
    SpinnerComponent
  ],
  imports: [
    CommonModule,
    FeaturesRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserAnimationsModule,
    MatProgressBarModule,
    InfiniteScrollModule,
    ToastrModule.forRoot({
      timeOut: 1000,
      progressBar: true,
      progressAnimation: 'increasing',
      preventDuplicates: true
    })


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
