import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiService } from 'src/app/common-lib/service/api.service';

@Component({
  selector: 'app-medicine-view',
  templateUrl: './medicine-view.component.html',
  styleUrls: ['./medicine-view.component.css']
})
export class MedicineViewComponent implements OnInit {

  constructor(private service: ApiService, private router: Router, private activatedRoute: ActivatedRoute) { }
 
  id: any;
  medicineDetails:any={};
  ngOnInit(): void {
    if (this.activatedRoute.snapshot.paramMap.get('id') != null) {
      this.id = this.activatedRoute.snapshot.paramMap.get('id')
      console.log(this.id);

      this.service.getMedicineDetails(this.id).subscribe({
        next: (response: any) => {

          console.log('Success', response);
          this.medicineDetails = response
       

        },
        error: (error: any) => {
          console.log('error', error);
        }
      })



    }
  }

}
