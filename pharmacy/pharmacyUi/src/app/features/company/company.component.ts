import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/common-lib/service/api.service';
import { ToastrService } from 'ngx-toastr';
import { HttpParams } from '@angular/common/http';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.css']
})
export class CompanyComponent implements OnInit {
  company:any
  count: any
  total: any
  data:any

  page:number = 1
  limit:number=5;
  sortBy:string='company_Id'
  search:string='';
  closeResult: string = '';

  constructor(private service:ApiService, private router:Router,private toastr:ToastrService) { }
  companyData:any=[];
  companyDeatils:any
  userType:any=localStorage.getItem("type")

  ngOnInit(): void {

    this.service.getCompany().subscribe((data:any)=>{
      console.log(data)
      this.companyData=data

    })
    this.companyList()

  }

  onDelete(data :any){
    this.service.deleteCompany(data).subscribe({
      next: (res:any) =>{
      alert("Deleted Successfully")
      window.location.reload();
      console.log(res)
      this.companyList();
      },
      error: (error: any) => {
        alert("Error: " + error.message);
        console.log(error)
      }

    })
  }


  Method(data:any,data2:any){



    if(window.confirm("Are You Sure To Delete :"+data)) {


      console.log("Implement delete functionality here");

        this.onDelete(data2);

  }
}

companyList() {

  let queryParams = new HttpParams()
    .append('page', this.page)
    .append('limit', this.limit)
    .append('sortBy', this.sortBy)
    .append('search', this.search);

  this.service.getJobs(queryParams).subscribe({
    next: (response: any) => {
      if (response) {
        console.log(response);
        this.company = response;
        // -------------
        this.companyData=response.result;
        this.count = response.result?.length;
        this.total = response.numItems;
      }

    },
    error: (error: any) => { console.log(error) }
  })
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


numSeq(n: number): Array<number> {
  return Array(n);
}

prevPage() {
  this.page -= 1;
  this.companyList();
}

gotoPage(page: number) {
  this.page = page;
  this.companyList();
}

nextPage() {
  this.page += 1;
  this.companyList();
}
setSort(sortBy: string) {
  this.sortBy = sortBy;
  this.page = 1;
  this.companyList();
}

setLimit() {
  console.log(this.limit);
  this.companyList();
}

setSearch() {
  console.log(this.search);
  this.companyList();
}


checked: Array<number> = [];

checkedUser(event: any) {
  if (event.target.checked) {
    this.checked.push(Number(event.target.attributes.value.value));

    if (this.company.result.length == this.checked.length) {
      (document.getElementById('selectAll') as HTMLInputElement).checked =
        true;
    }
  } else {
    this.checked.splice(
      this.checked.indexOf(Number(event.target.attributes.value.value)),
      1
    );
    (document.getElementById('selectAll') as HTMLInputElement).checked =
      false;
  }

  console.log(this.checked);
}

checkAll(event: any) {
  this.company.result.forEach((element: any) => {
    (
      document.getElementById('checkbox' + element.copanyId) as HTMLInputElement
    ).checked = event.target.checked;

    if (event.target.checked) {
      if (!this.checked.includes(element.companyId)) {
        this.checked.push(element.companyId);
      }
    } else {
      this.checked.splice(this.checked.indexOf(element.companyId), 1);
    }
  });
  console.log(this.checked);
}


}
