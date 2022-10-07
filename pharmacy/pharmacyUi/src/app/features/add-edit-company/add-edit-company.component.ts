import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiService } from 'src/app/common-lib/service/api.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-add-edit-company',
  templateUrl: './add-edit-company.component.html',
  styleUrls: ['./add-edit-company.component.css']
})
export class AddEditCompanyComponent implements OnInit {

  constructor(private service: ApiService, private router: Router, private activatedRoute: ActivatedRoute , public toastr:ToastrService) { }
  companyGroup!: FormGroup
  id: any
  companyDetails: any = {

  }
  ngOnInit(): void {
    if (this.activatedRoute.snapshot.paramMap.get('id') != null) {
      this.id = this.activatedRoute.snapshot.paramMap.get('id')
      console.log(this.id);

      this.service.getCompanyDeatails(this.id).subscribe({
        next: (response: any) => {

          console.log('Success', response);
          this.companyDetails = response
          this.companyGroup.patchValue(this.companyDetails)
        },
        error: (error: any) => {
          console.log('error', error);
        },
        complete: () => {
          console.log('Completed');
        },
      })
      console.log(this.companyDetails);


    }
    this.companyGroup = new FormGroup({
      name: new FormControl('', [Validators.required]),
      address: new FormControl('', Validators.required),
      phone: new FormControl('', [Validators.required,Validators.pattern("^[0-9]{10}$")]),
      description: new FormControl('', Validators.required),
    })
  }
  bv:any;
  addCompany() {
    this.companyGroup.markAllAsTouched()
    if (this.companyGroup.valid) {
      this.service.addCompany(this.companyGroup.value).subscribe({
        next: (res: any) => {
          this.bv=res;
          if(this.bv != 0){
            this.showToastSuccess();

          }
          console.log(res)


          this.router.navigateByUrl('/company');
        },
        error: (error: any) =>{
          this.bv=error.message;
          if(this.bv==null){
            this.showToastWarn()+error.message;
          }
          console.log(error)}


      });
    }

  }
  updateCompany(companyId:any) {
    this.companyGroup.markAllAsTouched()
    if (this.companyGroup.valid) {
      this.service.updateCompany(this.companyGroup.value,companyId).subscribe({
        next:(response:any)=>{
          this.bv=response.data;
          if(this.bv != 0){
            this.showToastSuccess2();
          }
        console.log(response)
        
        this.router.navigateByUrl("company");
        },
        error:(err:any)=> {this.bv=err;
          if(this.bv==null) {
            this.showToastWarn()+err;
          }
          console.log(err)}


      })
    }
  }

  showToastSuccess() {
    this.toastr.success("Added Successfully","Success",{timeOut: 800,positionClass: "toast-top-center"});
  }
  showToastSuccess2() {
    this.toastr.success("Updated Successfully","Success",{timeOut: 800,positionClass: "toast-top-center"});
  }


  showToastWarn(){
    this.toastr.warning("Error","Warning",{timeOut: 800,positionClass: "toast-top-center"});
  }


}
