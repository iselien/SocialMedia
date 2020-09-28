import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { User } from 'src/app/class/user';
import { UserService } from 'src/app/service/user.service';
import { PhotoService } from 'src/app/service/photo.service';
import * as $ from 'jquery';

@Component({
  selector: 'app-personal',
  templateUrl: './personal.component.html',
  styleUrls: ['./personal.component.css']
})
export class PersonalComponent implements OnInit {

  personalId: number;
  user:User;
  self:boolean = false;
  article;
  albums;
  articleList = [];


  constructor(
    private actRoute: ActivatedRoute,
    private userService: UserService,
    private photoService: PhotoService
  ) {
    // console.log(this.user);
    // this.albums = this.userService.allAlbums(this.personalId);
  }

  ngOnInit(): void {
    this.personalId = this.actRoute.snapshot.params.id;  // 获取路由参数
    console.log("路由参数："+this.personalId);

    // 判断角色，是否为当前登录的用户自身，并获取对应的用户信息数据
    if(!this.userService.user){
      this.user = null;
    }else if(this.personalId == this.userService.user.id){
      this.self = true;
      this.user = this.userService.selfInfo();
    }else{
      this.self = false;
      this.user = this.userService.userInfo(this.personalId);
    }
    console.log("用户信息："+this.user);
  }

  ngDoCheck(){
    // 当路由参数变换时，重新初始化本页面
    if(this.personalId != this.actRoute.snapshot.params.id){
      this.personalId = this.actRoute.snapshot.params.id;
      this.ngOnInit()
    }
  }

  ngAfterViewInit(){
    


    // 默认显示页设置
    $("#content_inbox").show();

    // 加载所有文章
    this.showall()
    // 加载专辑
    this.fetchAlbums();
  }

  /* 按扭控制 */
  resume(){
    $("#resume_inbox").show();
    $("#content_inbox").hide();
    $("#photo_inbox").hide();
  }

  home(){
    $("#content_inbox").show();
    $("#photo_inbox").hide();
    $("#resume_inbox").hide();
  }

  photo(){
    $("#photo_inbox").show();
    $("#content_inbox").hide();
    $("#resume_inbox").hide();
  }

  addAlbum(){
    this.userService.addAlbumDisplay = true;
    console.log(this.userService.addAlbumDisplay);
  }

  showall(){
    var _this = this;
    this.articleList = this.userService.allArticle(this.personalId);
    console.log("用户文章",this.articleList);
    
    for(var i = 0;i<_this.articleList.length;i++){
      var article = _this.articleList[i];
      var articleHTML = 
        '<div style="width:100%;clear:both"> \
          <p><span class="pimg"></span><span>Matas Rastenis</span></p> \
          <p> '+article.createTime+'</p> \
          <p>'+article.content+'</p> \
          <div id="img'+article.id+'"></div> \
          <p>&nbsp;</p>\
        </div>';
      $("#content_inbox").append(articleHTML);  // 插入一条文章

      // var data = _this.articleList[i].photoList.split(",")
      // // console.log(data);
      for(var k = 0; k< article.articlePhotoList.length;k++){
        this.getPhoto(article.articlePhotoList[k].id,article.id);  // 
      }
    }
  }

  private getPhoto(id,parentId){
    console.log("owwwwwwwwwwwwwwwwwwwllmmmmmmmmmmm",id);
    var photo = new XMLHttpRequest();
    photo.open("GET","http://localhost:8080/photo/thumbnail/1920x1080/"+id);
    photo.responseType = "blob";
    photo.withCredentials = true;
    photo.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    photo.send();              
    
    photo.onreadystatechange = function(){
      if (photo.readyState==4 && photo.status==200){
        console.log(photo.response);
        let tmpNode = document.createElement('div')
        
        var url = URL.createObjectURL(photo.response);
        var img = new Image();
        img.src = url;
        img.onload = function(){
          // console.log(img.width,img.height);
          $(tmpNode).css("background","url("+URL.createObjectURL(photo.response)+") no-repeat")
            .css("background-position","center center")
            .css("height","250px")
            .css("width","250px")
            .css("margin","10px")
            .css("border","#eee solid 3px")
            .css("float","left")

            if(img.width>img.height){
              $(tmpNode).css("background-size","auto 100%")
            }else if(img.width>img.height){
              $(tmpNode).css("background-size","100% auto")
            }else{
              $(tmpNode).css("background-size","100% 100%")
            }
          $("#img"+parentId).append(tmpNode).css("width","100%").css("float","left").css("clear","right");
        }
      };
    }
  }

  

  // 获取个人专辑
  fetchAlbums(){
    console.log("oooooooooooooooooooooooooooooooooooooo");
    var albums = this.photoService.fetchAlbums();
    console.log(albums);
    function fill(blob,album){
      var fr = new FileReader();
      console.log("aaa",blob);
      fr.readAsDataURL(blob);
      var img = new Image();
      var div = <HTMLElement>document.createElement("div");
      var p = document.createElement("p");
      p.innerHTML = album.albumName;
      p.style.color = "white";
      div.appendChild(p);
      div.style.width = "150px";
      div.style.height = "150px";
      div.style.margin = "5px";
      div.style.float = "left";


      fr.onload = function(){
        img.src = fr.result.toString();
        img.onload = function () {
         if(img.width>img.height) div.style.backgroundSize = "auto 100%"; 
         else div.style.backgroundSize = "100% auto"; 
        }
        div.style.background = "url("+fr.result+")";
        div.style.backgroundPosition = "center center";
        console.log(div);
        $("#lastEle").before(div);
      }
    }
    for(var i = 0; i< albums.length; i++){
      this.photoService.thumbnail("1920x1080",albums[i].centralPhotoId,albums[i],fill);
    }
    // $("#albums").width("100%");
  }

       
}