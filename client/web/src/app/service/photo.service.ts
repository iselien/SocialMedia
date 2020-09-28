import { Injectable } from '@angular/core';
import { UserService } from '../service/user.service';
import { Router } from '@angular/router';
import * as $ from 'jquery';
import md5 from '../../assets/js/md5.js';
import { Content } from '@angular/compiler/src/render3/r3_ast';


@Injectable({
  providedIn: 'root'
})
export class PhotoService {

  constructor(
    private user: UserService,
    private router:Router
  ) { }

  process:boolean = true; // 是否打印调试信息

  tag(likelyName){
    var result = null;
    console.log("wwwwww:",likelyName);
    $.ajax({
      type: "GET",
      url: this.user.domain+"/album/tag",
      async: false,
      contentType: "application/json",
      dataType: "json",
      data: 'likelyName='+likelyName,
      xhrFields: { withCredentials: true },
      crossDomain: true,
      success: function (data) {
        console.log(data);
        if(data.status == 0){
          result = data.data;
        }
      },
      error: function () {
        console.log("error")
      }
    })
    return result;
  }

  Itag(tag){
    var result = null;
    $.ajax({
      type: "POST",
      url: this.user.domain+"/album/tag",
      async: false,
      contentType: "application/json",
      dataType: "json",
      data: JSON.stringify({"tag":tag}),
      xhrFields: { withCredentials: true },
      crossDomain: true,
      success: function (data) {
        console.log(data);
        if(data.status == 0){
          result = data.data;
        }
      },
      error: function () {
        console.log("error")
      }
    })
    return result;
  }


  // 插入专辑 
  insertAlbums(userId,name,describe){
    var result = null;
    $.ajax({
      type: "POST",
      url: this.user.domain+"/album/",
      async: false,
      contentType: "application/json",
      dataType: "json",
      xhrFields: { withCredentials: true },
      crossDomain: true,
      data:JSON.stringify({"userId":userId,"albumName":name,"albumDescribe":describe,"albumTag":"tag1,tag2,tag3"}), 
      success: function (data) {
        console.log(data);
        if(data.status == 0){
          result = data.data;
        }
      },
      error: function () {
        console.log("error")
      }
    })
    return result;
  }

  // 获取用户的所有个人专辑
  fetchAlbums(){
    var result = null;
    $.ajax({
      type: "GET",
      url: this.user.domain+"/album/"+this.user.user.id,
      async: false,
      contentType: "application/json",
      dataType: "json",
      xhrFields: { withCredentials: true },
      crossDomain: true,
      success: function (data) {
        console.log(data);
        if(data.status == 0){
          result = data.data;
        }
      },
      error: function () {
        console.log("error")
      }
    })
    return result;
  }

  thumbnail(size,id,album,callback){
    var photo = new XMLHttpRequest();
    photo.open("GET","http://localhost:8080/photo/thumbnail/"+size+"/"+id);
    photo.responseType = "blob";
    photo.withCredentials = true;
    photo.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    photo.send();              
    
    photo.onreadystatechange = function(){
      if (photo.readyState==4 && photo.status==200){
        console.log(photo.response); 
        callback(photo.response,album);
      }
    }
  }
  
  photoUpload(photoList,content,tags,right,error){
    // 参数：上传列表，内容，正确上传，标签,错误上传

    console.log("参数:上传列表:",photoList,",内容:",content,",tag:",tags,",right:",right,"error:",error);
    

    var _this = this;
    var List = photoList;
    var TryList = [];
    var TrackList = [];  // final error list
    var ErrorList = [];
    var CorrectList = [];
    var i = 0;   // 每轮执行次数，计数器
    var j = [];  // 执行轮数，计数器

    
    var callback = function(index){
      i++;  // 每执行一次加1
      if(_this.process)console.log("执行第",j.length,"轮,第",i,"次",index); 

      if(i==List.length){
        if(_this.process)console.log("第"+j.length+"轮执行完成，剩余错误元素",ErrorList);
        
        // 错误列表中存在元素，并且，执行数次小于3
        if(ErrorList.length > 0 && j.length < 3){
          i=0;  // 本轮执行完成，执行次数，计数器归0
          List = ErrorList;  // 把错误元素附值于List，准备下一轮
          ErrorList = [];  // 错误列表清空，为记录下一轮错误元素做准备
          TrackList = TrackList.concat(TryList);
          TryList = [];
          _this.start(List,CorrectList,ErrorList,TryList,j,callback,right);  // 递归调用
        }else{
          // 完成
          TrackList = TrackList.concat(TryList);
          TryList = [];
          if(_this.process)console.log("上传完成,TrackList:",TrackList);
          _this.record(CorrectList,TrackList,content,tags);  // 回调记录函数
          console.log("结果:",CorrectList,TrackList,content,tags);
          // error(ErrorList);  // 回调错误处理函数
        }
      }
    }

    if(_this.process) console.log("开始上传任务");
    this.start(List,CorrectList,ErrorList,TryList,j,callback,right); 
  }
  
