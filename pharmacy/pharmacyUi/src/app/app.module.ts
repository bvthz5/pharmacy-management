import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { CommonLibModule } from './common-lib/common-lib.module';
import { FeaturesModule } from './features/features.module';
import { InterceptorInterceptor } from './common-lib/interceptor/interceptor.interceptor';

@NgModule({
  declarations: [
    AppComponent,
   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
   FeaturesModule
    
    
  ],
  providers: [
    {
      provide:HTTP_INTERCEPTORS,
      useClass:InterceptorInterceptor,
      multi:true}
  
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
