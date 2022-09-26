import { formatCurrency } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormControlName, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { ApiService } from 'src/app/common-lib/service/api.service';

@Component({
  selector: 'app-sales',
  templateUrl: './sales.component.html',
  styleUrls: ['./sales.component.css']
})
export class SalesComponent implements OnInit {

  constructor(private service: ApiService, private router: Router) { }
  // brandName:String="Select your option";
  salesForm: FormGroup = new FormGroup({
    quantity: new FormControl('', [Validators.required, Validators.min(1), Validators.pattern('^(0|[1-9][0-9]*)$')]),
    brand: new FormControl('', Validators.required)
  })
  totalAmount = 0
  ngOnInit(): void {
    this.service.getMedineList().subscribe({
      next: (res: any) => {
        console.log(res);
        this.medicine = res

      },
      error: (err: any) => {
        console.log(err);

      }
    })

  }
  medicine: any
  brand: any = []
  sortedBrand:any=[]
  availableQuantity?: any = ""
  med: any
  medi: any
  cart: any = {}
  soldMedicine: any = []


  onChangeMedicine(data: any) {
    console.log(data.target.value);

    let med = this.medicine.find((p: any) => p.medicineId == data.target.value)


    let medName = med.medicinename;
    this.brand = this.medicine.filter((p: any) => p.medicinename == medName)
    let date = new Date()

    this.sortedBrand = this.brand.sort((a: any) => {
      return   new Date(a.expiry_date).getTime()-date.getTime();
    });

    
    console.log("=====>",this.sortedBrand);
    this.salesForm.controls["brand"].reset()
    this.availableQuantity = ""
  }


  onChangeCompany(data: any) {
    this.med = this.medicine.find((p: any) => p.medicineId == data.target.value)
    console.log("stock===>" + this.med.quantity);
    this.availableQuantity = this.med.quantity + " Stock  Left"
    this.salesForm.controls["quantity"].reset()
  }

  addItem() {
    if (this.salesForm.valid) {
      if (this.salesForm.value.quantity <= this.med.quantity) {
        let quant = this.medicine[this.medicine.findIndex((p: any) => p.medicineId == this.med.medicineId,)].quantity
        this.medicine[this.medicine.findIndex((p: any) => p.medicineId == this.med.medicineId,)].quantity = quant - this.salesForm.value.quantity
        let totalPrice = this.salesForm.value.quantity * this.med.cost_price
        this.totalAmount = this.totalAmount + totalPrice
        // console.log("===================>"+totalPrice);
        this.cart = {
          "salesQuantity": this.salesForm.value.quantity,
          "totalAmount": totalPrice,
          "companyId": this.med.companyId,
          "medicineId": this.med.medicineId,
          "brand": this.med.brand,
          "medicinename": this.med.medicinename,
          "price": this.med.cost_price

        }

        // console.log("===============");

        // console.log(this.cart);
        this.soldMedicine.push(this.cart)
        // console.log("Array=>",this.soldMedicine);

        this.salesForm.reset()
        this.availableQuantity = ""

      } else {
        alert("Only " + this.med.quantity + " stock left")
      }
    } else {
      alert("*All Fields are required and quantity should only contain a positive number")
    }
  }

  confirmSale() {
    this.soldMedicine[0]["completeTotal"] = this.totalAmount
    this.service.addSale(this.soldMedicine).subscribe({
      next: (res: any) => {
        console.log(res)
        this.service.getsale(this.soldMedicine)
        this.router.navigateByUrl('/invoice');
      },
      error: (err: any) => {
        console.log(err);

      }
    })
  }
  onDelete(index: number, medicineId: any, quantity: any) {
    console.log("Id==>", medicineId);

    let quant = this.medicine[this.medicine.findIndex((p: any) => p.medicineId == medicineId,)].quantity
    this.medicine[this.medicine.findIndex((p: any) => p.medicineId == medicineId)].quantity = quant + quantity
    this.totalAmount = this.totalAmount - this.soldMedicine[0].totalAmount
    this.cart = ""
    this.soldMedicine.splice(index, 1)
  }
}
