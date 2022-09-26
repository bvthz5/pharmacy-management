import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/common-lib/service/api.service';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.css']
})
export class CompanyComponent implements OnInit {

  constructor(private service:ApiService, private router:Router) { }
  companyData:any=[];
  userType:any=localStorage.getItem("type")

  ngOnInit(): void {

    this.service.getCompany().subscribe((data:any)=>{
      console.log(data)
      this.companyData=data
    })
  }

  onDelete(data :any){
    this.service.deleteCompany(data).subscribe((res:any)=>{
      alert("Deleted Successfully")
      window.location.reload();
      console.log(res)

    })
  }
}
