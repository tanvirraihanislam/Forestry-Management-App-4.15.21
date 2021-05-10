import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Button } from 'selenium-webdriver';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  showMe:boolean = false
  showNot:boolean = true

  showTable:boolean = true
  showRest:boolean = false
  showText:boolean = true


  toggle(){
    this.showNot = !this.showNot
    this.showMe=!this.showMe
    this.showText=false;
  }

  toggle2(){
    this.showTable=!this.showTable
    this.showRest=!this.showRest
    this.showText=false;
  }

  toggle3(){

    this.showTable=false;
    this.showText=true;
  }
  toggle4(){
    this.showTable=true;
    this.showText=false;
  }

  

}
