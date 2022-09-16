import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/common-lib/service/api.service';

@Component({
  selector: 'app-sales',
  templateUrl: './sales.component.html',
  styleUrls: ['./sales.component.css']
})
export class SalesComponent implements OnInit {

  constructor(private service :ApiService) { }

  ngOnInit(): void {
    this.service.getMedineList().subscribe({
      next:(res:any)=>{
        console.log(res);
        this.medicine=res
        
      },
      error:(err:any)=>{
        console.log(err);
        
      }
    })
  }
  medicine:any

//   =[{
//     "id":1,
//     "name":"C-cold",

//   },
// {
//   "id":2,
//   "name":"Paracetamol",
  
// },{
//   "id":3,
//   "name":"Meftal"
// }]
onChangeMedicine(data:any){
console.log(data.target.value);

}
}
