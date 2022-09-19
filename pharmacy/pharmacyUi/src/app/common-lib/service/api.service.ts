import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ApiService 
{



  constructor(private http:HttpClient ) { }
  AccessToken!: any;
  value!: any;
  valueContactinsert!: any;
  valueItem!: any;
  valueContactEdit!: any;
  AT:any;

  api_url=environment.api_url
  getHeader() 
  {
    this.AccessToken = localStorage.getItem('accessToken');
    console.log(this.AccessToken);
    
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Headers': 'Content-Type',
      'Access-Control-Allow-Methods': 'GET,POST,OPTIONS,DELETE,PUT',
      'Authorization': 'Pharmacy ' + this.AccessToken
    });
    return headers;
  }

  /**
   * Login user
   */
  loginUser(data:any)
  {
    
    return this.http.post(this.api_url+"/login",data)
  }
  
  getSalesData(data:any,data1:any): Observable<any> 
  {
    const headers = this.getHeader();
    return this.http.get(this.api_url+"/sales/"+data+"/"+data1, { headers });
  }
}