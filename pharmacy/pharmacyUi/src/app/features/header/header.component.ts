import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/common-lib/service/api.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private service :ApiService) { }
  userType: any = localStorage.getItem("type")

  ngOnInit(): void {
this.getprofilePicture()
  }
  onLogout() {
    localStorage.removeItem("accessToken")
    localStorage.removeItem("refreshToken")
    window.location.reload()

  }
  getprofilePicture() {
    this.service.getProfilePic().subscribe({
      next: (response: any) => {
        console.log("Imgage", response);

        (document.getElementById('asd'))?.setAttribute('src', URL.createObjectURL(
          new Blob([response], { type: response.type })
        ))
      },
      error: (err) => {
        console.log(err)
      }
    })
    // console.log(this.imgurl);
  }
}
