import { formatCurrency } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormControlName, FormGroup, Validators } from '@angular/forms';
import { ApiService } from 'src/app/common-lib/service/api.service';

@Component({
  selector: 'app-sales',
  templateUrl: './sales.component.html',
  styleUrls: ['./sales.component.css']
})
export class SalesComponent implements OnInit {

  constructor(private service: ApiService) { }
  // brandName:String="Select your option";
  salesForm: FormGroup = new FormGroup({
    quantity: new FormControl('', Validators.required),
    brand: new FormControl('', Validators.required)
  })
  totalAmount=0
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
  availableQuantity?: any = ""
  med: any
  cart: any = {}

  //   =[{
  //     "id":1,
  //     "name":"C-cold",

  //   },
  // {
  //   "id":2,
  //   "name":"Paracetamol",

  // },{
  //   "id":3,
  //   "name":"Meftal"
  // }]
  onChangeMedicine(data: any) {
    console.log(data.target.value);

    let med = this.medicine.find((p: any) => p.medicineId == data.target.value)
    // console.log("stock===>"+med.stock);

    let medName = med.medicinename;
    this.brand = this.medicine.filter((p: any) => p.medicinename == medName)
    console.log(this.brand);
    this.salesForm.controls["brand"].reset()




  }
  onChangeCompany(data: any) {
    // console.log(this.brandName);

    this.med = this.medicine.find((p: any) => p.medicineId == data.target.value)
    console.log("stock===>" + this.med.quantity);
    this.availableQuantity = this.med.quantity + " Stock  Left"
    this.salesForm.controls["quantity"].reset()



  }

  addItem() {
    if (this.salesForm.valid) {
      if (this.salesForm.value.quantity <= this.med.quantity) {
        console.log("============================================================");
        console.log(this.med);
        let totalPrice = this.salesForm.value.quantity * this.med.cost_price
        this.totalAmount=this.totalAmount+totalPrice
        // console.log("===================>"+totalPrice);
        this.cart = {
          "salesQuantity": this.salesForm.value.quantity,
          "totalAmount": totalPrice,
          "companyId": this.med.companyId,
          "medicineId": this.med.medicineId,
          "brand":this.med.brand,
          "medicinename":this.med.medicinename,
          "price":this.med.cost_price

        }
        console.log("===============");

        console.log(this.cart);
        this.salesForm.reset()
        this.availableQuantity=""
        
      } else {
        alert("Only " + this.med.quantity + " stock left")
      }
    } else {
      alert("*All Fields are required")
    }



  }

  confirmSale(){
    this.service.addSale(this.cart).subscribe({
      next:(res:any)=>{
        console.log(res)
      },
      error:(err:any)=>{
        console.log(err);
        
      }
    })
  }
}
