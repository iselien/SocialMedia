import { Component } from '@angular/core';
import { UserService } from './service/user.service';
import * as $ from 'jquery';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'CPA-Cloud Photography Association';
  domain = "http://localhost:8080"

  constructor(
    private user: UserService
  ){}

  ngOnInit() {
    var _this = this;
    /* DYNAMIC DATA FILL */
    console.log(_this.user.isLogined());
    console.log(_this.user.isConnected());
    if(this.user.isLogined() || this.user.isLogined()){
      this.user.autoLogin();  
    }

    // $.ajax({
    //   type: "GET",
    //   url: _this.user.domain+"/",
    //   async: false,
    //   contentType: "application/json",
    //   dataType: "json",
    //   xhrFields: { withCredentials: true },
    //   crossDomain: true,
    //   success: function (data) {
    //     console.log(data);
    //   },
    //   error: function () {
    //     console.log("error")
    //   }
    // })

  }

  test(){
    console.log("test");

    var formData = new FormData();
    console.log(formData);
    var file = <HTMLInputElement>document.getElementById("file");
    formData.append("file",file.files[0]);
    formData.append("abc","user");
    console.log(formData.getAll("abc"));
    console.log(formData.getAll("file"));

    var photo = new XMLHttpRequest();
    photo.open("POST","http://localhost:8080/photo/upload1");
    // photo.responseType = "blob";
    photo.withCredentials = true;
    // photo.setRequestHeader("Content-Type","mutipart/form-data");
    photo.overrideMimeType("mutipart/form-data");
    photo.send(formData);
  }
}


