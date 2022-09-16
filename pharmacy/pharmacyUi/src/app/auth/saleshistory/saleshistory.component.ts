import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/common-lib/service/api.service';

@Component({
  selector: 'app-saleshistory',
  templateUrl: './saleshistory.component.html',
  styleUrls: ['./saleshistory.component.css']
})
export class SaleshistoryComponent implements OnInit {

  constructor(private router : Router, private service : ApiService) { }
  salesdata:any;
  ngOnInit(): void {
        this.service.getSalesData().subscribe((res:any)=> {  
          console.log(res);
          
          this.salesdata = res; 
          
          // this.id = res.itemId; 
          // console.log(this.Item);
    
        },  
        err => {  
          console.log("error while getting user Details");  
        }  
      );  
      }
}
    
