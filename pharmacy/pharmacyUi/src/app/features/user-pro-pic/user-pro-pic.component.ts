import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ApiService } from '../../common-lib/service/api.service';
import { EditDetailsComponent } from '../edit-details/edit-details.component';

@Component({
  selector: 'app-user-pro-pic',
  templateUrl: './user-pro-pic.component.html',
  styleUrls: ['./user-pro-pic.component.css']
})
export class UserProPicComponent implements OnInit {

  constructor(private service: ApiService, private modalService: NgbModal) { }
  admin: any
  src:any
  blob: any

  ngOnInit(): void {
    this.getProfilePic();
    this.getDetails()
  }
  changePasswordForm: FormGroup = new FormGroup({
    currentPswd: new FormControl('', [Validators.required, Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,12}$'), Validators.minLength(8)]),
    newPswd: new FormControl('', [Validators.required, Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,12}$'), Validators.minLength(8)]),
    confirmPswd: new FormControl('', Validators.required)
  })


  uploadPic() {
    const fd = new FormData();
    fd.append('profilePic', this.selectedImage)
    this.service.uploadImage(fd).subscribe({
      next: (response: any) => {
        if (response) {
          alert("uploaded");
          this.getProfilePic();
          window.location.reload();
        }
      },
      error: (error: any) => {
        console.log(error);
        alert('Failed To Upload');
      }
    })
  }

  getProfilePic() {

    this.service.getProfilePic().subscribe({
      next: (response: any) => {
        if (response) {
        console.log("pic", response);

          (document.getElementById('img') as HTMLImageElement).src = URL.createObjectURL(
            new Blob([response], { type: response.type })
          )
        }
      },
      error: (error: any) => {
        console.log(error);
      }
    })
  }

  selectedImage:any

  onFileChanged(event: any) {
    console.log(event);
    this.selectedImage = <File>event.target.files[0]
    this.uploadPic();
  }

  onLogout() {
    localStorage.clear();
    window.location.reload()

  }
  changePassword() {
    if (this.changePasswordForm.valid) {
      let data = {
        currentPassword: this.changePasswordForm.controls['currentPswd'].value,
        newPassword: this.changePasswordForm.controls['newPswd'].value
      }
      this.service.changePswd(data).subscribe({
        next: (response: any) => {
          console.log(response);
          if (response) {
            alert("Password Changed Successfully");
            document.getElementById('closeChangePswdModal')?.click();
            this.changePasswordForm.reset();
          }
        },
        error: (error: any) => {
          console.log(error);
          if (error) {
            alert("Password Mismatch");
            document.getElementById('closeChangePswdModal')?.click();
          }
        }
      })
    }
  }

  getDetails() {
    this.service.getUserDetails().subscribe({
      next: (response: any) => {
        console.log(response);
        if (response) {
          this.admin = response;
        }
      },
      error: (error: any) => { console.log(error) }
    })
  }

  open(content: any) {
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' })
  }
  interval:any
  ngOnDestroy(): void {
    clearInterval(this.interval);
  }
  openModal() {
    const modalRef = this.modalService.open(EditDetailsComponent,{backdrop:"static"})

  }
}
