import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../common-lib/service/api.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private service:ApiService) { }
  admin:any
  userType: any = localStorage.getItem("type")
  ngOnInit(): void {
this.getDetails();
this.getprofilePicture();
  }
  onLogout() {
    localStorage.removeItem("accessToken")
    localStorage.removeItem("refreshToken")
    window.location.reload()

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

  getprofilePicture() {
    this.service.getProfilePic().subscribe({
      next: (response: any) => {
        console.log("Image", response);

        (document.getElementById('asd'))?.setAttribute('src', URL.createObjectURL(
          new Blob([response], { type: response.type })
        ))
      },
      error: (err:any) => {
        console.log(err)
      }
    })



}
}
