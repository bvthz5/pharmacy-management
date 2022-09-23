import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiService } from 'src/app/common-lib/service/api.service';

@Component({
  selector: 'app-company-detain-view',
  templateUrl: './company-detain-view.component.html',
  styleUrls: ['./company-detain-view.component.css']
})
export class CompanyDetainViewComponent implements OnInit {

  constructor(private service: ApiService, private router: Router, private activatedRoute: ActivatedRoute) { }

  id: any;
  companyDetails: any = {}

  ngOnInit(): void {
    if (this.activatedRoute.snapshot.paramMap.get('id') != null) {
      this.id = this.activatedRoute.snapshot.paramMap.get('id')
      console.log(this.id);

      this.service.getCompanyDeatails(this.id).subscribe({
        next: (response: any) => {
          console.log('Success', response);
          this.companyDetails = response

        },
        error: (error: any) => {
          console.log('error', error);
        },
        complete: () => {
          console.log('Completed');
        },
      });


    }
  }
}
