import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { ApiService } from '../service/api.service';

@Injectable({
  providedIn: 'root'
})
export class UserTypeGuard implements CanActivate {
  constructor(private router:Router,private service :ApiService){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      let accessToken=localStorage.getItem("accessToken")
      let userType=localStorage.getItem("type")
      
      if(accessToken!=null && userType==="admin"){
        return true;
      }
      else{
        this.router.navigateByUrl("/home")
        return false
      }
    
  }


  
}
