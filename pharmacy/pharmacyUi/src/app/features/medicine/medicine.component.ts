import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/common-lib/service/api.service';

@Component({
  selector: 'app-medicine',
  templateUrl: './medicine.component.html',
  styleUrls: ['./medicine.component.css']
})
export class MedicineComponent implements OnInit {

  medicine: any
  constructor(private service: ApiService) { }
  userType: any = localStorage.getItem("type")

  ngOnInit(): void {
    this.service.medicineList().subscribe({
      next: (res: any) => {
        let date = new Date()
        this.medicine = res

        console.log(res);
      },
      error: (error: any) => console.log(error)


    });

  }
  medicineDetails:any
  medView(medId: any) {
    this.service.getMedicineDetails(medId).subscribe({
      next: (response: any) => {

        console.log('Success', response);
        this.medicineDetails = response
     

      },
      error: (error: any) => {
        console.log('error', error);
      }
    })
    
  }
  deleteMedicine(data: any) {
    this.service.deleteMedicine(data).subscribe((res: any) => {
      window.location.reload();
      console.log(res);
    });

  }
  checkExpiry(expiryDate: any): Boolean {
    let epoch = new Date(expiryDate).getTime() - new Date().getTime()
    let day = epoch / 84600000
    if (day < 30) {
      return true
    }
    else {
      return false
    }
  }
  onSortChange(data: any) {
    if (data.target.value == 2) {
      this.medicine.sort((a: any, b: any) => {
        return new Date(a.expiry_date).getTime() - new Date(b.expiry_date).getTime();
      });

    } else if (data.target.value == 3) {
      this.medicine.sort((a: any, b: any) => {
        return a.quantity - b.quantity;
      });
    }
    else {
      this.medicine.sort((a: any, b: any) => {
        return a.medicineId - b.medicineId;
      });
    }
  }

}
