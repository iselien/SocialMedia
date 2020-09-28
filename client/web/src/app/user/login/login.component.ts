import { Component, OnInit } from '@angular/core';
import { AppComponent } from '../../app.component';
import { UserService } from '../../service/user.service';
import { User } from '../../class/user';
import * as $ from 'jquery';
import { FormsModule } from '@angular/forms';
import { onErrorResumeNext } from 'rxjs';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { of,interval } from "rxjs";
import { Location } from '@angular/common'


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
 
  username:String = null;
  newUsername:String;
  newPassword:String;
  password:String;
  vcode:String;

  inputUsernameContent:Boolean = true;
  inputPasswordContent:Boolean = false;
  createAccountContent:Boolean = false;
  createPasswordContent:Boolean = false;
  errorMessage:String;
  inboxheight:Number = 380;
  status:Boolean = false;
  statusIcon = "fa fa-square-o fa-lg";

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private location: Location,
    private user: UserService
  ) { }

  ngOnInit(): void {

    // console.log(this.route.parent);


    // console.log("init");
    // var _this = this;
    // $("#main_inbox").on("keydown","#username",function (){
    // $("#msg").hide();
    // $("#main_box").height(380);
    // })
    // $("#main_inbox").on("click","#createone",function (){
    //   _this.create();
    // })
    // $("#main_inbox").on("click","#signin",function (){
    //   _this.signin();
    // })
    // $("#main_inbox").on("click","#next",function (){
    //   _this.next();
    // })

  }

  displayCreateAccount(){
    this.createAccountContent = true;
    this.inputUsernameContent = false;
    this.inputPasswordContent = false;
    this.createPasswordContent = false;
    this.changeHeight();
  }
  
  backVerifyUsername(){
    this.inputUsernameContent = true;
    this.inputPasswordContent = false;
    this.createAccountContent = false;
    this.createPasswordContent = false;
  }

  verifyUsername(){
    if(!this.username){
      this.inboxheight = 400;
      this.errorMessage = "Enter a valid email address or phone number."
    }else{
      if(this.user.isExist(this.username)){
        this.inputUsernameContent = false;
        this.inputPasswordContent = true;
      }else{
        this.inboxheight = 400;
        this.errorMessage = "The user does not exist."
      }
    }
  }

  changeHeight(){
    this.inboxheight = 380;
    this.errorMessage = null;
  }
 
  changeStatus(){
    if(this.status){
      this.status = false;
      this.statusIcon = "fa fa-square-o fa-lg";
    }else{
      this.status = true;
      this.statusIcon = "fa fa-check-square fa-lg";
    }
  }

  login(){
    console.log(this.password);
    if(!this.password){
      this.inboxheight = 400;
      this.errorMessage = "The password cannot be empty"
    }else{
      if(!this.user.login(this.username,this.password,this.status)){
        this.inboxheight = 420;
        this.errorMessage = "Your account or password is incorrect. If you don't remember your password, <a href='#'>reset it now.</a>"
      }else this.location.back();
    }
  }


  verifyIsExist(){
    console.log(this.newUsername)
    console.log(this.user.isExist(this.newUsername));
    if(!this.newUsername){
      this.inboxheight = 400;
      this.errorMessage = "The username cannot be empty";
    }else{
      if(this.user.isExist(this.newUsername)){
        this.inboxheight = 400;
        this.errorMessage = "The username already exist";
      }else{
        this.createPasswordContent = true;
        this.createAccountContent = false;
      }
    }
  }

  confirmCreate(){
    var _this = this;
    console.log("owowwwwwwwwwllllllllllllllll");
    this.user.register(this.newUsername, this.newPassword);
  }


}
