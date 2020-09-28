import { Component, OnInit } from '@angular/core';
import { UserService } from '../../service/user.service';
import { PhotoService } from '../../service/photo.service';

@Component({
  selector: 'app-add-album',
  templateUrl: './add-album.component.html',
  styleUrls: ['./add-album.component.css']
})
export class AddAlbumComponent implements OnInit {

  constructor(
    private user: UserService,
    private photo: PhotoService 
    ) { }

  ngOnInit(): void {
  }
  display:boolean = false;
  name;
  describe;

  ngDoCheck(): void {
    this.display = this.user.addAlbumDisplay;
    console.log(this.display);
  }

  // 关闭写文章弹出层
  close(){
    this.user.addAlbumDisplay = false;
  }

  create(){
    this.photo.insertAlbums(this.user.user.id,this.name,this.describe);
  }




}
