import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/common-lib/service/api.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private service: ApiService, private router :Router) { }

  ngOnInit(): void {
  }
  loginForm: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/)])
  })


  login() {
    this.loginForm.markAllAsTouched()
    if (this.loginForm.valid) {
      this.service.loginUser(this.loginForm.value).subscribe({
        next:(res:any)=>{
          alert("login Success"),

          console.log("********************************************")
          console.log(res);
          // this.router.navigateByUrl("dashBoard")
          
          console.log(res.accessToken.value);
          console.log(res.email);
          localStorage.setItem('accessToken',res.accessToken.value);
          localStorage.setItem('user',res.email);

        }
        
      })
    }
    this.router.navigate(["saleshistory"]);
  }

}
