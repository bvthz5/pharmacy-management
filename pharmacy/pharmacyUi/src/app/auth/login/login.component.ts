import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/common-lib/service/api.service';
import { ToastrService } from 'ngx-toastr';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

 showSpinner: boolean = false;

  constructor(private service: ApiService, private router :Router,public toastr:ToastrService,private modalService:NgbModal) { }

  ngOnInit(): void {
  }
  loginForm: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/)])
  })

  forgotPswdForm: FormGroup = new FormGroup({
    email: new FormControl('',[ Validators.required,Validators.email]),
  })


bv:any;
  login() {
    this.loginForm.markAllAsTouched()
    if (this.loginForm.valid ) {
      this.service.loginUser(this.loginForm.value).subscribe({
        next:(res:any)=>{
          this.bv=res;
          if(this.bv !=0){
            this.showToastSuccess();

          }

          else{
            this.showToastWarn()


          }

          console.log(res);

          localStorage.setItem("accessToken",res.accessToken.value),
          localStorage.setItem("refreshToken",res.refreshToken.value),
          localStorage.setItem("type",res.type)

          this.router.navigateByUrl("home")


        }
      })
    }
  }

  showToastSuccess() {
    this.toastr.success("Login Successfully","Success",{timeOut: 800,positionClass: "toast-top-center"});
  }

  showToastWarn(){
    this.toastr.warning("Invalid","Warning",{timeOut: 800,positionClass: "toast-top-center"});
  }

  forgotPswd(){
    if(this.forgotPswdForm.valid){
      this.showSpinner=true
      let data =this.forgotPswdForm.controls['email'].value;
      this.service.forgotPswrd(data).subscribe({
        next: (response: any) => {
          this.showSpinner=false;
          console.log(response);
          document.getElementById('forgotPswdModal')?.click();
          alert("Password reset link has been sent to "+this.forgotPswdForm.controls['email'].value);
        },
        error: (error: any) => {
          this.showSpinner=false;
          console.log(error);
          document.getElementById('forgotPswdModal')?.click(); }
      })
    }
  }

  open(content: any) {
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' })
  }

}



