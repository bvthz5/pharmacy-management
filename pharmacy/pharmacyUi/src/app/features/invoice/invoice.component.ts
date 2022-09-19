import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/common-lib/service/api.service';
import { SalesComponent } from '../sales/sales.component';

@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  styleUrls: ['./invoice.component.css']
})
export class InvoiceComponent implements OnInit {

  constructor(private router: Router, private service: ApiService) { }
  saleDetails?: any
  async ngOnInit(): Promise<void> {
   await this.service.returnSale().then((result: any) => {
      this.saleDetails=  result
      console.log(this.saleDetails);
      setTimeout(() => {
          window.print()
        this.router.navigateByUrl('/home')

      }, 100);
     
      //  this.router.navigateByUrl('/home')
    }).catch((err: any) => {

    });



  }

}