  private start(List,CorrectList,ErrorList,TryList,j,callback,right){
    var _this = this;
    j.push("excute");

    $.each(List,function(index,value){    // 遍历上传列表
      var client = new XMLHttpRequest();
      var rf = new FileReader();
      value.md5 = md5(value.dataUrl.split(",")[1])

      rf.readAsArrayBuffer(value.file);  // 将文件转为ArrayBuffer再创建Blob，并执行上传
      client.open("POST", _this.user.domain+"/photo/upload");
      client.overrideMimeType("application/octet-stream");
      client.withCredentials = true;
      rf.onload=function(){
        value.blob = rf.result;
        client.send(value.blob);
      }

      client.onreadystatechange = function(){   // 接收上传结果，并执行相应的操作
        if (client.readyState==4 && client.status==200){
          var data = JSON.parse(client.response);
          console.log(data);
          if(data.status == 0){
            // success 
            if(value.md5.toLowerCase() != data.data.MD5.toLowerCase()){
              if(_this.process)console.log("MD5匹配不成功",value.md5,data.data.MD5,data);
              value.recordname = data.data.name;
              ErrorList.push(value);
              TryList.push(data.data.name);
            }else{
              if(_this.process)console.log("ok");
              value.recordname = data.data.name;
              CorrectList.push(value);
              right(value.dataUrl,value.filename) 
            }
          }else{
            // failed
            console.log(data.message);
          }
          callback(index);
        }
      }
    });
  };

  private record(List,TrackList,content,tags){
    var _this = this;
    console.log(List);
    console.log(TrackList);

    var id = this.user.user.id;
    var list = List;
    var data = [];

    for(var i = 0;i < list.length;i++){  // 遍历正确列表,并添加到data数组中
      var item = {
        "userId": id,
        "fileName": List[i].filename,
        "recordName": List[i].recordname,
        "describe": List[i].descript,
        "tag": List[i].tag,
        "error": false
      };
      data.push(item);
    }
    for(var i = 0;i < TrackList.length;i++){  // 遍历踪迹列表，并添加到data数组中
      var item = {
        "userId": id,
        "fileName": null,
        "recordName": TrackList[i],
        "describe": null,
        "tag": null,
        "error": true
      };
      data.push(item);
    }

    console.log("data:",data);

    var photolist = "";
    for(var i = 0; i< data.length;i++){
      photolist += data[i].recordName;
      if(i != data.length-1){
        photolist += ",";
      }
    }

    var corrent= "";
    for(var i = 0; i< data.length;i++){
      if(!data[i].error){

      corrent += data[i].recordName;
      if(i != data.length-1){
        corrent += ",";
      }
      }
    }

    $.ajax({    // 让服务器将错误上传的图片删除
      type: "POST",
      url: this.user.domain+"/photo/record",
      async: false,
      data: JSON.stringify(data),
      contentType: "application/json",
      dataType: "json",
      xhrFields: { withCredentials: true },
      crossDomain: true,
      success: function (data) {
        console.log(data);
        // _this.router.navigate(['uploadedit']);
      },
      error: function() {
        console.log("error")
        // _this.router.navigate(['uploadedit']);
      }
    })
    
    $.ajax({
      type: "PUT",
      url: this.user.domain+"/album/tag",
      async: false,
      data: JSON.stringify({
        "photoList":photolist,
        "userId": id,
        "tags": tags,
        "content":content
      }),
      contentType: "application/json",
      dataType: "json",
      xhrFields: { withCredentials: true },
      crossDomain: true,
      success: function (data) {
        console.log(data);
      },
      error: function() {
        console.log("error")
      }
    })
    
    $.ajax({
      type: "POST",
      url: this.user.domain+"/article",
      async: false,
      data: JSON.stringify({
        "photoList":photolist,
        "userId": id,
        "tags": tags,
        "content":content
      }),
      contentType: "application/json",
      dataType: "json",
      xhrFields: { withCredentials: true },
      crossDomain: true,
      success: function (data) {
        console.log(data);
      },
      error: function() {
        console.log("error")
      }
    })
  }
}
