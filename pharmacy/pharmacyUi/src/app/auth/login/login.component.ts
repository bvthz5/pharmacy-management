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
    if (this.loginForm.valid ) {
      this.service.loginUser(this.loginForm.value).subscribe({
        next:(res:any)=>{
          alert("login Success"),
          console.log(res);
          this.router.navigateByUrl("dashBoard")
          
        }
      })
    }
    else{
      alert("Enter a valid form")
    }
  }

}
