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
  companyDeatils:any
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
  Method(data:any,data2:any){ {

    if(confirm("Are You Sure To Delete :"+data)) {
      console.log("Implement delete functionality here");

        this.onDelete(data2);


    }

  }
}
ComView(id: any) {
  this.service.getCompanyDeatails(id).subscribe({
    next: (response: any) => {

      console.log('Success', response);
      this.companyDeatils = response


    },
    error: (error: any) => {
      console.log('error', error);
    }
  })

}

}
