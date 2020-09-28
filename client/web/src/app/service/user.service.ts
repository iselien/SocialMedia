import { Injectable } from '@angular/core';
import { Config } from '../class/config';
import { User } from '../class/user';
import * as $ from 'jquery';
import { interval } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  domain = "http://localhost:8080";
  user:User;
  username = "";
  writeDisplay:boolean = false;
  addAlbumDisplay:boolean = false;

  constructor() { }

  autoLogin(){
    console.log("auto");
    var _this = this;
    $.ajax({
      type: "GET",
      url: this.domain+"/user/login",
      async: false,
      contentType: "application/json",
      dataType: "json",
      xhrFields: { withCredentials: true },
      crossDomain: true,
      success: function (data) {
        console.log(data);
        if(data.status == 0){
          _this.user = data.data;
          _this.username = data.data.username;
          console.log(this.user);
        }
      },
      error: function () {
        console.log("error")
      }
    })
  }

  login(username,password,status){
    var success = false;
    var _this = this;
    $.ajax({
      type: "POST",
      url: this.domain+"/user/login/ia",
      async: false,
      contentType: "application/json",
      dataType: "json",
      data: '{"username":"'+username+'", "password":"'+password+'","status":"'+status+'"}',
      xhrFields: { withCredentials: true },
      crossDomain: true,
      success: function (data) {
        console.log(data);
        if(data.status == 0){
          success = true;
          _this.user = data.data;
          _this.username = data.data.username;
          console.log(this.user);
        }
      },
      error: function () {
        console.log("error")
      }
    })
    return success;
  }
  isExist(username){
    var _this = this;
    var result = false;
    $.ajax({
      type: "GET",
      url: this.domain+"/user/isexist",
      async: false,
      contentType: "application/json",
      dataType: "json",
      data: 'username='+username,
      xhrFields: { withCredentials: true },
      crossDomain: true,
      success: function (data) {
        console.log(data);
        if(data.status == 0){
          result = true;
        }
      },
      error: function () {
        console.log("error")
      }
    })
    return result;
  }
  selfInfo(){
    var _this = this;
    var result = null;
    $.ajax({
      type: "GET",
      url: this.domain+"/user/",
      async: false,
      contentType: "application/json",
      dataType: "json",
      xhrFields: { withCredentials: true },
      crossDomain: true,
      success: function (data) {
        console.log(data);
        result = data.data;
      },
      error: function () {
        console.log("error")
      }
    })
    return result;
  }
  userInfo(id){
    var _this = this;
    var result = null;
    $.ajax({
      type: "POST",
      url: this.domain+"/user/userinfobyid",
      async: false,
      contentType: "application/json",
      dataType: "json",
      data: '{"id":'+id+'}',
      xhrFields: { withCredentials: true },
      crossDomain: true,
      success: function (data) {
        console.log(data);
        result = data.data;
      },
      error: function () {
        console.log("error")
      }
    })
    return result;
  }
  isLogined(){
    var _this = this;
    var result = null;
    $.ajax({
      type: "GET",
      url: this.domain+"/user/islogined",
      async: false,
      contentType: "application/json",
      dataType: "json",
      xhrFields: { withCredentials: true },
      crossDomain: true,
      success: function (data) {
        console.log(data);
        result = data;
      },
      error: function () {
        console.log("error")
      }
    })
    return result;
  }
  isConnected(){
    var _this = this;
    var result = null;
    $.ajax({
      type: "GET",
      url: this.domain+"/user/isconnected",
      async: false,
      contentType: "application/json",
      dataType: "json",
      xhrFields: { withCredentials: true },
      crossDomain: true,
      success: function (data) {
        console.log(data);
        result = data.data;
      },
      error: function () {
        console.log("error")
      }
    })
    return result;
  }
  register(newUsername, newPassword){
    var _this = this;
    var result = null;
    $.ajax({
      type: "POST",
      url: this.domain+"/user/register",
      async: false,
      contentType: "application/json",
      dataType: "json",
      data: '{"username":"'+newUsername+'","password":"'+newPassword+'"}',
      xhrFields: { withCredentials: true },
      crossDomain: true,
      success: function (data) {
        console.log(data);
        result = data.data;
      },
      error: function () {
        console.log("error")
      }
    })
    return result;
  }

  allArticle(id){
    var _this = this;
    var result = null;
    $.ajax({
      type: "GET",
      url: this.domain+"/article/"+id,
      async: false,
      contentType: "application/json",
      dataType: "json",
      xhrFields: { withCredentials: true },
      crossDomain: true,
      success: function (data) {
        console.log(data);
        result = data.data;
      },
      error: function () {
        console.log("error")
      }
    })
    return result;
  }
  
  allAlbums(id){
    var _this = this;
    var result = null;
    $.ajax({
      type: "POST",
      url: this.domain+"/album/getalbumsbyid",
      async: false,
      contentType: "application/json",
      dataType: "json",
      data: '{"id":'+id+'}',
      xhrFields: { withCredentials: true },
      crossDomain: true,
      success: function (data) {
        console.log(data);
        result = data.data;
      },
      error: function () {
        console.log("error")
      }
    })
    return result;
  }

}
