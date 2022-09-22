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
  api_url=environment.api_url
  

  loginUser(data:any)
  {
    return this.http.post(this.api_url+"/login",data)
  }
  
  getSalesData(data:any,data1:any): Observable<any> 
  {
    
    return this.http.get(this.api_url+"/sales/"+data+"/"+data1);
  }

  download(): Observable<Blob> 
  {
    return this.http.get(this.api_url+"/sales/export", {responseType: 'blob'});
  }
}