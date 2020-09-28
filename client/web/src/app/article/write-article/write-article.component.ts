import { Component, OnInit } from '@angular/core';
import { UserService } from '../../service/user.service';
import { PhotoService } from '../../service/photo.service';
import * as $ from 'jquery';
import { Photo } from 'src/app/class/photo';
import { User } from 'src/app/class/user';

@Component({
  selector: 'app-write-article',
  templateUrl: './write-article.component.html',
  styleUrls: ['./write-article.component.css']
})
export class WriteArticleComponent implements OnInit {

  constructor(
    private user: UserService,
    private photo: PhotoService
  ) { }

  display:boolean = false;
  photoInboxClass = "selectedimg";
  filenameClass = "filename";
  selectOption:String = null;
  NameList = [];
  PhotoList = [];
  SelectList = [];
  TagsList = [];
  searchTagOn:Boolean = true;

  // 生命周期函数
  ngDoCheck(): void {
    this.display = this.user.writeDisplay;
  }
  ngOnInit(): void {
    $("write_box").height($(window).height())
    console.log($(window).height());
    var _this = this;
  }
  ngAfterViewChecked(){
    var _this = this;
    if($("#tag").find("select") && $("#tag").find("select").html() == ""){
      console.log("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
      this.fillAlbumSelect();
    }
  }
  ngAfterViewInit(){
    var _this = this;
    console.log($("#eventBind"));
    var handle = function (e){
        console.log("wwwwww",e.target.innerHTML); 
        _this.TagsList.push(e.target.innerHTML);
      }
    $("#eventBind").on("click","#searchContent>p",handle);

    document.onkeydown=showkey;
    function showkey(e){
      var key = e.keyCode;
      console.log("key:",key);
    }

  }

  // 搜索标签并显示在选择列表
  searchTags(e){
    var _this = this;
    console.log("kkkk:",e.keyCode);
    if(e.keyCode == 13){
      console.log("enter:",e.target.value);
      _this.TagsList.push(e.target.value);
      this.photo.Itag(e.target.value);
      e.target.value = null;
    }
    var list = this.photo.tag(e.target.value);
    $("#searchContent").html("");
    for(var i = 0;i< list.length;i++){
      var p = document.createElement("p");
      $("#searchContent").append("<p style=width:150px>"+list[i].tag+"</p>");
    }

  }
  
  // 标签搜索关闭按扭
  searchBlur(){
    $("#searchContent").html("");
  }

  // 获取该用户创建的专辑
  fillAlbumSelect(){
    var data = this.photo.fetchAlbums();
    console.log(data);
    for(var i = 0;i<data.length;i++){
      var div = document.createElement('div'); 
      var option = <HTMLOptionElement>new Option(); 
      option.value = data[i].id
      option.innerHTML = data[i].albumName
      div.appendChild(option);
      $("#tag").find("select").html($("#tag").find("select").html() + div.innerHTML);
    }
  }

  // 关闭写文章弹出层
  close(){
    this.user.writeDisplay = false;
  }

  // 添加照片
  addPhoto(){
    console.log("addadad");
    document.getElementById("upload").click();
  }

  select(filename,e){
    console.log(filename);
    console.log(e);
    if(this.SelectList.indexOf(filename) == -1){  
      console.log($(e).attr("class","fa fa-circle"));
      this.SelectList.push(filename);
    }else{
      this.SelectList.splice(this.SelectList.indexOf(filename),1);
      console.log($(e).attr("class","fa fa-circle-o"));

    }
    console.log(this.SelectList);
  }
 
  // 删除照片
  delete(filename){
    console.log(filename);
    console.log(this.PhotoList);
    for(var i = 0; i< this.PhotoList.length;i++){
      if(this.PhotoList[i].filename == filename){
        console.log(this.PhotoList[i]);
        this.PhotoList.splice(i,1);
    console.log(this.PhotoList);
      }
    }
  }

  // 文件选择改变事件处理，
  change(){
    var _this = this;
    var input = <HTMLInputElement>document.getElementById("upload");
    var filelist = input.files;
    var callback = function (img,index,photo) {
        let tmpNode = document.createElement('div')
        tmpNode.appendChild(img) 
        let str = tmpNode.innerHTML
        tmpNode = img = null; 
        var id = (index+5)*Math.random();
        var docu = '<div id="selectedimg_'+id+'" style="width: 150px; height:150px; float: left; background:url('+photo.dataUrl+'); background-position:center center; background-size:100%;"><p><input photo="'+photo.filename+'" type="button" value="del" class="del" >'+
          photo.filename+'</p></div>'
        $("#photo_container").append(docu);
        $("#selectedimg_"+id).css("background","red");  // 绑定样式
        
        if(index == filelist.length-1){
          $(".del").click(function(e){
            var name = $(this).attr("photo");
            console.log(name);
            $(this).parent().parent().remove();
            console.log(_this.PhotoList);
            $.each(_this.PhotoList,function(index,value){
              if(_this.PhotoList[index].filename == name){
                console.log(_this.PhotoList.splice(index,1));
                return false;
              }
            })
            console.log(_this.PhotoList);
          })
        }
        
      }
    // 遍历本次选择的文件列表
    $.each(filelist,function(index,value){
      var photo = new Photo;  // 为每个文件创建一个Photo对象
      var rfurl = new FileReader();
      photo.file = value;

      if(_this.NameList.indexOf(value.name) == -1){  // 确保文件不重复
        rfurl.readAsDataURL(value); // 开始读取DataUrl
        rfurl.onload=function(){
          photo.filename = value.name;
          photo.dataUrl = rfurl.result.toString();
          var img = new Image();    // 创建图片对象
          img.src = rfurl.result.toString();
          img.onload = () => {
            if(img.width>img.height) photo.asBackground = "auto 100%";
            else photo.asBackground = "100% auto";
          }
          // callback(img,index,photo);
          _this.PhotoList.push(photo);
          _this.NameList.push(photo.filename);
          if(index == filelist.length-1){
            console.log(_this.PhotoList);

          }
        }
      }else{
        console.log("Don't select duplicate file");
      }
    })
  }
  
  // 上传
  upload(){
    var _this = this;
    var List = _this.PhotoList;
    
    var error = function(ErrorList){
      $.each(ErrorList,function(index,value){
        var img = <HTMLImageElement>new Image();
        img.src = value.dataUrl;
        img.width = 50;
        $("#errorlist").append(img);
        $("#errorlist").append(value.filename+"<br>");
      })
    }
    var right = function(dataUrl,filename){
      var img = <HTMLImageElement>new Image();
      img.src = dataUrl;
      img.width = 50;
      $("#photolist").append(img);
      $("#photolist").append(filename+"<br>");
    }
    var content = <HTMLInputElement>document.getElementById("content");
    this.photo.photoUpload(List,content.value,"tag,taga,tags",right,error);
  }

}

