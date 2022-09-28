import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/common-lib/service/api.service';

@Component({
  selector: 'app-expired-medicine',
  templateUrl: './expired-medicine.component.html',
  styleUrls: ['./expired-medicine.component.css']
})
export class ExpiredMedicineComponent implements OnInit {

  medicine: any
  medicineDetails: any
  constructor(private service: ApiService) { }
  userType: any = localStorage.getItem("type")

  ngOnInit(): void {
    this.service.getExpiredMedicine().subscribe({
      next: (res: any) => {
        let date = new Date()
        this.medicine = res

        console.log(res);
      },
      error: (error: any) => console.log(error)


    });

  }
  deleteMedicine(data: any) {
    this.service.deleteMedicine(data).subscribe((res: any) => {
      window.location.reload();
      console.log(res);
    });

  }

  Method(data: any, data1: any) {
    if (confirm('Are you sure you want to delete:' + data)) {

      this.deleteMedicine(data1);
    }

  }
}
