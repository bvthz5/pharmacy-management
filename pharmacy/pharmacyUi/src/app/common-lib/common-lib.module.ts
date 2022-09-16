import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { InterceptorInterceptor } from './interceptor/interceptor.interceptor';




@NgModule({
  declarations: [
   
  ],
  imports: [
    CommonModule
  ],
  providers: [
    {
      provide:HTTP_INTERCEPTORS,
      useClass:InterceptorInterceptor,
      multi:true}
  
  ],
})
export class CommonLibModule { }
