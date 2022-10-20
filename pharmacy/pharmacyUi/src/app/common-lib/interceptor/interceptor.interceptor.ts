import { Injectable, Injector } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { delay, finalize, Observable } from 'rxjs';
import { ApiService } from '../service/api.service';


@Injectable()
export class InterceptorInterceptor implements HttpInterceptor {

  constructor(private injector:Injector) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    let Service = this.injector.get(ApiService)
    let token = localStorage.getItem("accessToken")
    if (token) {
      request = request.clone({
        setHeaders: {

          'Authorization': 'Pharmacy ' + token
        }
      });
    }
    Service.requestStarted();
    return next.handle(request).pipe(delay(1600),
    finalize(() => Service.requestEnded()));
  }
}
