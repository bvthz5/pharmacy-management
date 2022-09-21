import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiService } from 'src/app/common-lib/service/api.service';

@Component({
  selector: 'app-add-edit-company',
  templateUrl: './add-edit-company.component.html',
  styleUrls: ['./add-edit-company.component.css']
})
export class AddEditCompanyComponent implements OnInit {

  constructor(private service: ApiService, private router: Router, private activatedRoute: ActivatedRoute) { }
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
      phone: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required),
    })
  }
  addCompany() {
    if (this.companyGroup.valid) {
      this.service.addCompany(this.companyGroup.value).subscribe({
        next: (res: any) => {
          console.log(res)
          alert("Successfully Inserted");

          this.router.navigateByUrl('/company');
        },
        error: (error: any) => console.log(error)

      });
    } else {
      alert("Enter a valid form")
    }
  }
  updateCompany(companyId:any) {
    if (this.companyGroup.valid) {
      this.service.updateCompany(this.companyGroup.value,companyId).subscribe({
        next:(response:any)=>{
        console.log(response)
        alert("Successfully updated!");
        this.router.navigateByUrl("company");
        },
        error:(err:any)=> console.log(err)


      })
    }
  }

}
