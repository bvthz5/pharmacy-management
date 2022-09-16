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


@NgModule({
  declarations: [
    DashboardComponent,
    HeaderComponent,
    CompanyComponent,
    SalesComponent
  ],
  imports: [
    CommonModule,
    FeaturesRoutingModule,

  ],
  providers: [
    {
      provide:HTTP_INTERCEPTORS,
      useClass:InterceptorInterceptor,
      multi:true}
  
  ],
})
export class FeaturesModule { }
