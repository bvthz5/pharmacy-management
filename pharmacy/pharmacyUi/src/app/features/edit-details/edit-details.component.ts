import { Component, OnInit, Input } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ApiService } from 'src/app/common-lib/service/api.service';

@Component({
  selector: 'app-edit-details',
  templateUrl: './edit-details.component.html',
  styleUrls: ['./edit-details.component.css']
})
export class EditDetailsComponent implements OnInit {

  constructor(private service: ApiService, public activeModal: NgbActiveModal) { }

  editForm: FormGroup = new FormGroup({
    name: new FormControl("", Validators.required),
    email: new FormControl("", [Validators.required, Validators.email]),
    phone: new FormControl("", [Validators.required, Validators.pattern(/^\+?(0|[1-9]\d*)$/)])
  })
  ngOnInit(): void {
    this.service.getCurrentUserDetails().subscribe({
      next: (res: any) => {
        console.log(res);
        this.editForm.patchValue(res)
        console.log(this.editForm.value);


      },
      error: (err: any) => {
        console.log(err);

      }
    })
  }

  onSubmit() {
    this.editForm.markAllAsTouched();
    if (this.editForm.valid) {
      this.service.updateUserDetails(this.editForm.value).subscribe({
        next: (res: any) => {
          console.log(res);
          window.location.reload()
          this.activeModal.close()

        },
        error:(err:any)=>{
          console.log(err);
          
        }
      })
    }
  }
}
