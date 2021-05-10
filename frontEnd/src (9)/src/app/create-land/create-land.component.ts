import { Component, OnInit } from '@angular/core';
import { Land } from 'src/module/land';
import { LandServiceService } from '../land-service.service';

@Component({
  selector: 'app-create-land',
  templateUrl: './create-land.component.html',
  styleUrls: ['./create-land.component.css']
})
export class CreateLandComponent implements OnInit {

  validationMessages: string[] = null;
  errorMessage: string = null;
  successMessage: string = null;

  constructor(private service: LandServiceService) { }

  ngOnInit() {
  }


  createNew(data) {  
    this.service.addLand(data).subscribe(
      (message) => {
        this.successMessage = message;
        this.validationMessages = null;
        this.errorMessage = null;
      },
      (failure) => {
        this.successMessage = null;
        //this.validationMessages = JSON.parse(failure.error).errors;
        //this.errorMessage = JSON.parse(failure.error).errorMessage;
      }

    )

  }

  // createNew(data: Land) {
  //   this.service.addLand(data).subscribe(
  //     (message) => {
  //       this.successMessage = message;
  //       this.validationMessages = null;
  //       this.errorMessage = null;
  //     },
  //     (failure) => {
  //       this.successMessage = null;
  //       this.validationMessages = JSON.parse(failure.error).errors;
  //       this.errorMessage = JSON.parse(failure.error).errorMessage;
  //     }

  //   )

  // }

}
