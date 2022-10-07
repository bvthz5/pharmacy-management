import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/common-lib/service/api.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.css']
})
export class CompanyComponent implements OnInit {

  constructor(private service:ApiService, private router:Router,private toastr:ToastrService) { }
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
    this.service.deleteCompany(data).subscribe({
      next: (res:any) =>{
      alert("Deleted Successfully")
      window.location.reload();
      console.log(res)
      },
      error: (error: any) => {
        alert("Error: " + error.message);
        console.log(error)
      }


    })
  }
  // show()
  // {
  //   this.toastr.success('hurray!!!','Hellooo');
  // }

  Method(data:any,data2:any){



    if(window.confirm("Are You Sure To Delete :"+data)) {


      console.log("Implement delete functionality here");

        this.onDelete(data2);




  }
}
ComView(id: any) {
  this.service.getCompanyDeatails(id).subscribe({
    next: (response: any) => {

      console.log('Success', response);

    },
    error: (error: any) => {
      this.companyDeatils = error.message;
      if(this.companyDeatils==0){
        this.showToastWarn()+error.message;
      }

      console.log('error', error);
    }
  })

}
showToastSuccess2() {
  this.toastr.success("Deleted Successfully","Success",{timeOut: 800,positionClass: "toast-top-center"});
}


showToastWarn(){
  this.toastr.warning("Error","Warning",{timeOut: 800,positionClass: "toast-top-center"});
}


}
