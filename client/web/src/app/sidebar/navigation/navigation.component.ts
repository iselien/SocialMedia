import { Component, OnInit } from '@angular/core';
import { UserService } from '../../service/user.service';
import { User } from 'src/app/class/user';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  constructor(
    private user : UserService
  ) { }
  ngOnInit(): void {
  }

  username:String;
  id:Number;

  ngDoCheck(){
    if(this.user.user){
      this.username = this.user.user.username;
      this.id = this.user.user.id;
    }
  }

  writeDisplay(){
    this.user.writeDisplay = true;
  }
}
