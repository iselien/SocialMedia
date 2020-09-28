import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor() { 
    // this.init();
  }

  ngOnInit(): void {
    this.init();
  }

  popularEle = [
    "https://tuchong.pstatp.com/2732846/ft640/20811119.webp",
    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586867688081&di=8dd3ce7bea4b6827f7164a3df231c255&imgtype=0&src=http%3A%2F%2Fimg2.fengniao.com%2Fproduct%2F157%2F266%2FcejSHIUINovPA.jpg",
    "https://tuchong.pstatp.com/244519/ft640/15195139.webp",
    "https://tuchong.pstatp.com/1724826/ft640/12024514.webp",
    "https://tuchong.pstatp.com/2367683/ft640/162662491.webp",
    "https://tuchong.pstatp.com/2613444/ft640/29542573.webp",
    "https://tuchong.pstatp.com/3172693/ft640/8435591.webp",
    "https://tuchong.pstatp.com/1811570/ft640/512426462.webp",
    "https://tuchong.pstatp.com/3172693/ft640/8435682.webp",
    "https://tuchong.pstatp.com/2732846/ft640/20811133.webp",
    "https://tuchong.pstatp.com/2517772/ft640/15957661.webp",
    "https://tuchong.pstatp.com/3012478/ft640/182452789.webp",
    "https://tuchong.pstatp.com/2673565/ft640/19372018.webp",
    "https://tuchong.pstatp.com/48047/ft640/14067491.webp",
    "https://tuchong.pstatp.com/48047/ft640/14085687.webp",
    "https://tuchong.pstatp.com/981318/ft640/242287039.webp",
    "https://tuchong.pstatp.com/2613444/ft640/29542573.webp"
  ];

  disoreder(arr1){
    var arr2 = [];
    while(arr1.length>0){
        var index = Math.floor(Math.random()*arr1.length);
        arr2.push(arr1[index]);
        arr1.splice(index,1);
    }
    return arr2;
  }


  init(){
    var app = new HomeComponent();
    var total = 0;
    var po = $("#popular_box>div");
    var elemax = Math.round(Math.random()) == 0 ? 3 : 4;
    for(var i = 0; i< elemax; i++){
      var inelemax = Math.round(Math.random()) == 0 ? 3 : 4;
      var height = 300 + Math.random()*50;
      var gap = inelemax == 3 ? 2*15 : 3*15;
      var realwidth = $(po).width()-gap;
      var width = inelemax == 3 ? realwidth *.333 : realwidth*.25;
      var arr = new Array(inelemax);
      var offset = new Array(inelemax-1);
  
      // 生成偏差数组
      for(var k = 0; k < offset.length;k++){
        console.log(offset[k] = inelemax == 3 ? 80+Math.random()*(130-80) : 50+Math.random()*(80-50));
      } 
  
      // 填充偏差
      for(var k = 0; k < arr.length;k++){
        if(k == 0){
          console.log(arr[k] = width+offset[k]);
        }else if(k == arr.length-1){
          console.log(arr[k] = width-offset[k-1]);
        }else{
          console.log(arr[k] = width-offset[k-1]+offset[k]);
        }
      }
  
      // 乱序填充
      arr = app.disoreder(arr); 
  
      $(po).append("<div id='popular_ele_"+i+"'></div>");
      $(po).children("#popular_ele_"+i)
        .css("width","100%")
        .css("height",height)
        .css("margin","20px 0");
  
      for(var j = 0; j< inelemax; j++){
        total++;
        var gap = inelemax == 3 ? 2*15 : 3*15;
        var realwidth = $(po).width()-gap;
        var width = inelemax == 3 ? realwidth *.333 : realwidth*.25;
  
        $(po).children("#popular_ele_"+i).append("<div id='popular_inele_"+i+"_"+j+"'>www</div>");
        $("#popular_inele_"+i+"_"+j)
          .css("float","left")
          .css("width",arr[j])
          .css("height",height)
          .css("background-image","url("+app.popularEle[total]+")")
          // .css("background-size","auto 100%")
          .css("background-repeat","no-repeat")
          .css("background-position","50% 50%");
  
        if(j == inelemax -1){
          $("#popular_inele_"+i+"_"+j).css("margin-right","0");
        }else{
          $("#popular_inele_"+i+"_"+j).css("margin-right","15px");
        }
      }
    }
    console.log(total);
  }

}


// onload = function home(){
//   var app = new HomeComponent();
//   var total = 0;
//   var po = $("#popular_box>div");
//   var elemax = Math.round(Math.random()) == 0 ? 3 : 4;
//   for(var i = 0; i< elemax; i++){
//     var inelemax = Math.round(Math.random()) == 0 ? 3 : 4;
//     var height = 300 + Math.random()*50;
//     var gap = inelemax == 3 ? 2*15 : 3*15;
//     var realwidth = $(po).width()-gap;
//     var width = inelemax == 3 ? realwidth *.333 : realwidth*.25;
//     var arr = new Array(inelemax);
//     var offset = new Array(inelemax-1);

//     // 生成偏差数组
//     for(var k = 0; k < offset.length;k++){
//       console.log(offset[k] = inelemax == 3 ? 80+Math.random()*(130-80) : 50+Math.random()*(80-50));
//     } 

//     // 填充偏差
//     for(var k = 0; k < arr.length;k++){
//       if(k == 0){
//         console.log(arr[k] = width+offset[k]);
//       }else if(k == arr.length-1){
//         console.log(arr[k] = width-offset[k-1]);
//       }else{
//         console.log(arr[k] = width-offset[k-1]+offset[k]);
//       }
//     }

//     // 乱序填充
//     arr = app.disoreder(arr); 

//     $(po).append("<div id='popular_ele_"+i+"'></div>");
//     $(po).children("#popular_ele_"+i)
//       .css("width","100%")
//       .css("height",height)
//       .css("margin","20px 0");

//     for(var j = 0; j< inelemax; j++){
//       total++;
//       var gap = inelemax == 3 ? 2*15 : 3*15;
//       var realwidth = $(po).width()-gap;
//       var width = inelemax == 3 ? realwidth *.333 : realwidth*.25;

//       $(po).children("#popular_ele_"+i).append("<div id='popular_inele_"+i+"_"+j+"'>www</div>");
//       $("#popular_inele_"+i+"_"+j)
//         .css("float","left")
//         .css("width",arr[j])
//         .css("height",height)
//         .css("background-image","url("+app.popularEle[total]+")")
//         // .css("background-size","auto 100%")
//         .css("background-repeat","no-repeat")
//         .css("background-position","50% 50%");

//       if(j == inelemax -1){
//         $("#popular_inele_"+i+"_"+j).css("margin-right","0");
//       }else{
//         $("#popular_inele_"+i+"_"+j).css("margin-right","15px");
//       }
//     }
//   }
//   console.log(total);
// }