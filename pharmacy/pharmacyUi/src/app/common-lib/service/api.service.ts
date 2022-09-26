import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }
  api_url = environment.api_url
  sales: any

  /**
   * Login user
   */
  loginUser(data: any) {
    return this.http.post(this.api_url + "/login", data)
  }

  /**
   * get All medicne List
   */
  getMedineList() {
    return this.http.get(this.api_url + "/medicine")
  }
  addSale(data: any) {
    return this.http.post(this.api_url + "/sales", data)
  }
  /**
   * get sale details after adding it to sales table
   */
  getsale(data: any) {
    this.sales = data
  }
  async returnSale() {
    return await this.sales
  }

  getCompany() {
    return this.http.get(this.api_url + "/company")
  }

  deleteCompany(data: any) {
    return this.http.delete(this.api_url + "/company/" + data)
  }

  getCompanyDeatails(companyId: any) {
    return this.http.get(this.api_url + "/company/" + companyId)
  }

  addCompany(data: any) {
    return this.http.post(this.api_url + "/company", data)
  }

  updateCompany(data: any, companyId: any) {
    return this.http.put(this.api_url + "/company/" + companyId, data)
  }
  medicineList() {
    return this.http.get(this.api_url + '/medicine')
  }

  deleteMedicine(data: any) {
    return this.http.delete(this.api_url + '/medicine/' + data)
  }
  medicinedetails(id: any) {
    return this.http.get(this.api_url + "/medicine/" + id)

  }
  getMedicineDetails(id: any) {
    return this.http.get(this.api_url + "/medicine/" + id)
  }

  AddMedicine(info: any): Observable<any> {
    return this.http.post(this.api_url + '/medicine', info)
  }
  updatemedicine(data: any, medicineId: any) {
    return this.http.put(this.api_url + "/medicine/" + medicineId, data)
  }

  getCurrentUserDetails(){
    return this.http.get(this.api_url + "/login")
  }
}
