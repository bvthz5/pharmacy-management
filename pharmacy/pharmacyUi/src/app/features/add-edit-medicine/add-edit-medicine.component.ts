import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiService } from 'src/app/common-lib/service/api.service';

@Component({
  selector: 'app-add-edit-medicine',
  templateUrl: './add-edit-medicine.component.html',
  styleUrls: ['./add-edit-medicine.component.css']
})
export class AddEditMedicineComponent implements OnInit {

  constructor(private service: ApiService, private router: Router, private activatedRoute: ActivatedRoute) { }

  id: any;
  medicineAdd !: any;
  medicineDetails: any = {};
  companyList: any;
  brand: any
  companyId: any
  date= new Date()
  ngOnInit(): void {
    if (this.activatedRoute.snapshot.paramMap.get('id') != null) {
      this.id = this.activatedRoute.snapshot.paramMap.get('id')
      console.log(this.id);

      this.service.getMedicineDetails(this.id).subscribe({
        next: (response: any) => {

          console.log('Success', response);
          this.medicineDetails = response
          this.medicineAdd.patchValue(this.medicineDetails)
         console. log("===>",this.medicineDetails.brand)
         this.medicineAdd.get("expiry_date").patchValue(this.formatDate(this.medicineDetails.expiry_date));
         this.medicineAdd.get("production_date").patchValue(this.formatDate(this.medicineDetails.production_date));
         this.medicineAdd.get("brand").setValue(this.medicineDetails.companyId)
         this.brand=this.medicineDetails.brand,
         this.companyId=this.medicineDetails.companyId




        },
        error: (error: any) => {
          console.log('error', error);
        }
      })



    }
    console.log(this.medicineDetails);
    this.service.getCompanyLIst().subscribe({
      next: (response: any) => {
        console.log(response);
        this.companyList = response

      }
    })


    this.medicineAdd = new FormGroup({
      medicinename: new FormControl('', Validators.required),
      category: new FormControl('', Validators.required),
      brand: new FormControl('', Validators.required),
      production_date: new FormControl('', Validators.required),
      expiry_date: new FormControl('', Validators.required),
      quantity: new FormControl('', [Validators.required,Validators.pattern("^[1-9]+[0-9]*$")]),
      cost_price: new FormControl('', [Validators.required,Validators.pattern("^[0-9.]+$")]),

    })

  }

  AddMedicine() {
  this.medicineAdd.markAllAsTouched()
    if(this.medicineAdd.valid)
    {
      var data = {
        "medicinename": this.medicineAdd.value.medicinename,
        "category": this.medicineAdd.value.category,
        "brand": this.brand,
        "productionDate": this.medicineAdd.value.production_date,
        "expiryDate": this.medicineAdd.value.expiry_date,
        "quantity": this.medicineAdd.value.quantity,
        "costPrice": this.medicineAdd.value.cost_price,
        "companyId": this.companyId
      }
      console.log(data);
      this.service.AddMedicine(data).subscribe({
        next: (res: any) => {
          console.log(res)
          alert("Successfully Inserted");

          this.router.navigateByUrl('/medicine');
        },
        error: (error: any) => console.log(error)

      });
    }
  }
  updateMedicine(id: any) {
  this.medicineAdd.markAllAsTouched()
    if (this.medicineAdd.valid) {
      var data = {
        "medicinename": this.medicineAdd.value.medicinename,
        "category": this.medicineAdd.value.category,
        "brand": this.brand,
        "productionDate": this.medicineAdd.value.production_date,
        "expiryDate": this.medicineAdd.value.expiry_date,
        "quantity": this.medicineAdd.value.quantity,
        "costPrice": this.medicineAdd.value.cost_price,
        "companyId": this.companyId
      }
      console.log(data);
      if(data.brand!=null){
        this.service.updatemedicine(data, id).subscribe({
          next:(response: any) => {
          console.log(response);
          alert("Successfully Updated");

          this.router.navigateByUrl('/medicine');
          },
          error: (err: any) => console.log(err)
        })
      }
    }
  }
  onChange(data: any) {
    console.log("=====>", this.medicineAdd.value.brand);
    this.companyId = data.target.value;
    console.log(this.companyId);
    let comp = this.companyList.find((c: any) => c.companyId == data.target.value)
    console.log(comp);

    this.brand = comp.name;
    console.log("====>", this.brand);




  }
  private formatDate(date:any) {
    const d = new Date(date);
    let month = '' + (d.getMonth() + 1);
    let day = '' + d.getDate();
    const year = d.getFullYear();
    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;
    return [year, month, day].join('-');
  }
}
