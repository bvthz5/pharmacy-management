import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }
  api_url = environment.api_url
  sales: any
  medId: any

  /**
   * Login user
   *
   */

  getToken(){
    return localStorage.getItem('accessToken')
  }

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

  getCurrentUserDetails() {
    return this.http.get(this.api_url + "/login")
  }
  getSalesData(data: any, data1: any, sort: any): Observable<any> {

    return this.http.get(this.api_url + "/sales/" + data + "/" + data1 + "/" + sort);
  }

  download(): Observable<Blob> {
    return this.http.get(this.api_url + "/sales/export", { responseType: 'blob' });
  }

  filter(days: any): Observable<any> {
    return this.http.get(this.api_url + "/sales/filter/" + days);
  }
  getExpiredMedicine(){
    return this.http.get(this.api_url+"/medicine/expired")
  }

  resetPswrd(data :any,token:any){
    return this.http.post(this.api_url+"/login/forgotPswrd/" + token, data);
  }

  forgotPswrd(data: any): Observable<any> {
    return this.http.post(this.api_url+"/login/resetPswrd", data);
  }

// -----


  getUserDetails(){
    return this.http.get(this.api_url+"/login/detail");
  }

  changePswd(data:any):Observable<any>{
    return this.http.put(this.api_url+"/login/changepswrd",data)
  }

  uploadImage(selectedFile:any):Observable<any>{
    return this.http.put(this.api_url + "/login/profilePic",selectedFile);
  }

  getProfilePic():Observable<any>{
    return this.http.get(this.api_url + "/login/getImage",{ responseType: "blob" })

  }
  // ---spinner---

  private count = 0;
  private spinner$ = new BehaviorSubject<string>('');

  getSpinnerObserver(): Observable<String> {
    return this.spinner$.asObservable();
  }

  requestStarted() {
    if (++this.count === 1) {
      this.spinner$.next('start');
    }
  }

  requestEnded() {
    if (this.count === 0 || --this.count === 0) {
      this.spinner$.next('stop');
    }
  }

  resetSpinner() {
    this.count = 0;
    this.spinner$.next('stop');
  }

  getJobs(queryParam: HttpParams): Observable<any> {
    return this.http.get(`${environment.api_url}/company`, { params: queryParam });
  }

}
