import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
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
   
  ngOnInit(): void 
  {
      this.getValueFromSalesApi(0,1);
  }

      conditionVariable:any;
      valuesOfUser:any;
      pageNo:any = 0;
      pageSize:any = 1;
      getValueFromSalesApi(pageNo: number, pageSize: number) {
      this.service.getSalesData(pageNo, pageSize).subscribe((res: any) => {

            console.log(res);
            if (res == 0) {
              this.conditionVariable = false
              return
            }
            this.conditionVariable = true
            this.salesdata = res

          });

        }
    
      nextClick() {
        this.pageNo = this.pageNo + 1;
        this.getValueFromSalesApi(this.pageNo, this.pageSize)
    
      }
      previousClick() {
        if (this.pageNo == 0)
          return
        this.pageNo = this.pageNo - 1;
        this.getValueFromSalesApi(this.pageNo, this.pageSize)
    
      }

      SortBy()
      {
        this.router.navigate(['login']);
      }
  
      ShowHistory()
      {
        this.router.navigate(['login']);
      }
      onSubmit(form: NgForm): void {
 
        console.log(form.value.name);

      } 
    }
   