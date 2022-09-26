import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
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


  addForm =new FormGroup({
    days:new FormControl('',[Validators.required]),

  })


  ngOnInit(): void
  {
      this.getValueFromSalesApi(0,5,this.sortValue);
  }

      conditionVariable:any;
      valuesOfUser:any;
      pageNo:any = 0;
      pageSize:any = 5;
      days:any;
      sales:any

      sortValue="salesId"
      getValueFromSalesApi(pageNo: number, pageSize: number, sort:any) 
      {
      this.service.getSalesData(pageNo, pageSize, sort).subscribe((res: any) => {

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
        this.getValueFromSalesApi(this.pageNo, this.pageSize,this.sortValue)
    
      }
      previousClick() 
      {
        if (this.pageNo == 0)
          return
        this.pageNo = this.pageNo - 1;
        this.getValueFromSalesApi(this.pageNo, this.pageSize,this.sortValue)
    
      }
      onSort(sort:any)
      {
        console.log("=====>",sort.target.value);
        if(sort.target.value==1){
          this.sortValue="salesId"}
          else if(sort.target.value==2){
            this.sortValue="salesDate"
          }
          else{
            this.sortValue="totalAmount"
          }
        //  this.service.getSalesData().subscribe({
        //   next:(res:any)=>{
        //     console.log("Success===>",res);
            
        //   },
        //   error:(err:any)=>{
        //     console.log((err));
            
        //   }
        //  })
        this.getValueFromSalesApi(0,5,this.sortValue)
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
    //   onChangeDays(days:any) {
    //     this.service.filter(days).subscribe(
    //       (res:any)=>{

    //         console.log(res);
            
    //       this.salesdata = res
        
    //     });
      

    // }

    onSubmit()
    {
        console.log(this.addForm.value)
        this.service.filter(this.addForm.value.days).subscribe(res => {

          this.salesdata = res
          alert("success")
          } );
        // this.router.navigate(['itemlist'])
        
    }
  
    }



 