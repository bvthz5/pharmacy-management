import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/common-lib/service/api.service';
import {MatSort,Sort} from '@angular/material/sort';

@Component({
  selector: 'app-saleshistory',
  templateUrl: './saleshistory.component.html',
  styleUrls: ['./saleshistory.component.css']
})
export class SaleshistoryComponent implements OnInit {



  constructor(private router : Router, private service : ApiService) {       }


  salesdata:any;
  sortedData:string[]=[];
  pageNo:any = "";
  pageSize:any = "";
  sortValue = "";
  sortDir = "";
  conditionVariable:any;
  valuesOfUser:any;
  days:any;
  sales:any;
  order:boolean = true;

  ngOnInit(): void
  {
      this.getValueFromSalesApi(this.pageNo,this.pageSize,this.sortValue,this.sortDir);
      // this.service.getSalesList().subscribe((res:any) =>
      // {
      //   this.salesdata = res
      // })

  }

// ================================================================================================>

      getValueFromSalesApi(pageNo: number, pageSize: number, sortValue:any, sortDir:any)
      {
      this.service.getSalesData(pageNo, pageSize, sortValue, sortDir ).subscribe((res: any) => {
            console.log(this.pageNo);

            // console.log(res);
            if (res == 0)
            {
              this.conditionVariable = false
              return
            }
            this.conditionVariable = true
            this.salesdata = res

          });

        }
// ===========================================================================================>

sort(data:any)
{

  if(this.order == true)
  {
    this.sortValue = data;
    this.sortDir = "ASC"
    this.getValueFromSalesApi(this.pageNo,this.pageSize,this.sortValue,this.sortDir);
    this.order = false;
  }

  else
  {

    this.sortValue = data;
    this.sortDir = "DESC";
    this.getValueFromSalesApi(this.pageNo,this.pageSize,this.sortValue,this.sortDir)
    this.order = true;

  }
}

// ============================================================================================>

      // onSort(sort:any)
      // {

      //   console.log(sort.target.value);

      //   console.log("=====>",sort.target.value);
      //   if(sort.target.value==1){
      //     this.sortValue="salesId"
      //   }
      //     else if(sort.target.value==2){
      //       this.sortValue="salesDate"
      //     }
      //     else{
      //       this.sortValue="totalAmount"
      //     }

      //   this.getValueFromSalesApi(this.pageNo,this.pageSize,this.sortValue,this.sortDir)
      // }

      // onSortDir(sort:any)
      // {
      //   console.log("=====>",sort.target.value);
      //   if(sort.target.value==1)
      //   {
      //     this.sortDir="ASC"
      //   }
      //     else if(sort.target.value==2)
      //     {
      //       this.sortDir="DESC"
      //     }


      //   this.getValueFromSalesApi(this.pageNo,this.pageSize,this.sortValue, this.sortDir)
      // }
// ============================================================================================>
      nextClick()
      {
        this.pageNo = this.pageNo + 1;
        this.getValueFromSalesApi(this.pageNo, this.pageSize,this.sortValue,this.sortDir)

      }

      previousClick()
      {
        if (this.pageNo == 0)
          return
        this.pageNo = this.pageNo - 1;
        this.getValueFromSalesApi(this.pageNo, this.pageSize,this.sortValue,this.sortDir)

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

    addForm =new FormGroup({

        date:new FormControl('',[Validators.required]),

      })
    onSubmit()
    {

        console.log(this.addForm.value)
        this.service.search(this.addForm.value.date).subscribe(res => {

          this.salesdata = res

          } );


    }
    searchForm =new FormGroup({

      name:new FormControl('',[Validators.required]),

    })
    onSearch()
    {
      this.service.search(this.searchForm.value.name).subscribe(res => {
        console.log(this.searchForm.value.name);
        console.log(res);

        this.salesdata = res

      })
    }

// ====================================================================================>
//   sortData(pageNo:number , pageSize:number ,sort: Sort) {
//     this.service.getSalesData(pageNo, pageSize, sort).subscribe((res: any) => {

//       console.log(res);
//     })


//     const data = this.salesdata.slice();
//     if (!sort.active || sort.direction === '') {
//       this.sortedData = data;
//       return;
//     }

//     this.sortedData = data.sort((a: { salesId: string | number; salesQuantity: string | number; totalAmount: string | number; }, b: { salesId: string | number; salesQuantity: string | number; totalAmount: string | number; }) => {
//       const isAsc = sort.direction === 'asc';
//       switch (sort.active) {
//         case 'salesId':
//           return compare(a.salesId, b.salesId, isAsc);
//         case 'salesQuantity':
//           return compare(a.salesQuantity, b.salesQuantity, isAsc);
//         case 'totalAmount':
//           return compare(a.totalAmount, b.totalAmount, isAsc);
//         default:
//           return 0;
//       }
//     });
//   }

// }

// function compare(a: number | string, b: number | string, isAsc: boolean) {
//   return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
// }
// ===================================================================================>

// ====================================================================================>
// categoris!: ['Paracetamol'];
  // salesdata: Array<any> = [];
  // isDesc: boolean = false;
  // column: string = 'CategoryName';

  // searchText: any;
  // public searchText: Sales['medicinename'];
  // sort(property: string) {

  //   this.service.getSalesList().subscribe((res:any) =>{


  //   this.salesdata = res;
  //   this.isDesc = !this.isDesc;
  //   this.column = property;
  //   let direction = this.isDesc ? 1 : -1;

  //   this.salesdata.sort(function (a, b) {
  //     if (a[property] < b[property]) {
  //       return -1 * direction;
  //     }
  //     else if (a[property] > b[property]) {
  //       return 1 * direction;
  //     }
  //     else {
  //       return 0;
  //     }
  //   });
  // })
  // }
  // =================================================================================>


}
