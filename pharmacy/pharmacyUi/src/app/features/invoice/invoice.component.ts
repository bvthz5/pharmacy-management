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

  constructor(private router:Router,private service:ApiService) { }
saleDetails?:any
  ngOnInit(): void {
   this.saleDetails= this.service.returnSale()   
   console.log(this.saleDetails);
   
  }

}
