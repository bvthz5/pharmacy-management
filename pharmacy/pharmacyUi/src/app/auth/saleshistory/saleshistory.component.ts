import { Component, ElementRef, EventEmitter, HostListener, Input, OnInit, Output, ViewChild } from '@angular/core';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/common-lib/service/api.service';
import { IDropdownSettings } from 'ng-multiselect-dropdown';


@Component({
  selector: 'app-saleshistory',
  templateUrl: './saleshistory.component.html',
  styleUrls: ['./saleshistory.component.css']
})
export class SaleshistoryComponent implements OnInit {
 
  constructor(private router : Router, private service : ApiService) { }
  salesdata:any;
  
  ngOnInit(): void
  {
      this.getValueFromSalesApi(0,1);
  }

      conditionVariable:any;
      valuesOfUser:any;
      pageNo:any = 0;
      pageSize:any = 1;
      getValueFromSalesApi(pageNo: number, pageSize: number) 
      {
      this.service.getSalesData(pageNo, pageSize).subscribe((res: any) => {

            console.log(res);
            if (res == 0) 
            {
              this.conditionVariable = false
              return
            }
            this.conditionVariable = true
            this.salesdata = res

          });

        }
    
      nextClick() 
      {
        this.pageNo = this.pageNo + 1;
        this.getValueFromSalesApi(this.pageNo, this.pageSize)
    
      }
      previousClick() 
      {
        if (this.pageNo == 0)
          return
        this.pageNo = this.pageNo - 1;
        this.getValueFromSalesApi(this.pageNo, this.pageSize)
    
      }

      SortBy()
      {
        // this.router.navigate(['login']);
      }
  
      ShowHistory()
      {
       
      }
      

      onSubmit(form: NgForm): void 
      {
 
        console.log(form.value.name);

      } 


      downloadFile()
      {
         this.service.download().subscribe({
          next:(res:any)=>{

                              let a = document.createElement('a');
                              a.download = "sales_history.csv";
                              a.href = window.URL.createObjectURL(res);
                              a.click();

          
                          }

        });
     
      }
      salesForm: FormGroup = new FormGroup({
        quantity: new FormControl('', [Validators.required, Validators.min(1), Validators.pattern('^(0|[1-9][0-9]*)$')]),
        brand: new FormControl('', Validators.required)
      })
      totalAmount = 0

      medicine: any
      brand: any = []
      sortedBrand:any=[]
      availableQuantity?: any = ""
      med: any
      medi: any
      cart: any = {}
      soldMedicine: any = []
    
    
      onChangeField(data: any) {
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
    

    
      onChangeDays() {
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


}
   