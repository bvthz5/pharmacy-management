import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonLibModule } from './common-lib/common-lib.module';
import { FeaturesModule } from './features/features.module';
import { InterceptorInterceptor } from './common-lib/interceptor/interceptor.interceptor';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
// import { MedicineViewmodalComponent } from './medicine-viewmodal/medicine-viewmodal.component';

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
    FeaturesModule,
    NgbModule,
    BrowserAnimationsModule,
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
  bootstrap: [AppComponent]
})
export class AppModule { }
