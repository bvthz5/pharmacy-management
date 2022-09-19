import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http:HttpClient ) { }
  api_url=environment.api_url

  /**
   * Login user
   */
  loginUser(data:any){
    return this.http.post(this.api_url+"/login",data)
  }

  /**
   * get All medicne List
   */
  getMedineList(){
    return this.http.get(this.api_url+"/medicine")
  }
  addSale(data:any){
    return this.http.post(this.api_url+"/sales",data)
  }
}
